//package com.company;
//
///**
// * Created by lwpc on 2017/10/11.
// */
//import static com.amazonaws.util.StringUtils.UTF8;
//
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//import java.util.UUID;
//
//import com.amazonaws.SdkClientException;
//import com.amazonaws.SignableRequest;
//import com.amazonaws.auth.*;
//import com.amazonaws.log.InternalLogApi;
//import com.amazonaws.log.InternalLogFactory;
//import com.amazonaws.util.Base64;
//import com.amazonaws.util.DateUtils;
//import com.amazonaws.util.SdkHttpUtils;
//import com.amazonaws.util.StringUtils;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
///**
// * Signer implementation that signs requests with the AWS3 signing protocol.
// */
//public class AWS2Signer extends AbstractAWSSigner {
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String NONCE_HEADER = "x-amz-nonce";
//    private static final String HTTP_SCHEME = "AWS3";
//    private static final String HTTPS_SCHEME = "AWS3-HTTPS";
//
//
//    private String overriddenDate;
//
//    @Deprecated
//    protected static final DateUtils dateUtils = new DateUtils();
//    private static final InternalLogApi log = InternalLogFactory.getLog(AWS3Signer.class);
//
//
//    /**
//     * Signs the specified request with the AWS3 signing protocol by using the
//     * AWS account credentials specified when this object was constructed and
//     * adding the required AWS3 headers to the request.
//     *
//     * @param request
//     *            The request to sign.
//     */
//    @Override
//    public void sign(SignableRequest<?> request, AWSCredentials credentials) throws SdkClientException {
//        // annonymous credentials, don't sign
//        if ( credentials instanceof AnonymousAWSCredentials) {
//            return;
//        }
//
//        AWSCredentials sanitizedCredentials = sanitizeCredentials(credentials);
//
//        SigningAlgorithm algorithm = SigningAlgorithm.HmacSHA1;
//        String nonce = UUID.randomUUID().toString();
//
//        int timeOffset = request.getTimeOffset();
//        Date dateValue = getSignatureDate(timeOffset);
//        String date = DateUtils.formatRFC822Date(dateValue);
//        boolean isHttps = false;
//
//        if (overriddenDate != null) date = overriddenDate;
//
//
//        // AWS3 HTTP requires that we sign the Host header
//        // so we have to have it in the request by the time we sign.
//        String hostHeader = request.getEndpoint().getHost();
//        if (SdkHttpUtils.isUsingNonDefaultPort(request.getEndpoint())) {
//            hostHeader += ":" + request.getEndpoint().getPort();
//        }
//
//        request.addHeader("Host", hostHeader);
//
//        request.addHeader("Date", date);
//        request.addHeader("x-amz-date", date);
//
//
//        if ( sanitizedCredentials instanceof AWSSessionCredentials ) {
//            addSessionCredentials(request, (AWSSessionCredentials) sanitizedCredentials);
//        }
//        byte[] bytesToSign;
//        String stringToSign;
//        if (isHttps) {
//            request.addHeader(NONCE_HEADER, nonce);
//            stringToSign = date + nonce;
//            bytesToSign = stringToSign.getBytes(UTF8);
//        } else {
//            String path = SdkHttpUtils.appendUri(request.getEndpoint().getPath(), request.getResourcePath());
//
//            /*
//             * AWS3 requires all query params to be listed on the third line of
//             * the string to sign, even if those query params will be sent in
//             * the request body and not as a query string. POST formatted query
//             * params should *NOT* be included in the request payload.
//             */
//            stringToSign = request.getHttpMethod().toString() + "\n"
//                    + "\n";
//
//            String contentType=request.getHeaders().get("Content-Type");
//            if(contentType!=null){
//                stringToSign+=contentType;
//            }
//
//            stringToSign+='\n';
//
//            String headerStr=getCanonicalizedHeadersForStringToSign(request) + "\n";
//            stringToSign+=headerStr;
//            String resPath=getCanonicalizedResourcePath(path);
//            stringToSign+=resPath;
//            String queryStr=getCanonicalizedQueryString(request.getParameters());
//            stringToSign+=queryStr;
//            String params=getRequestPayloadWithoutQueryParams(request);
//           // stringToSign+=params;
//
//            bytesToSign = stringToSign.getBytes();
//        }
//        request.getHeaders().remove("x-amz-date");
//        request.getHeaders().remove("amz-sdk-invocation-id");
//        request.getHeaders().remove("amz-sdk-retry");
//        request.getHeaders().remove("User-Agent");
//
//        String signature = signAndBase64Encode(bytesToSign,
//                sanitizedCredentials.getAWSSecretKey(), algorithm);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("AWS "+sanitizedCredentials.getAWSAccessKeyId() +":").append(signature);
//        request.addHeader(AUTHORIZATION_HEADER, builder.toString());
//
//    }
//
//    protected List<String> getHeadersForStringToSign(SignableRequest<?> request) {
//        List<String> headersToSign = new ArrayList<String>();
//        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
//            String key = entry.getKey();
//            String lowerCaseKey = StringUtils.lowerCase(key);
//            if (lowerCaseKey.startsWith("x-amz")) {
//                headersToSign.add(key);
//            }
//        }
//
//        Collections.sort(headersToSign);
//        return headersToSign;
//    }
//
//    /**
//     * For internal testing only - allows the date to be overridden for internal
//     * tests.
//     *
//     * @param date
//     *            The RFC822 date string to use when signing requests.
//     */
//    void overrideDate(String date) {
//        this.overriddenDate = date;
//    }
//
//    protected String getCanonicalizedHeadersForStringToSign(SignableRequest<?> request) {
//        List<String> headersToSign = getHeadersForStringToSign(request);
//
//        for (int i = 0; i < headersToSign.size(); i++) {
//            headersToSign.set(i, StringUtils.lowerCase(headersToSign.get(i)));
//        }
//
//        SortedMap<String, String> sortedHeaderMap = new TreeMap<String, String>();
//        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
//            if (headersToSign.contains(StringUtils.lowerCase(entry.getKey()))) {
//                sortedHeaderMap.put(StringUtils.lowerCase(entry.getKey()), entry.getValue());
//            }
//        }
//
//        StringBuilder builder = new StringBuilder();
//        for (Map.Entry<String, String> entry : sortedHeaderMap.entrySet()) {
//            builder.append(entry.getValue());
//        }
//
//        return builder.toString();
//    }
//
//
//    @Override
//    protected void addSessionCredentials(SignableRequest<?> request, AWSSessionCredentials credentials) {
//        request.addHeader("x-amz-security-token", credentials.getSessionToken());
//    }
//    @Override
//    protected byte[] sign(byte[] data, byte[] key, SigningAlgorithm algorithm) throws SdkClientException {
//        try {
//            Mac mac = algorithm.getMac();
//            mac.init(new SecretKeySpec(key, algorithm.toString()));
//            return mac.doFinal(data);
//        } catch (Exception var5) {
//            throw new SdkClientException("Unable to calculate a request signature: " + var5.getMessage(), var5);
//        }
//    }
//    @Override
//    protected String signAndBase64Encode(byte[] data, String key, SigningAlgorithm algorithm) throws SdkClientException {
//        try {
//            byte[] signature = this.sign(data, key.getBytes(StringUtils.UTF8), algorithm);
//            return Base64.encodeAsString(signature);
//        } catch (Exception var5) {
//            throw new SdkClientException("Unable to calculate a request signature: " + var5.getMessage(), var5);
//        }
//    }
//
//
//}