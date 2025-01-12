package com.codemeet.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Configuration
public class AWSConfig {
  @Value("${aws.region}")
  private String awsRegion;

  @Value("${aws.accessKeyId}")
  private String accessKeyId;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Value("${aws.bucketName}")
  private String bucketName;

  @Bean
  public AmazonS3 createS3instance() {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);

    AmazonS3 s3Client = AmazonS3ClientBuilder
      .standard()
      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
      .withRegion(awsRegion)
      .build();

    if (!s3Client.doesBucketExistV2(bucketName)) {
      System.out.println("Bucket não existe: " + bucketName);
      throw new RuntimeException("Bucket não encontrado: " + bucketName);
    }

    return s3Client;
  }
}
