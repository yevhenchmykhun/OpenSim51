package gui.controller;

import assembler.Assembler;
import assembler.format.SourceCodeFormatter;
import gui.controller.device.DisplayArrayController;
import gui.debug.LineInfo;
import gui.editorstyles.BreakpointFactory;
import gui.editorstyles.DebuggerArrowFactory;
import gui.editorstyles.SelectedLineArrowFactory;
import gui.editorstyles.TokensHighlighting;
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
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;
import simulator.ExecutionListener;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.datatype.UInt16;
import simulator.memory.datatype.UInt8;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

public class MainWindow {

    @FXML
    private MenuItem displayMenuItem;

    @FXML
    private Button runButton;

    @FXML
    private Button translateButton;

    @FXML
    private Button stepOverButton;

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
    private MenuItem registerBanksMenuItem;

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

    private Map<String, Stage> shownPortWindows;

    private Map<String, Stage> shownWindows;

    private Map<String, Stage> shownTimerWindows;

    private Map<String, Object> loadedControllers;

    public static Simulator simulator = Simulator.getInstance();

    private List<LineInfo> lineInfos;

    public MainWindow() {
        shownPortWindows = new HashMap<>();
        shownTimerWindows = new HashMap<>();
        shownWindows = new HashMap<>();
        loadedControllers = new HashMap<>();
        lineInfos = new ArrayList<>();
    }

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

        editor.setOnKeyPressed(event -> {
            KeyCodeCombination combination = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
            if (combination.match(event)) {
                String text = editor.getText();
                String format = SourceCodeFormatter.format(text);
                editor.replaceText(format);

                // TODO: richtextfx 0.9.1 has a bug related to selection replacement
                // String selectedText = editor.getSelectedText();
                // String format = SourceCodeFormatter.format(selectedText);
                // editor.replaceSelection(format);
            }
        });

        ObservableList<Integer> breakpointLineNumbers = FXCollections.observableArrayList();
        SimpleObjectProperty<Integer> debuggedLine = new SimpleObjectProperty<>(-1);


        IntFunction<Node> breakpointFactory = new BreakpointFactory(breakpointLineNumbers);
        IntFunction<Node> selectedLineArrowFactory = new SelectedLineArrowFactory(editor.currentParagraphProperty());
        IntFunction<Node> debuggerArrowFactory = new DebuggerArrowFactory(debuggedLine);
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
                    if (breakpointLineNumbers.contains(currentLineNumber)) {
                        breakpointLineNumbers.remove((Integer) currentLineNumber);
                    } else {
                        breakpointLineNumbers.add(currentLineNumber);
                    }
                }
            });

            HBox gutter = new HBox(
                    breakpointContainer,
                    selectedLineArrowFactory.apply(line),
                    debuggerArrowFactory.apply(line),
                    lineNumberNode);

            gutter.getStyleClass().add("gutter");

            // addEventFilter and removeEventFilter must receive the same instance of EventHandler
            EventHandler<MouseEvent> consume = Event::consume;

            gutter.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                editor.addEventFilter(MouseEvent.MOUSE_PRESSED, consume);
            });

            gutter.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                editor.removeEventFilter(MouseEvent.MOUSE_PRESSED, consume);
            });

            return gutter;
        };
        editor.setParagraphGraphicFactory(graphicFactory);

        // TODO: recompute syntax highlighting for a line instead of a file

        // recompute the syntax highlighting 20 ms after user stops editing area
        Subscription cleanupWhenNoLongerNeedIt = editor

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
                Assembler assembler = new Assembler(editor.getText());
                assembler.assemble((ctx, locationCounter, machineCodes) -> {
                    lineInfos.add(new LineInfo(ctx.getStart().getLine() - 1, locationCounter));

                    for (Integer machineCode : machineCodes) {
                        simulator.getExternalCode().setCellValue(locationCounter, new UInt8(machineCode));
                        locationCounter = locationCounter.inc();
                    }
                }, new BaseErrorListener() {
                    @Override
                    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                        statusBarTextField.setText("line " + line + ":" + charPositionInLine + " " + msg);
                        throw new ParseCancellationException();
                    }
                });

                if (!lineInfos.isEmpty()) {
                    debuggedLine.set(lineInfos.get(0).getEditorLineNumber());
                }
            } catch (ParseCancellationException e) {
                e.printStackTrace();
            }

            updateUserInterface();
        });

        burnHexMenuItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open HEX File");
            File file = fileChooser.showOpenDialog(primaryStage);

            try {
                simulator.burnIntel8HexFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            memoryController.update();
        });


        ExecutionListener executionListener = new ExecutionListener() {
            private boolean running = true;

            @Override
            public void process(UInt16 programCounter) {
                int line = -1;
                for (LineInfo lineInfo : lineInfos) {
                    if (lineInfo.getAssociatedLocationCounter().equals(programCounter)) {
                        line = lineInfo.getEditorLineNumber();
                        running = !breakpointLineNumbers.contains(line);
                        break;
                    }
                }
                debuggedLine.set(line);
                editor.showParagraphInViewport(line);
            }

            @Override
            public void reset() {
                running = true;
            }

            @Override
            public boolean isRunning() {
                return running;
            }
        };

        runButton.setOnAction(event -> {
            simulator.run(executionListener);
            executionListener.reset();
            updateUserInterface();
        });


        stepOverButton.setOnAction(event -> {
            simulator.step(executionListener);
            updateUserInterface();
        });

        registerBanksMenuItem.setOnAction(event -> {
            editor.replaceText(readFileToString("examples/register_banks.A51", Charset.forName("UTF-8")));
        });

        exitMenuItem.setOnAction(event -> primaryStage.close());
    }

    private String readFileToString(String path, Charset encoding) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI uri = classLoader.getResource(path).toURI();
            byte[] bytes = Files.readAllBytes(Paths.get(uri));
            return new String(bytes, encoding);
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

}
