package org.datasplit.bootstrap;

import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.datasplit.domain.S3Credentials;
import org.datasplit.s3.S3CredentialsProvider;
import org.datasplit.s3.S3FileReadUtil;
import org.datasplit.s3.S3FileWriteUtil;
import org.datasplit.util.ArgumentParser;
import org.datasplit.util.DataSplitUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            ArgumentParser argumentParser = new ArgumentParser(args);
            configureConsoleLogging(argumentParser.getDebugLoggingEnabled());

            if (argumentParser.isAWSEnabled()) {
                S3Credentials s3Credentials = new S3Credentials(argumentParser);
                S3CredentialsProvider credentialsProvider = new S3CredentialsProvider(s3Credentials);

                S3Object inputFileObj = S3FileReadUtil.readInputFile(argumentParser.getS3BucketName(),
                        argumentParser.getS3InputFileName(), credentialsProvider.s3Client);
                // write input file to tmp
                File file=new File(argumentParser.getFileLocation());
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    IOUtils.copy(inputFileObj.getObjectContent(), new FileOutputStream(file));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataSplitUtil dataSplitUtil = new DataSplitUtil(argumentParser);

                int sepPos = argumentParser.getS3InputFileName().lastIndexOf("/");
                String s3UploadLocationTrain = argumentParser.getS3BucketName()+argumentParser.getS3InputFileName().substring(0,sepPos)+"/train.txt";
                S3FileWriteUtil.writeFileToS3(argumentParser.getS3BucketName(), s3UploadLocationTrain, new File(argumentParser.getTrainFileLocation()),credentialsProvider.s3Client);
                String s3UploadLocationTest = argumentParser.getS3BucketName()+argumentParser.getS3InputFileName().substring(0,sepPos)+"/test.txt";
                S3FileWriteUtil.writeFileToS3(argumentParser.getS3BucketName(), s3UploadLocationTest, new File(argumentParser.getTestFileLocation()), credentialsProvider.s3Client);
            } else {
                DataSplitUtil dataSplitUtil = new DataSplitUtil(argumentParser);
            }
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
