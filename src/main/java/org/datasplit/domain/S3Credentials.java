package org.datasplit.domain;

import com.amazonaws.auth.AWSCredentials;
import org.datasplit.util.ArgumentParser;

public class S3Credentials implements AWSCredentials {
    private transient String accessKeyId;
    private transient String secretsKey;

    public S3Credentials(ArgumentParser argumentParser){
        this.accessKeyId = argumentParser.getAwsAccessKeyId();
        this.secretsKey = argumentParser.getAwsS3SecretsKey();
    }

    @Override
    public String getAWSAccessKeyId() {
        return accessKeyId;
    }

    @Override
    public String getAWSSecretKey() {
        return secretsKey;
    }
}
