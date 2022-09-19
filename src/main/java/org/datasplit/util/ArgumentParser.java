package org.datasplit.util;

public class ArgumentParser {
    private final String FILE_LOC_ARG = "--file.location";
    private final String TRAIN_TST_ARG = "--train.test.percentage";
    private final String DEBUG_ARG = "--debug.logging.enabled";
    private final String TRAIN_FILE_LOC = "--train.output.location";
    private final String TEST_FILE_LOC = "--test.output.location";

    private String trainFileLocation;
    private String testFileLocation;
    private String fileLocation;
    private Float testTrainSplitPrcnt;
    private Boolean debugLoggingEnabled;

    public String getTrainFileLocation() {
        return trainFileLocation;
    }

    public void setTrainFileLocation(String trainFileLocation) {
        this.trainFileLocation = trainFileLocation;
    }

    public String getTestFileLocation() {
        return testFileLocation;
    }

    public void setTestFileLocation(String testFileLocation) {
        this.testFileLocation = testFileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Float getTestTrainSplitPrcnt() {
        return testTrainSplitPrcnt;
    }

    public void setTestTrainSplitPrcnt(Float testTrainSplitPrcnt) {
        this.testTrainSplitPrcnt = testTrainSplitPrcnt;
    }

    public Boolean getDebugLoggingEnabled() {
        return debugLoggingEnabled;
    }

    public void setDebugLoggingEnabled(Boolean debugLoggingEnabled) {
        this.debugLoggingEnabled = debugLoggingEnabled;
    }

    public ArgumentParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(FILE_LOC_ARG)) {
                this.fileLocation = args[i + 1];
                i++;
            } else if (args[i].equals(TRAIN_TST_ARG)) {
                this.testTrainSplitPrcnt = Float.parseFloat(args[i + 1]);
                i++;
            } else if (args[i].equals(DEBUG_ARG)) {
                this.debugLoggingEnabled = Boolean.parseBoolean(args[i + 1]);
                i++;
            } else if (args[i].equals(TRAIN_FILE_LOC)) {
                this.trainFileLocation = args[i + 1];
                i++;
            } else if (args[i].equals(TEST_FILE_LOC)) {
                this.testFileLocation = args[i + 1];
                i++;
            } else {
                showHelp();
                throw new IllegalArgumentException("Not enough arguments");
            }
        }
    }

    private void showHelp() {
        //print something to the command line
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("--file.location location of file to split \n");
        messageBuilder.append("--debug.logging.enabled true/false \n");
        messageBuilder.append("--train.test.percentage 0.1-0.9 \n");
        messageBuilder.append("--train.file.location location of output train file \n");
        messageBuilder.append("--test.file.location location of output test file. \n");
        messageBuilder.append("--help show this message");
        System.out.println(messageBuilder);
    }

}
