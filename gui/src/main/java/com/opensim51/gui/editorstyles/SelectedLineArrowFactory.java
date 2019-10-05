package com.opensim51.gui.editorstyles;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import org.reactfx.value.Val;

import java.util.function.IntFunction;

public class SelectedLineArrowFactory implements IntFunction<Node> {
    private final ObservableValue<Integer> selectedLine;

    public SelectedLineArrowFactory(ObservableValue<Integer> selectedLine) {
        this.selectedLine = selectedLine;
    }

    @Override
    public Node apply(int lineNumber) {
        Polygon triangle = new Polygon(0.0, 0.0, 12.0, 8.0, 0.0, 16.0);
        triangle.getStyleClass().add("selectedLineArrow");

        ObservableValue<Boolean> visible = Val.map(selectedLine, sl -> sl == lineNumber);

        triangle.visibleProperty().bind(
                Val.flatMap(triangle.sceneProperty(), scene -> scene != null ? visible : Val.constant(false)));

        return triangle;
    }
}