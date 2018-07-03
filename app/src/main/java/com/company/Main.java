///*
// * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License").
// * You may not use this file except in compliance with the License.
// * A copy of the License is located at
// *
// *  http://aws.amazon.com/apache2.0
// *
// * or in the "license" file accompanying this file. This file is distributed
// * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
// * express or implied. See the License for the specific language governing
// * permissions and limitations under the License.
// */
//package com.company;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.util.List;
//import java.util.UUID;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.Protocol;
//import com.amazonaws.auth.*;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Builder;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.*;
//
///**
// * This sample demonstrates how to make basic requests to Amazon S3 using the
// * AWS SDK for Java.
// * <p>
// * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
// * account, and be signed up to use Amazon S3. For more information on Amazon
// * S3, see http://aws.amazon.com/s3.
// * <p>
// * Fill in your AWS access credentials in the provided credentials file
// * template, and be sure to move the file to the default location
// * (~/.aws/credentials) where the sample code will load the credentials from.
// * <p>
// * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
// * the credentials file in your source directory.
// *
// * http://aws.amazon.com/security-credentials
// */
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//
//        AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder.standard();
//        AwsClientBuilder.EndpointConfiguration endpointConfiguration =new AwsClientBuilder.EndpointConfiguration("s3.bestcloud.cn", "");
//
//        AWSCredentials credentials = new BasicAWSCredentials("155ZJO8ICW41YWAPSC59","D5bCKulbeiNVncPT6lWTLwl26gnml2R51QVPrplC");
//
//        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
//
//
//        ClientConfiguration clientConfiguration = new ClientConfiguration();
//        clientConfiguration.setProtocol(Protocol.HTTP);
//        clientConfiguration.setUseTcpKeepAlive(false);
//        clientConfiguration.setMaxErrorRetry(0);
//        SignerFactory.registerSigner("AWS2Signer",AWS2Signer.class);
//
//        //clientConfiguration.setSignerOverride(SignerFactory.VERSION_THREE_SIGNER);
//        clientConfiguration.setSignerOverride("AWS2Signer");
//
//        amazonS3ClientBuilder.withCredentials(credentialsProvider)
//                .withEndpointConfiguration(endpointConfiguration)
//                .withClientConfiguration(clientConfiguration);
//
//        amazonS3ClientBuilder.enablePathStyleAccess();
//
//
//        final AmazonS3 s3 = amazonS3ClientBuilder.build();
//
//        System.out.println("===========================================");
//        System.out.println("Getting Started with Amazon S3");
//        System.out.println("===========================================\n");
//
//        try {
//
//            System.out.println("Your Amazon S3 buckets are:");
//            List<Bucket> buckets = s3.listBuckets();
//            for(Bucket b : buckets){
//                System.out.println("bucketName:"+b.getName());
//            }
//
//
//            String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
//            s3.createBucket(bucketName);
//
//            System.out.println("Your Amazon S3 buckets are:");
//            List<Bucket>  buckets2 = s3.listBuckets();
//            for(Bucket b : buckets2){
//                System.out.println("bucketName:"+b.getName());
//            }
//
//            s3.deleteBucket(bucketName);
//
////            GetObjectRequest gor=new GetObjectRequest("bucket","22222");
////            S3Object obj = s3.getObject(gor);
////            System.out.print("obj===>"+obj.getKey());
//
//        } catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which means your request made it "
//                    + "to Amazon S3, but was rejected with an error response for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with S3, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        }
//    }
//
//
//
//}
