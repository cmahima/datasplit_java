package org.datasplit.util;

public class ArgumentParser {
    private final String FILE_LOC_ARG = "--file.location";
    private final String TRAIN_TST_ARG = "--train.test.percentage";
    private final String DEBUG_ARG = "--debug.logging.enabled";
    private final String TRAIN_FILE_LOC = "--train.output.location";
    private final String TEST_FILE_LOC = "--test.output.location";
    private final String S3_BUCKET_NAME = "--aws.s3.bucket.name";
    private final String S3_INPUT_FILENAME = "--aws.s3.input.file.name";
    private final String AWS_ACCESS_KEY_ID = "--aws.access.key.id";
    private final String AWS_S3_SECRETS_KEY = "--aws.s3.secrets.key";

    private String s3BucketName;
    private String s3InputFileName;
    private String trainFileLocation;
    private String testFileLocation;
    private String fileLocation;
    private Float testTrainSplitPrcnt;
    private Boolean debugLoggingEnabled;
    private transient String awsAccessKeyId;
    private transient String awsS3SecretsKey;

    public boolean isAWSEnabled() {
        return (awsAccessKeyId != null && awsS3SecretsKey != null);
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    public String getAwsS3SecretsKey() {
        return awsS3SecretsKey;
    }

    public void setAwsS3SecretsKey(String awsS3SecretsKey) {
        this.awsS3SecretsKey = awsS3SecretsKey;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }

    public String getS3InputFileName() {
        return s3InputFileName;
    }

    public void setS3InputFileName(String s3InputFileName) {
        this.s3InputFileName = s3InputFileName;
    }

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

            // if aws support is enabled, set trainFileLocation and testFileLocation to tmp folder.
            // if aws support is enabled, set input file to tmp file read from s3.
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
