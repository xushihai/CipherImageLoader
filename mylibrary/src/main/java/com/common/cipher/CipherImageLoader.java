package com.common.cipher;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import java.io.File;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 作者：徐仕海 on 2017/1/8 0008 09:23
 * <p>
 * 邮箱：1056483075@qq.com
 */

public class CipherImageLoader {
    /**
     * 加解密图片的密钥
     */
    private String key;

    /**
     * 加解密算法
     */
    private String algorithm;
    CipherFilter cipherFilter;
    CipherFilter globalFilter = new CipherFilter() {
        @Override
        public boolean continueCipher(String imageUri) {
            if (cipherFilter != null)
                return cipherFilter.continueCipher(imageUri);
            return true;
        }

        @Override
        public Cipher getDecryptCipher() {
            if (cipherFilter == null || cipherFilter.getDecryptCipher() == null) {
                return CipherImageLoader.this.getDecryptCipher();
            }
            return cipherFilter.getDecryptCipher();
        }

        @Override
        public Cipher getEncryptCipher() {
            if (cipherFilter == null || cipherFilter.getEncryptCipher() == null) {
                return CipherImageLoader.this.getEncryptCipher();
            }
            return cipherFilter.getEncryptCipher();
        }
    };

    public CipherImageLoader(String key, String algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    public CipherFilter getCipherFilter() {
        return cipherFilter;
    }

    public CipherImageLoader setCipherFilter(CipherFilter cipherFilter) {
        this.cipherFilter = cipherFilter;
        return this;
    }

    /**
     * 获取解密Cipher
     */
    public Cipher getDecryptCipher() {
        try {
            byte[] str = key.getBytes();
            Key key = new SecretKeySpec(str, 0, str.length, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取加密Cipher
     */
    public Cipher getEncryptCipher() {
        try {
            byte[] str = key.getBytes();
            Key key = new SecretKeySpec(str, 0, str.length, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BaseImageDecoder getImageDecoder(boolean logging) {
        return new CipherImageDecoder(globalFilter, logging);
    }

    public UnlimitedDiskCache getDiskCache(File cacheDir) {
        return new CipherUnlimitedDiskCache(cacheDir, globalFilter);
    }

    public UnlimitedDiskCache getDiskCache(File cacheDir, File reserveCacheDir) {
        return new CipherUnlimitedDiskCache(cacheDir, reserveCacheDir, globalFilter);
    }

    public UnlimitedDiskCache getDiskCache(File cacheDir, File reserveCacheDir, FileNameGenerator fileNameGenerator) {
        return new CipherUnlimitedDiskCache(cacheDir, reserveCacheDir, fileNameGenerator, globalFilter);
    }
}
