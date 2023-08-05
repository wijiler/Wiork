package Rendering

import org.lwjgl.BufferUtils

class Material(ImagePath: String) {
    var w : Int = 0; var h : Int = 0;var channels : Int = 0;
    private var texture = BufferUtils.createFloatBuffer(2)

    fun load() {

    }
}