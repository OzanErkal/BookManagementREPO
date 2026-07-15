package com.example.SpringBootDemo2;

import java.util.logging.Logger;


public class Logging {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Application started");
        LOGGER.severe("This is a severe error message");
    }
}
