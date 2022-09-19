package org.datasplit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DataSplitUtil {
    private ArgumentParser argumentParser;
    private Logger logger = LoggerFactory.getLogger(DataSplitUtil.class);
    private List<String> trainFileContent = new ArrayList<>();
    private List<String> testFileContent = new ArrayList<>();

    public DataSplitUtil(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
        splitData();
        writeData();
    }

    private void writeData() {
        try {
            Files.write(Paths.get(argumentParser.getTrainFileLocation()), trainFileContent);
            Files.write(Paths.get(argumentParser.getTestFileLocation()), testFileContent);
        } catch (IOException io) {
            logger.error("Could not write data to given locations", io);
        }
    }

    private void splitData() {
        try {
            trainFileContent = Files.readAllLines(Paths.get(argumentParser.getFileLocation()));
            int testFileLength = (int) (trainFileContent.size() * argumentParser.getTestTrainSplitPrcnt());
            testFileLength = trainFileContent.size() - testFileLength;

            while (testFileContent.size() < testFileLength) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, trainFileContent.size());
                String dataRow = trainFileContent.get(randomIndex);
                testFileContent.add(dataRow);
                trainFileContent.remove(randomIndex);
            }
        } catch (IOException io) {
            logger.error("Could not read input file. file name = " + argumentParser.getFileLocation(), io);
        }
    }
}
