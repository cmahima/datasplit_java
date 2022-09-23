package org.datasplit.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.datasplit.domain.S3Credentials;
import org.datasplit.util.ArgumentParser;

public class S3CredentialsProvider implements AWSCredentialsProvider {
    public BasicAWSCredentials awsCreds;
    public AmazonS3 s3Client;
    public S3CredentialsProvider(S3Credentials credentials){
         this.awsCreds = new BasicAWSCredentials(credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey());
         this.s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.CA_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(this.awsCreds))
                .build();
    }

    @Override
    public AWSCredentials getCredentials() {
        return null;
    }

    @Override
    public void refresh() {

    }
}
