package com.github.kongpf8848.animation.bean

class StickerItem(var id: Int, var name: String, var file: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        } else if (other != null && this.javaClass == other.javaClass) {
            val other = other as StickerItem
            return id == other.id
        }
        return false
    }

}