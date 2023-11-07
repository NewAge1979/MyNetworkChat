package org.example.Server.Command;

import org.example.Server.Connection;

public record CommandParameters(Connection curConnect, String message) {
}
