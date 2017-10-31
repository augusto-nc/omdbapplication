package com.example.jabra.omdbapplication;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by jabra on 30/10/2017.
 */

public class MyApplication extends Application {

    private static MyApplication  instance;
    public MyApplication()
    {
        instance = this;
    }
    ImageLoaderConfiguration config;
    @Override
    public void onCreate() {
        super.onCreate();


        File cacheDir = StorageUtils.getCacheDirectory(this);
        DiskCache disk= new UnlimitedDiskCache(cacheDir);
        MemoryCache memoryCache= new LruMemoryCache(2 * 1024 * 1024);
        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        config = new ImageLoaderConfiguration.Builder(this)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(disk)
                .defaultDisplayImageOptions(opts)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .threadPoolSize(5)
                .memoryCache(memoryCache)
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(200)
                .build();
        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.init(config);

    }

    public static DisplayImageOptions.Builder getDisplayImageOptions(int r){
        DisplayImageOptions.Builder builder=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true);
        if(r==0) {
            return builder;
        }
        return builder.showImageForEmptyUri(r).showImageOnFail(r).showImageOnLoading(r);
    }

}
