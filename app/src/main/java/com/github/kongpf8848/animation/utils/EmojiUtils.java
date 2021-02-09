package com.github.kongpf8848.animation.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;

import java.io.File;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EmojiUtils {

    public static final String SUFFIX_TGS = ".tgs";
    public static final String SUFFIX_JSON = ".json";

    //是否为tgs文件
    public static boolean isTgsFormat(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(SUFFIX_TGS);
    }

    //是否为json文件
    public static boolean isJsonFromat(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(SUFFIX_JSON);
    }

    public static void loadImageFromAsset(final Context context, final String fileName, final RLottieImageView imageView, final int width, final int height) {
        try {
            if (isTgsFormat(fileName)) {
                RLottieDrawable drawable = new RLottieDrawable(context, fileName, width, height, false, null);
                imageView.setAutoRepeat(true);
                imageView.setAnimation(drawable);
                imageView.playAnimation();
                Log.d("JACK8","load tgs:"+fileName);
            }  else {
                Glide.with(context).load("file:///android_asset/"+fileName).into(imageView);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JACK8", "error:" + e.getMessage());
        }
    }


    public static void loadImageFromUrl(final Context context, final String url, final RLottieImageView imageView, final int width, final int height) {
        try {
            if (isTgsFormat(url)) {
                getRLottieDrawable(context, url, width, height).subscribe(new Consumer<RLottieDrawable>() {
                    @Override
                    public void accept(RLottieDrawable drawable) throws Exception {
                        if (drawable != null) {
                            imageView.setAutoRepeat(true);
                            imageView.setAnimation(drawable);
                            imageView.playAnimation();
                        }
                    }
                });
            }
             else {
                Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JACK8", "error:" + e.getMessage());
        }
    }

    public static Observable<RLottieDrawable> getRLottieDrawable(final Context context, final String url, final int width, final int height) {
        return Observable.just(url).flatMap(new Function<String, ObservableSource<RLottieDrawable>>() {
            @Override
            public ObservableSource<RLottieDrawable> apply(String url) {
                FutureTarget<File> target = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                try {
                    File file = target.get();
                    RLottieDrawable drawable = new RLottieDrawable(file, width, height, false, false);
                    return Observable.just(drawable);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.e("JACK8", "error:" + e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("JACK8", "error:" + e.getMessage());
                }
                return Observable.empty();

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


}
