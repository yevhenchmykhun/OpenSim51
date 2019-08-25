package gui.debug;

import simulator.memory.datatype.UInt16;

public class LineInfo {
    private int editorLineNumber;
    private UInt16 associatedLocationCounter;

    public LineInfo(int editorLineNumber, UInt16 associatedLocationCounter) {
        this.editorLineNumber = editorLineNumber;
        this.associatedLocationCounter = associatedLocationCounter;
    }

    public int getEditorLineNumber() {
        return editorLineNumber;
    }

    public UInt16 getAssociatedLocationCounter() {
        return associatedLocationCounter;
    }
}
