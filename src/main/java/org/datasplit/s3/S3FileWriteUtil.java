package org.datasplit.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectId;

import java.io.File;


public class S3FileWriteUtil {

    public static void writeFileToS3(String bucketName, String key, File file,  AmazonS3 s3Client) {
        s3Client.putObject(bucketName, key, file);
    }
}
