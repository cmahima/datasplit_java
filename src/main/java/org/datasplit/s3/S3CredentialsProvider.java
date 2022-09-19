package org.datasplit.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class S3CredentialsProvider implements AWSCredentialsProvider {

    public S3CredentialsProvider(){


    }

    @Override
    public AWSCredentials getCredentials() {
        return null;
    }

    @Override
    public void refresh() {

    }
}
