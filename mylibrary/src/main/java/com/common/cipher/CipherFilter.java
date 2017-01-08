package com.common.cipher;

import javax.crypto.Cipher;

/**
 * 作者：徐仕海 on 2017/1/8 0008 09:24
 * <p>
 * 邮箱：1056483075@qq.com
 */

public interface CipherFilter {
    /**
     * 是否加密或者解密，用于对指定的uri的图片选择是否加密存储，不加密存储返回false
     * @param imageUri
     * @return
     */
    boolean continueCipher(String imageUri);

    /**
     * 如果要加密存储的时候返回加密工具
     * @return
     */
    Cipher getDecryptCipher();
    /**
     * 如果要解密显示的时候返回解密工具
     * @return
     */
    Cipher getEncryptCipher();
}
