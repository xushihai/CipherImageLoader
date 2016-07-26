package com.common.cipher;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 作者：徐仕海 on 2016/7/26 0026 10:18
 * <p>主要提供加密密钥，加解密算法，加密Cipher对象，解密Cipher对象
 * 邮箱：1056483075@qq.com
 */
public class CipherImage {
    private static CipherImage ourInstance = new Builder().build();

    public static CipherImage getInstance() {
        return ourInstance;
    }

    private CipherImage() {}

    /**
     * 加解密图片的密钥
     */
    private String key;

    /**
     * 加解密算法
     */
    private String algorithm;

    /**
     * 获取解密Cipher
     *
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public Cipher getDecryptCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] str = key.getBytes();
        Key key = new SecretKeySpec(str, 0, str.length, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }

    /**
     * 获取加密Cipher
     *
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public Cipher getEncryptCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] str = key.getBytes();
        Key key = new SecretKeySpec(str, 0, str.length, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher;
    }


    /**
     * 构造器
     */
    public static class Builder {
        private String key, algorithm;

        public Builder() {
            key = "123456";//默认密钥
            algorithm = "RC4";//默认加解密算法
        }

        public Builder cipherKey(String key) {
            this.key = key;
            return this;
        }

        public Builder cipherAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public CipherImage build() {
            if (ourInstance == null) {
                ourInstance = new CipherImage();
            }
            ourInstance.algorithm = algorithm;
            ourInstance.key = key;
            return ourInstance;
        }
    }
}
