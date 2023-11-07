package org.example.Logger;

public interface Logger {
    static Logger getInstance() {
        return null;
    }

    void log(LogState state, String message);
}