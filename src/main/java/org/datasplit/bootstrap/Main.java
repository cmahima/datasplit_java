package org.datasplit.bootstrap;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.datasplit.util.ArgumentParser;
import org.datasplit.util.DataSplitUtil;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            ArgumentParser argumentParser = new ArgumentParser(args);
            configureConsoleLogging(argumentParser.getDebugLoggingEnabled());
            DataSplitUtil dataSplitUtil = new DataSplitUtil(argumentParser);
        } catch (Exception e) {

        }
    }

    public static void configureConsoleLogging(boolean debug) {
        ConsoleAppender ca = new ConsoleAppender();
        if (!debug) {
            ca.setThreshold(Level.toLevel(Priority.INFO_INT));
        } else {
            ca.setThreshold(Level.toLevel(Priority.DEBUG_INT));
        }
        ca.setLayout(new EnhancedPatternLayout("%-6d [%25.35t] %-5p %40.80c - %m%n"));
        ca.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(ca);
    }
}
