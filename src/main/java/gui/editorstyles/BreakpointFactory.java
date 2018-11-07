package gui.editorstyles;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import org.reactfx.value.Val;

import java.util.function.IntFunction;

public class BreakpointFactory implements IntFunction<Node> {

    private ObservableList<Integer> breakpointLineNumbers;

    public BreakpointFactory(ObservableList<Integer> breakpointLineNumbers) {
        this.breakpointLineNumbers = breakpointLineNumbers;
    }

    @Override
    public Node apply(int lineNumber) {
        Circle circle = new Circle();
        circle.getStyleClass().add("breakpoint");
        circle.setRadius(7);

        SimpleObjectProperty<Boolean> visible = new SimpleObjectProperty<>(breakpointLineNumbers.contains(lineNumber));
        breakpointLineNumbers.addListener((ListChangeListener<? super Integer>)  c -> {
            visible.set(breakpointLineNumbers.contains(lineNumber));
        });

        circle.visibleProperty().bind(
                Val.flatMap(circle.sceneProperty(), scene -> scene != null ? visible : Val.constant(false)));

        return circle;
    }
}