package com.github.kongpf8848.animation.bean;

import androidx.annotation.Nullable;

public class StickerItem {

    private int id;
    protected String Name;
    protected String File;

    public StickerItem(int id, String name, String file) {
        this.id=id;
        this.Name = name;
        this.File = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        this.File = file;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(this==obj){
            return true;
        }
        else if(obj!=null && this.getClass()==obj.getClass()){
            StickerItem other=(StickerItem)obj;
            return this.id==other.id;
        }
        return false;
    }

}
