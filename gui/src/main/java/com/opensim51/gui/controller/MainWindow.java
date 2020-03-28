package com.opensim51.gui.controller;

import com.opensim51.assembler.Assembler;
import com.opensim51.assembler.mcs51.mcu8051.Mcu8051Assembler;
import com.opensim51.assembler.mcs51.mcu8051.format.SourceCodeFormatter;
import com.opensim51.gui.controller.device.DisplayArrayController;
import com.opensim51.gui.editorstyles.BreakpointFactory;
import com.opensim51.gui.editorstyles.ExecutionPointArrowFactory;
import com.opensim51.gui.editorstyles.SelectedLineArrowFactory;
import com.opensim51.gui.editorstyles.TokensHighlighting;
import com.opensim51.simulator.ExecutionListener;
import com.opensim51.simulator.ExecutionListenerAdapter;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.memory.datatype.UInt8;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.StringUtils;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;

public class MainWindow {

    private final Simulator simulator = Simulator.getInstance();
    private final Map<String, Stage> shownWindows = new HashMap<>();
    private final Map<String, Stage> shownPortWindows = new HashMap<>();
    private final Map<String, Stage> shownTimerWindows = new HashMap<>();
    private final Map<String, Object> loadedControllers = new HashMap<>();
    private final Map<UInt16, Integer> executableEditorLines = new HashMap<>();
    private final SimpleObjectProperty<Integer> executionPointEditorLine = new SimpleObjectProperty<>(-1);
    private final ObservableList<Integer> breakpointEditorLines = FXCollections.observableArrayList();
    private final AtomicBoolean uiThreadReadinessFlag = new AtomicBoolean(true);
    @FXML
    private MenuItem reformatFileMenuItem;
    @FXML
    private MenuItem displayMenuItem;
    @FXML
    private Button runButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button stepButton;
    @FXML
    private MenuItem interruptMenuItem;
    @FXML
    private MenuItem timer0MenuItem;
    @FXML
    private MenuItem timer1MenuItem;
    @FXML
    private TextField statusBarTextField;
    @FXML
    private TabPane tabPane;
    @FXML
    private MenuItem burnHexMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem displayExampleMenuItem;
    @FXML
    private MenuItem registerBanksExampleMenuItem;
    @FXML
    private RegistersController registersController;
    @FXML
    private MemoryController memoryController;
    @FXML
    private DisplayArrayController displayArrayController;
    @FXML
    private ScrollPane codeScrollPane;
    @FXML
    private MenuItem port0MenuItem;
    @FXML
    private MenuItem port1MenuItem;
    @FXML
    private MenuItem port2MenuItem;
    @FXML
    private MenuItem port3MenuItem;
    private Stage primaryStage;

    @FXML
    public void initialize() {
        registersController.setMainWindow(this);
        memoryController.setMainWindow(this);
        memoryController.setMemoryTableContainer(tabPane);

        interruptMenuItem.setOnAction(event -> showWindow("Interrupt System", "interrupt.fxml"));

        port0MenuItem.setOnAction(event -> showPortWindow("0", simulator.getInternalData().P0));
        port1MenuItem.setOnAction(event -> showPortWindow("1", simulator.getInternalData().P1));
        port2MenuItem.setOnAction(event -> showPortWindow("2", simulator.getInternalData().P2));
        port3MenuItem.setOnAction(event -> showPortWindow("3", simulator.getInternalData().P3));

        timer0MenuItem.setOnAction(event -> showTimerWindow("0"));
        timer1MenuItem.setOnAction(event -> showTimerWindow("1"));

        displayMenuItem.setOnAction(event -> showWindow("Display", "device/displayArray.fxml"));

        CodeArea editor = new CodeArea();
        editor.setId("editor");
        editor.getStylesheets().add(getClass().getResource("editor.css").toExternalForm());

        reformatFileMenuItem.setOnAction(event -> {
            String text = editor.getText();
            if (StringUtils.isNotBlank(text)) {
                String formattedText = SourceCodeFormatter.format(text);
                editor.replaceText(formattedText);
            }
        });

        IntFunction<Node> breakpointFactory = new BreakpointFactory(breakpointEditorLines);
        IntFunction<Node> selectedLineArrowFactory = new SelectedLineArrowFactory(editor.currentParagraphProperty());
        IntFunction<Node> executionPointArrowFactory = new ExecutionPointArrowFactory(executionPointEditorLine);
        IntFunction<Node> lineNumberFactory = LineNumberFactory.get(editor);
        IntFunction<Node> graphicFactory = line -> {

            Node lineNumberNode = lineNumberFactory.apply(line);

            // each breakpoint node has a separate container that has a mouse click listener for showing or hiding the breakpoint node
            HBox breakpointContainer = new HBox(breakpointFactory.apply(line));
            breakpointContainer.setAlignment(Pos.CENTER);
            breakpointContainer.setPadding(new Insets(0, 5, 0, 5));
            breakpointContainer.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                    int currentLineNumber = Integer.parseInt(((Label) lineNumberNode).getText().trim()) - 1;
                    if (breakpointEditorLines.contains(currentLineNumber)) {
                        breakpointEditorLines.remove((Integer) currentLineNumber);
                    } else {
                        breakpointEditorLines.add(currentLineNumber);
                    }
                }
            });

            HBox gutter = new HBox(
                    breakpointContainer,
                    selectedLineArrowFactory.apply(line),
                    executionPointArrowFactory.apply(line),
                    lineNumberNode);

            gutter.getStyleClass().add("gutter");

            // addEventFilter and removeEventFilter must receive the same instance of EventHandler
            EventHandler<MouseEvent> consume = Event::consume;

            gutter.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> editor.addEventFilter(MouseEvent.MOUSE_PRESSED, consume));
            gutter.addEventHandler(MouseEvent.MOUSE_EXITED, event -> editor.removeEventFilter(MouseEvent.MOUSE_PRESSED, consume));

            return gutter;
        };
        editor.setParagraphGraphicFactory(graphicFactory);

        // recompute the syntax highlighting 20 ms after user stops editing area
        // Subscription cleanupWhenNoLongerNeedIt = editor
        editor

                // plain changes = ignore style changes that are emitted when syntax highlighting is reapplied
                // multi plain changes = save computation by not rerunning the code multiple times
                //   when making multiple changes (e.g. renaming a method at multiple parts in file)
                .multiPlainChanges()

                // do not emit an event until 20 ms have passed since the last emission of previous stream
                .successionEnds(Duration.ofMillis(20))

                // run the following code block when previous stream emits an event
                .subscribe(ignore -> editor.setStyleSpans(0, TokensHighlighting.computeHighlighting(editor.getText())));

        // when no longer need syntax highlighting and wish to clean up memory leaks
        // run: `cleanupWhenNoLongerNeedIt.unsubscribe();`

        codeScrollPane.setContent(new VirtualizedScrollPane<>(editor));

        translateButton.setOnAction(event -> {
            statusBarTextField.clear();

            try {
                Assembler assembler = new Mcu8051Assembler();
                assembler.assemble(editor.getText(), (line, locationCounter, machineCodes) -> {
                    executableEditorLines.put(new UInt16(locationCounter), line - 1);
                    for (Integer machineCode : machineCodes) {
                        simulator.getExternalCode().setCellValue(locationCounter, UInt8.valueOf(machineCode));
                        locationCounter++;
                    }
                }, (line, charPositionInLine, message) -> {
                    statusBarTextField.setText(line + ":" + charPositionInLine + " " + message);
                    throw new IllegalStateException("translation canceled due to a syntax error");
                });

                if (!executableEditorLines.isEmpty()) {
                    executableEditorLines.entrySet().stream()
                            .reduce((a, b) -> a.getKey().compareTo(b.getKey()) <= 0 ? a : b)
                            .ifPresent(entry -> executionPointEditorLine.set(entry.getValue()));
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            updateUserInterface();
        });

        burnHexMenuItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Intel HEX File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Intel HEX files (*.hex)", "*.hex");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(primaryStage);

            try {
                if (file != null) {
                    simulator.burnIntel8HexFile(file);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            memoryController.update();
        });

        ProgramExecutionService executionService = new ProgramExecutionService();
        ExecutionListener executionListener = new ExecutionListenerAdapter(true) {
            @Override
            public void process(UInt16 programCounter) {
                if (uiThreadReadinessFlag.getAndSet(false)) {
                    updateUserInterfaceFromBackgroundThread(programCounter, executionService);
                }
            }
        };

        executionService.setExecutionListener(executionListener);
        executionService.setOnCancelled(stateEvent -> executionListener.cancel());

        runButton.setOnAction(event -> {
            executionListener.reset();
            executionService.start();
        });

        stopButton.setOnAction(event -> {
            executionService.cancel();
            executionService.reset();
        });

        ExecutionListener oneTimeExecutionListener = new ExecutionListenerAdapter(false) {
            @Override
            public void process(UInt16 programCounter) {

                // highlight execution point editor line
                int lineNumber = executableEditorLines.get(programCounter);
                executionPointEditorLine.set(lineNumber);

                updateUserInterface();
            }
        };

        stepButton.setOnAction(event -> simulator.step(oneTimeExecutionListener));

        displayExampleMenuItem.setOnAction(event -> editor.replaceText(readFileToString("examples/display.A51")));
        registerBanksExampleMenuItem.setOnAction(event -> editor.replaceText(readFileToString("examples/register_banks.A51")));

        exitMenuItem.setOnAction(event -> primaryStage.close());
    }

    public void updateUserInterfaceFromBackgroundThread(UInt16 programCounter, ProgramExecutionService executionService) {
        Platform.runLater(() -> {

            // highlight execution point editor line
            int lineNumber = executableEditorLines.get(programCounter);
            executionPointEditorLine.set(lineNumber);

            // stop background thread if execution point editor line has a breakpoint
            if (breakpointEditorLines.contains(lineNumber)) {
                executionService.cancel();
                executionService.reset();
            }

            updateUserInterface();

            // unblock UI updates
            uiThreadReadinessFlag.set(true);
        });
    }

    public void updateUserInterface() {
        registersController.update();
        memoryController.update();

        for (Object controller : loadedControllers.values()) {
            if (controller instanceof Updatable) {
                ((Updatable) controller).update();
            }
        }
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private String readFileToString(String path) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(path);
            assert resource != null;
            URI uri = resource.toURI();
            byte[] bytes = Files.readAllBytes(Paths.get(uri));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void showPortWindow(String portNumber, InternalData.BitAddressableCell port) {
        if (shownPortWindows.containsKey(portNumber)) {
            shownPortWindows.get(portNumber).requestFocus();
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Parallel Port " + portNumber);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setOnCloseRequest(event -> shownPortWindows.remove(portNumber));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("port.fxml"));
            Parent root = loader.load();
            PortController controller = loader.getController();
            controller.setMainWindow(this);
            controller.setPortNumber(portNumber);
            controller.setPort(port);
            controller.update();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();

            shownPortWindows.put(portNumber, stage);
            loadedControllers.put("port" + portNumber, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTimerWindow(String timerNumber/*, Memory.BitAddressableCell port*/) {
        if (shownTimerWindows.containsKey(timerNumber)) {
            shownTimerWindows.get(timerNumber).requestFocus();
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Timer/Counter " + timerNumber);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setOnCloseRequest(event -> shownTimerWindows.remove(timerNumber));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("timer.fxml"));
            Parent root = loader.load();
            TimerController controller = loader.getController();
            controller.setTimerNumber(timerNumber);
            controller.setMainWindow(this);
            controller.initializeController();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();

            shownTimerWindows.put(timerNumber, stage);
            loadedControllers.put("timer" + timerNumber, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWindow(String title, String resource) {
        if (shownWindows.containsKey(title)) {
            shownWindows.get(title).requestFocus();
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setOnCloseRequest(event -> shownWindows.remove(title));

            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent root = loader.load();
            Object controller = loader.getController();

            loadedControllers.put(title, controller);
            if (controller instanceof MainWindowDependant) {
                ((MainWindowDependant) controller).setMainWindow(this);
            }

            if (controller instanceof Updatable) {
                ((Updatable) controller).update();
            }

            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();

            shownWindows.put(title, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
