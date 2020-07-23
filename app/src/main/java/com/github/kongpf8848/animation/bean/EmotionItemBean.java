package com.github.kongpf8848.animation.bean;

import android.support.annotation.Nullable;

public class EmotionItemBean extends EmojiCell {

    private int id;
    private String mUrl;


    public EmotionItemBean(String name,String file, int id,String url) {
        super(name,file);
        this.id=id;
        this.mUrl=url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(this==obj){
            return true;
        }
        else if(obj!=null && this.getClass()==obj.getClass()){
            EmotionItemBean other=(EmotionItemBean)obj;
            return this.id==other.id;
        }
        return false;
    }

}
