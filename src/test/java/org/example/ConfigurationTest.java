package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {
    private Configuration configuration;

    @BeforeEach
    void init() {
        this.configuration = Configuration.getInstance();
        configuration.setSettingFile("config.txt");
        configuration.readConfiguration();
    }

    @Test
    void testUniqueConfig() {
        assertEquals(configuration, Configuration.getInstance());
    }

    @Test
    void testAddressConfig() {
        assertEquals("127.0.0.1", configuration.getHostName());
    }

    @Test
    void testPortConfig() {
        assertEquals(8888, configuration.getPortNumber());
    }
}