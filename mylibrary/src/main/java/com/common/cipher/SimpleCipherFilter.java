package com.common.cipher;

import javax.crypto.Cipher;

/**
 * 作者：徐仕海 on 2017/1/8 0008 09:58
 * <p>
 * 邮箱：1056483075@qq.com
 */

public class SimpleCipherFilter implements CipherFilter{
    @Override
    public boolean continueCipher(String imageUri) {
        return true;
    }

    @Override
    public Cipher getDecryptCipher() {
        return null;
    }

    @Override
    public Cipher getEncryptCipher() {
        return null;
    }
}
