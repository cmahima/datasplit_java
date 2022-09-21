package org.datasplit.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;

public class S3FileReadUtil {
    public static S3Object readInputFile(String bucketName, String objectKey, AmazonS3 s3Client){
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, objectKey));

        return object;
    }
}
