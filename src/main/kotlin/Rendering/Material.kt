package Rendering

import org.lwjgl.BufferUtils
import org.lwjgl.stb.STBImage.*
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL46.*
import java.nio.FloatBuffer

class Material(ImagePath: String) {

    var w = BufferUtils.createIntBuffer(1); var h = BufferUtils.createIntBuffer(1);var channels = BufferUtils.createIntBuffer(1);
    var texture = 0
        private set
    private val imgp = ImagePath
    fun load() {

        texture = glGenTextures()
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D,texture)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        stbi_set_flip_vertically_on_load(true)

        var data : ByteBuffer? = stbi_load(imgp,w,h,channels,0)
        if(data == null) {
            throw Exception("Could not load image: $imgp")
        }
        else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w[0], h[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, data)
            glGenerateMipmap(GL_TEXTURE_2D)
        }
        stbi_image_free(data)
    }
    fun whiteMat () {
        val image = BufferUtils.createIntBuffer(3)
        image.put(255).put(255).put(255).flip()
        val wtex = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, wtex);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 1, 1, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    }
}
