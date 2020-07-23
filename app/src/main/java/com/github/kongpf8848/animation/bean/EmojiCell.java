package com.github.kongpf8848.animation.bean;

public class EmojiCell {

    protected String Name;
    protected String File;


    public EmojiCell(EmojiCell cell){
        this(cell.getName(),cell.getFile());
    }
    public EmojiCell(String name, String file) {
        this.Name = name;
        this.File = file;
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
}
