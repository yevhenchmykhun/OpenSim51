package gui.debug;

import simulator.memory.datatype.UnsignedInt16;

public class LineInfo {
    private int editorLineNumber;
    private UnsignedInt16 associatedLocationCounter;

    public LineInfo(int editorLineNumber, UnsignedInt16 associatedLocationCounter) {
        this.editorLineNumber = editorLineNumber;
        this.associatedLocationCounter = associatedLocationCounter;
    }

    public int getEditorLineNumber() {
        return editorLineNumber;
    }

    public UnsignedInt16 getAssociatedLocationCounter() {
        return associatedLocationCounter;
    }
}
