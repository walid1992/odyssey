package com.walid.martian.utils;

import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Author   : walid
 * Data     : 2016-10-19  16:38
 * Describe : RAS非对称加密
 */
public class RSAUtil {

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    private static final String FAC_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiNI7OgbxjPOwMYlKrydSRwRowQuG9I5ePQhyM\n" +
            "jUY+ZO30SHTVKWdI4k4NxLhklr430xG2RGNJssdHQbpNwOV/DiClfBGwDm0hzqamUvCuJUrZB6BV\n" +
            "nVPR7l3yqyFFFYLaNjEQ2GToof90kRb1vWJBBZWP6WT4G86DosX0ALbzBQIDAQAB";

    public static String encryptByPublicKey(String source) {
        return encryptByPublicKey(source, PUBLIC_KEY);
    }

    public static String encryptByPublicKey(String source, String publicKey) {
        try {
            byte[] keyBytes = Base64.decode(publicKey, 0);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(FAC_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            byte[] sourceData = source.getBytes();
            int inputLen = sourceData.length;
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(sourceData, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(sourceData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return Base64.encodeToString(encryptedData, 0);
        } catch (Exception e) {
            throw new RuntimeException("rsa encrypt error", e);
        }
    }

}
