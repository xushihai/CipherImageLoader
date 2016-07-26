# CipherImageLoader
universalimageloader的加密磁盘缓存
在需要使用图片缓存到磁盘的加密功能的时候，只需要在配置UIL的时候设置imageDecoder和diskCache，
使用我写的CipherImageDecoder和CipherUnlimitedDiskCache就可以了。

如果需要修改密钥或加密算法，使用CipherImage的Builder进行配置就可以。new CipherImage.Builder().cipherKey("111111").cipherAlgorithm("DES").build();
