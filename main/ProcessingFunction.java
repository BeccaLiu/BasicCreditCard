package main;

/**
 * Created by rliu on 4/18/17.
 */
public enum ProcessingFunction {
    ADD("Add"),
    CHARGE("Charge"),
    CREDIT("Credit");

    String processingType;

    ProcessingFunction(String type) {
        this.processingType = type;
    }

    String getType() {
        return processingType;
    }
}
