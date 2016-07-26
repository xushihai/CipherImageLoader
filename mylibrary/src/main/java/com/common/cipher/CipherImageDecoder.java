package com.common.cipher;

import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;

import java.io.IOException;
import java.io.InputStream;

import javax.crypto.CipherInputStream;

/**
 * 作者：徐仕海 on 2016/7/25 0025 11:20
 * <p> 解密图片解码器
 * 邮箱：1056483075@qq.com
 */

public class CipherImageDecoder extends BaseImageDecoder {
    public CipherImageDecoder(boolean loggingEnabled) {
        super(loggingEnabled);
    }

    @Override
    protected InputStream getImageStream(ImageDecodingInfo decodingInfo) throws IOException {
        InputStream imageStream = super.getImageStream(decodingInfo);
        /**
         * 只解密网络下载的图片，其他的途径的图片不用加密所以就不用解密，加解密手段用的是Java的加解密输入流
         */
        if (decodingInfo.getOriginalImageUri().startsWith("http")) {
            CipherInputStream in = null;
            try {
                in = new CipherInputStream(imageStream, CipherImage.getInstance().getDecryptCipher());
            } catch (Exception e) {
                e.printStackTrace();
                imageStream.close();
            }
            return in;
        }
        return imageStream;
    }

}
