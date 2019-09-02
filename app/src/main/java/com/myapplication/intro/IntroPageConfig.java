package com.myapplication.intro;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class IntroPageConfig {
   @StringRes
   public int titleRes;

   @DrawableRes
    public int imageRes;


    public IntroPageConfig(int titleRes, int imageRes) {
        this.titleRes = titleRes;
        this.imageRes = imageRes;

    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(int titlRes) {
        this.titleRes = titlRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
