package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindow {

    public static InternalData data = new InternalData();

    public static Simulator simulator = new Simulator();

    @FXML
    public Button translateButton;

    @FXML
    public Button stepOverButton;

    @FXML
    public Button burnHexButton;

    @FXML
    public MenuItem interruptMenuItem;

    private Map<String, Stage> shownPortWindows;

    @FXML
    private RegistersController registersController;

    @FXML
    private MemoryController memoryController;

    @FXML
    public ScrollPane codeScrollPane;

    @FXML
    public MenuItem port0MenuItem;
    @FXML
    public MenuItem port1MenuItem;
    @FXML
    public MenuItem port2MenuItem;
    @FXML
    public MenuItem port3MenuItem;

    private Stage primaryStage;

    private Stage interruptWindow;
    private InterruptController interruptController;

    public MainWindow() {
        this.shownPortWindows = new HashMap<>();
    }

    @FXML
    public void initialize() {
        registersController.setMainWindow(this);
        memoryController.setMainWindow(this);

        interruptMenuItem.setOnAction(event -> showInterruptWindow());

        port0MenuItem.setOnAction(event -> showPortWindow("0", MainWindow.data.P0));
        port1MenuItem.setOnAction(event -> showPortWindow("1", MainWindow.data.P1));
        port2MenuItem.setOnAction(event -> showPortWindow("2", MainWindow.data.P2));
        port3MenuItem.setOnAction(event -> showPortWindow("3", MainWindow.data.P3));


        CodeArea codeArea = new CodeArea();

        // add line numbers to the left of area
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeScrollPane.setContent(codeArea);

        translateButton.setOnAction(event -> {
            simulator.translate(codeArea.getText());
        });

        burnHexButton.setOnAction(event -> {
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

        stepOverButton.setOnAction(event -> {
            simulator.step();
            updateUserInterface();
        });
    }

    private void showPortWindow(String portNumber, Memory.BitAddressableCell port) {
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
            controller.setPortNumber(portNumber);
            controller.setPort(port);

            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();

            shownPortWindows.put(portNumber, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showInterruptWindow() {
        if (interruptWindow != null) {
            interruptWindow.requestFocus();
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Interrupt System");
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setOnCloseRequest(event -> interruptWindow = null);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("interrupt.fxml"));
            Parent root = loader.load();
            interruptController = loader.getController();
            interruptController.setMainWindow(this);

            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();

            interruptWindow = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUserInterface() {
        if (interruptController != null) {
            interruptController.update();
        }
        registersController.update();
        memoryController.update();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
