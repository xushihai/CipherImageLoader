package hai.com.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.common.cipher.CipherImageLoader;
import com.common.cipher.SimpleCipherFilter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
        CipherImageLoader cipherImageLoader = new CipherImageLoader("52542734", "RC4").setCipherFilter(new SimpleCipherFilter() {
            @Override
            public boolean continueCipher(String imageUri) {
                Log.e("图片", imageUri);
                return true;
            }
        });
        ImageLoaderConfiguration configuration = null;
        configuration = new ImageLoaderConfiguration.Builder(this)
                .imageDecoder(cipherImageLoader.getImageDecoder(true))
                .diskCache(cipherImageLoader.getDiskCache(getCacheDir()))
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);


        /**
         * 如果是显示已经加密的图片，就不要设置缓存到磁盘，因为缓存在存盘的时候会加密，导致数据不对，只需要设置解密器
         * 如果是显示原始图片，还想保存在磁盘的磁盘是加密的
         */


        /**
         * 测试显示已经加密的图片
         */
        try {
            cipherImageLoader.getDiskCache(getCacheDir()).save("file:///storage/sdcard1/test1.jpg", new FileInputStream("/storage/sdcard1/test1.jpg"), new IoUtils.CopyListener() {
                @Override
                public boolean onBytesCopied(int i, int i1) {
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        ImageLoader.getInstance().displayImage("http://desk.fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/0A/05/ChMkJleMN-qIK73_AAQQ4IHcUNEAATmcgGtyq8ABBD4867.jpg", imageView);
        ImageLoader.getInstance().displayImage("file:///storage/sdcard1/test3.jpg", imageView);
    }
}
