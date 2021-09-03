package org.telegram.messenger;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;

public class AndroidUtilities {

    private static float density = 1;
    private static int prevOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    private static volatile Handler applicationHandler;
    public static DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    public static float screenRefreshRate = 60;

    public static void init(Application application){
        density= application.getApplicationContext().getResources().getDisplayMetrics().density;
        applicationHandler = new Handler(application.getApplicationContext().getMainLooper());
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static int dp2(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.floor(density * value);
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            applicationHandler.post(runnable);
        } else {
            applicationHandler.postDelayed(runnable, delay);
        }
    }

    public static void cancelRunOnUIThread(Runnable runnable) {
       applicationHandler.removeCallbacks(runnable);
    }



}
