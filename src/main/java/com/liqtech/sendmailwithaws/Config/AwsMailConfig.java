package com.liqtech.sendmailwithaws.Config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class AwsMailConfig {

  private CustomPropertyConfig customPropertyConfig;

  public AwsMailConfig(final CustomPropertyConfig customPropertyConfig) {
    this.customPropertyConfig = customPropertyConfig;
  }

  @Bean
  public AmazonSimpleEmailService amazonSimpleEmailService() {

    return AmazonSimpleEmailServiceClientBuilder.standard()
        .withCredentials(
            new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                    customPropertyConfig.awsAccessKey, customPropertyConfig.awsSecretKey)))
        .withRegion(Regions.EU_WEST_1)
        .build();
  }

  @Bean
  public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
    return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
  }
}
