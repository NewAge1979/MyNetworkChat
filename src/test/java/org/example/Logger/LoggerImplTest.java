package org.example.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerImplTest {
    private Logger logger;

    @BeforeEach
    void init() {
        this.logger = LoggerImpl.getInstance();
    }

    @Test
    void testUniqueLogger() {
        assertEquals(logger, LoggerImpl.getInstance());
    }
}