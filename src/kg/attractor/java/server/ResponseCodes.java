package kg.attractor.java.server;

public enum ResponseCodes {
    NOT_FOUND(404);

    private final int code;

    ResponseCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
