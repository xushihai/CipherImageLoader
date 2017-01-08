package com.common.cipher;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.crypto.CipherInputStream;

/**
 * 作者：徐仕海 on 2016/7/25 0025 14:47
 * <p> 加密图片磁盘缓存
 * 邮箱：1056483075@qq.com
 */

public class CipherUnlimitedDiskCache extends UnlimitedDiskCache {
    CipherFilter cipherFilter;

    public CipherUnlimitedDiskCache(File cacheDir, CipherFilter cipherFilter) {
        super(cacheDir);
        this.cipherFilter = cipherFilter;
    }

    public CipherUnlimitedDiskCache(File cacheDir, File reserveCacheDir, CipherFilter cipherFilter) {
        super(cacheDir, reserveCacheDir);
        this.cipherFilter = cipherFilter;
    }

    public CipherUnlimitedDiskCache(File cacheDir, File reserveCacheDir, FileNameGenerator fileNameGenerator, CipherFilter cipherFilter) {
        super(cacheDir, reserveCacheDir, fileNameGenerator);
        this.cipherFilter = cipherFilter;
    }

    @Override
    public boolean save(String imageUri, InputStream imageStream, IoUtils.CopyListener listener) throws IOException {
        InputStream inputStream;
        /**
         * 本地文件的缓存就不加密了
         */
        if (cipherFilter.continueCipher(imageUri)) {
            try {
                inputStream = new CipherInputStream(imageStream, cipherFilter.getEncryptCipher());
            } catch (Exception e) {
                imageStream.close();
                e.printStackTrace();
                return false;
            }
        } else {
            inputStream = imageStream;
        }
        return super.save(imageUri, inputStream, listener);
    }
}
