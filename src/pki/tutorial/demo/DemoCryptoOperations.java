/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pki.tutorial.demo;

import pki.tutorial.core.CryptoOperations;
import pki.tutorial.core.KeyFactory;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException; 
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey; 
import pki.tutorial.certificate.CertificateFactory;
import static pki.tutorial.core.AppConsts.*;
/**
 *
 * @author mohammed Almissbah
 */
public class DemoCryptoOperations {



    public static SecretKey generateAesKey(int keyBitSize) throws NoSuchAlgorithmException {
     return KeyFactory.generateSecretKey(keyBitSize, ALG_AES);
    }

    public static KeyPair generate1024RsaKeyPair() throws NoSuchAlgorithmException {
        return KeyFactory.generateKeyPair(1024, ALG_RSA);
    }

    public static byte[] aesEncrypt(byte[] data, SecretKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return CryptoOperations.cipherEncryptMode(data, ALG_AES, key);
    }
    
    public static byte[] aesDecrypt(byte[] cipherText, SecretKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return CryptoOperations.cipherDecryptMode(cipherText, ALG_AES, key);
    }

    public static byte[] rsaEncrypt(byte[] data, PrivateKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return CryptoOperations.cipherEncryptMode(data, ALG_RSA, key);
    }

    public static byte[] rsaDecrypt(byte[] cipherText, PublicKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return CryptoOperations.cipherDecryptMode(cipherText, ALG_RSA, key);
    }

    public static byte[] generateSha256(byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return CryptoOperations.generateHash(data, ALG_SHA256);
    }

   
    public static byte[] signDataSha256WithRSA(byte[] data, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException {
        return CryptoOperations.signData(data, privateKey, ALG_SHA256_WITH_RSA);
    }

    public static boolean verifySignatureSha256WithRSA(byte[] data,
            PublicKey pubKey, byte[] digitalSignature) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        return CryptoOperations.verifySignature(data, pubKey, digitalSignature, ALG_SHA256_WITH_RSA);
    }

    public static  Certificate generateSelfSignedCertificate(KeyPair keyPair,String subjectDn) throws CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
       return new CertificateFactory().createSelfSignedCertificate(subjectDn, subjectDn, keyPair, ALG_SHA256_WITH_RSA);
    }

    public static byte[] encodeUTF8(String data) throws UnsupportedEncodingException {
        return CryptoOperations.encodeString(data,ENC_UTF_8);
    }
}
