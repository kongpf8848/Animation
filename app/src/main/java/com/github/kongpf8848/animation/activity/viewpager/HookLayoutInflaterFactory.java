/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kongpf8848.animation.activity.viewpager;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * 通用的LayoutInflaterFactory
 */

public class HookLayoutInflaterFactory implements LayoutInflater.Factory2 {

    private static final String TAG = "HookLayoutInflaterFacto";

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };
    private static final HashMap<String, String> sSuccessClassNamePrefixMap = new HashMap<>();
    private WeakReference<Activity> mActivityWeakReference;
    private LayoutInflater mOriginLayoutInflater;
    private HookLayoutCallback mCallback;

    public HookLayoutInflaterFactory(Activity activity, LayoutInflater originLayoutInflater, HookLayoutCallback callback) {
        mActivityWeakReference = new WeakReference<>(activity);
        mOriginLayoutInflater = originLayoutInflater;
        mCallback=callback;

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Activity activity = mActivityWeakReference.get();
        View view = null;
        if(activity instanceof AppCompatActivity){
            view = ((AppCompatActivity)activity).getDelegate().createView(parent, name, context, attrs);
        }

        if(view == null){
            try{
                if (!name.contains(".")) {
                    if(sSuccessClassNamePrefixMap.containsKey(name)){
                        view = mOriginLayoutInflater.createView(name, sSuccessClassNamePrefixMap.get(name), attrs);
                    }else{
                        for (String prefix : sClassPrefixList) {
                            view = mOriginLayoutInflater.createView(name, prefix, attrs);
                            if (view != null) {
                                sSuccessClassNamePrefixMap.put(name, prefix);
                                break;
                            }
                        }
                    }
                }else{
                    view = mOriginLayoutInflater.cloneInContext(context).createView(name, null, attrs);
                }
            }catch (ClassNotFoundException ignore){

            }catch (Exception e){
                Log.e(TAG, "Failed to inflate view " + name + ",error: " + e.getMessage());
            }
        }

        if(view!=null) {
            if (mCallback != null) {
                mCallback.onCreateViewCallback(parent, name, context, attrs,view);
            }
        }

        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

}
