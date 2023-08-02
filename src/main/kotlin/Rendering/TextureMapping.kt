package Rendering

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.glfwGetFramebufferSize
import org.lwjgl.opengl.GL46.*
import org.lwjgl.stb.STBImage
import org.lwjgl.stb.STBImageWrite
import java.nio.ByteBuffer
import java.nio.IntBuffer


object TextureMapping {
    val width = IntArray(1)
    val height = IntArray(1)
    fun loadTexturefromPng(ImagePath : String) : ByteBuffer? {
        val imageFile = ImagePath
        val width = IntArray(1)
        val height = IntArray(1)
        val channels = IntArray(1)
        var image: ByteBuffer? = STBImage.stbi_load(imageFile, width, height, channels, STBImage.STBI_rgb_alpha)
        if (image == null) {
            System.err.println("Couldnt load image: " + ImagePath)
            return null
        }

        return image
    }
    fun bindTexture(image : ByteBuffer?, Repeat: Int,Filter: Int) {
        var texture : Int = glGenTextures()
        glBindTexture(GL_TEXTURE_2D,texture)
        glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,width[0],height[0],0, GL_RGBA, GL_UNSIGNED_BYTE,image)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,Repeat)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,Repeat)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,Filter)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,Filter)
        glGenerateMipmap(GL_TEXTURE_2D)
    }
}