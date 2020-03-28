package com.opensim51.gui.editorstyles;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import org.reactfx.value.Val;

import java.util.function.IntFunction;

public class ExecutionPointArrowFactory implements IntFunction<Node> {
    private final SimpleObjectProperty<Integer> selectedLine;

    public ExecutionPointArrowFactory(SimpleObjectProperty<Integer> selectedLine) {
        this.selectedLine = selectedLine;
    }

    @Override
    public Node apply(int lineNumber) {
        Polygon triangle = new Polygon(0.0, 0.0, 12.0, 8.0, 0.0, 16.0);
        triangle.getStyleClass().add("debuggerArrow");

        ObservableValue<Boolean> visible = Val.map(selectedLine, sl -> sl == lineNumber);

        triangle.visibleProperty().bind(
                Val.flatMap(triangle.sceneProperty(), scene -> scene != null ? visible : Val.constant(false)));

        return triangle;
    }
}