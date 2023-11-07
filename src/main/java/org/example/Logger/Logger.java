package org.example.Logger;

public interface Logger {
    public static Logger getInstance() {
        return null;
    }

    public void log(LogState state, String message);
}