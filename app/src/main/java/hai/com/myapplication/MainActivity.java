package hai.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import com.common.cipher.CipherImage;
import com.common.cipher.CipherImageDecoder;
import com.common.cipher.CipherUnlimitedDiskCache;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
        ImageLoaderConfiguration configuration = null;
        configuration = new ImageLoaderConfiguration.Builder(this)
                .imageDecoder(new CipherImageDecoder(true))
                .diskCache(new CipherUnlimitedDiskCache(getExternalCacheDir()))
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageLoader.getInstance().displayImage("http://desk.fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/0A/05/ChMkJleMN-qIK73_AAQQ4IHcUNEAATmcgGtyq8ABBD4867.jpg", imageView);
    }
}
