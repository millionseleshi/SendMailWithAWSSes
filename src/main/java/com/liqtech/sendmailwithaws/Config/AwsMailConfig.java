/*
 * Copyright (c) 2019. MILLION SELESHI
 * License: MIT
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *  .
 *  The above copyright notice and this permission notice shall be included
 *  in all copies or substantial portions of the Software.
 *  .
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 *  OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 *  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 *  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 *  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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

@Configuration public class AwsMailConfig {

	private CustomPropertyConfig customPropertyConfig;

	public AwsMailConfig(final CustomPropertyConfig customPropertyConfig) {
		this.customPropertyConfig = customPropertyConfig;
	}

	//Explicitly load credentials from custom config file
	@Bean public AmazonSimpleEmailService amazonSimpleEmailService() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(customPropertyConfig.awsAccessKey,
				customPropertyConfig.awsSecretKey);
		return AmazonSimpleEmailServiceClientBuilder.standard().
				withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.EU_WEST_1)
				.build();
	}

	@Bean public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
	}
}
