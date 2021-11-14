package com.github.kongpf8848.animation.views.circleindicator;

import android.view.Gravity;
import android.widget.LinearLayout;
import androidx.annotation.AnimatorRes;
import androidx.annotation.DrawableRes;

import com.github.kongpf8848.animation.R;

public class Config {

    int width = -1;
    int height = -1;
    int margin = -1;
    @AnimatorRes int animatorResId = R.animator.scale_with_alpha;
    @AnimatorRes int animatorReverseResId = 0;
    @DrawableRes int backgroundResId = R.drawable.white_radius;
    @DrawableRes int unselectedBackgroundId;
    int orientation = LinearLayout.HORIZONTAL;
    int gravity = Gravity.CENTER;

    Config() {
    }

    public static class Builder {

        private final Config mConfig;

        public Builder() {
            mConfig = new Config();
        }

        public Config.Builder width(int width) {
            mConfig.width = width;
            return this;
        }

        public Config.Builder height(int height) {
            mConfig.height = height;
            return this;
        }

        public Config.Builder margin(int margin) {
            mConfig.margin = margin;
            return this;
        }

        public Config.Builder animator(@AnimatorRes int animatorResId) {
            mConfig.animatorResId = animatorResId;
            return this;
        }

        public Config.Builder animatorReverse(@AnimatorRes int animatorReverseResId) {
            mConfig.animatorReverseResId = animatorReverseResId;
            return this;
        }

        public Config.Builder drawable(@DrawableRes int backgroundResId) {
            mConfig.backgroundResId = backgroundResId;
            return this;
        }

        public Config.Builder drawableUnselected(@DrawableRes int unselectedBackgroundId) {
            mConfig.unselectedBackgroundId = unselectedBackgroundId;
            return this;
        }

        public Config.Builder orientation(int orientation) {
            mConfig.orientation = orientation;
            return this;
        }

        public Config.Builder gravity(int gravity) {
            mConfig.gravity = gravity;
            return this;
        }

        public Config build() {
            return mConfig;
        }
    }
}
