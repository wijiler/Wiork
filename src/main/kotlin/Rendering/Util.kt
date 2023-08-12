package Rendering

import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL46.*
import org.lwjgl.system.MemoryUtil
import java.io.Serializable
import java.nio.FloatBuffer
import java.nio.IntBuffer


class Vertex(val position: Vector3f,val uvCoord : Vector2f): Serializable


open class Mesh(val vertices: Array<Vertex>, val indices: IntArray, val color: Array<Float>, val texture:Material) {
    var vAO = 0
        private set
    var pBO = 0
        private set
    var iBO = 0
        private set
    var cBO = 0
        private set
    var tBO = 0
    fun create() {
        vAO = glGenVertexArrays()
        glBindVertexArray(vAO)
        val positionBuffer = MemoryUtil.memAllocFloat(vertices.size * 3)
        val positionData = FloatArray(vertices.size * 3)
        for (i in vertices.indices) {
            positionData[i * 3] = vertices[i].position.x
            positionData[i * 3 + 1] = vertices[i].position.y
            positionData[i * 3 + 2] = vertices[i].position.z
        }
        positionBuffer.put(positionData).flip()
        pBO = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, pBO)
        glBufferData(GL_ARRAY_BUFFER, positionBuffer, GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
        glVertexAttribPointer(1,4, GL_FLOAT,false,0,0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        val indicesBuffer = MemoryUtil.memAllocInt(indices.size)
        indicesBuffer.put(indices).flip()
        iBO = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iBO)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW)
        val colorBuf = BufferUtils.createFloatBuffer(16)
        for(i in color) {
            colorBuf.put(i)
        }
        colorBuf.flip()
        cBO = storeData(colorBuf,1,4)
        val textureBuffer = MemoryUtil.memAllocFloat(vertices.size * 2)
        val textureData = FloatArray(vertices.size * 2)
        for (i in 0 until vertices.size) {
            textureData[i * 2] = vertices[i].uvCoord.x
            textureData[i * 2 + 1] = vertices[i].uvCoord.y
        }
        textureBuffer.put(textureData).flip()
        tBO = storeData(textureBuffer,2,2)
        texture.load()
    }
    // overloads
    private fun storeData(buffer: FloatBuffer, index: Int, size: Int): Int {
        val bufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        return bufferID
    }
    private fun storetData(buffer: FloatBuffer, index: Int, size: Int): Int {
        val bufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
        glVertexAttribPointer(index, size, GL_FLOAT, false, 32, 24)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        return bufferID
    }
    private fun storeData(buffer: IntBuffer, index: Int, size: Int): Int {
        val bufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        return bufferID
    }
    private fun storeData(buffer: Int, index: Int, size: Int): Int {
        val bufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
        val buffert = BufferUtils.createIntBuffer(1)
        buffert.put(buffer)
        buffert.flip()
        glBufferData(GL_ARRAY_BUFFER, buffert, GL_STATIC_DRAW)
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        return bufferID
    }
}
fun ArrayToFBuffer(arr : Array<Float>): FloatBuffer {
    val buff = BufferUtils.createFloatBuffer(arr.size)
    for(f in arr) {
        buff.put(f)
    }
    return buff
}
private fun rgb2decimal (rgba: Vector4f): Vector4f {
        return Vector4f(rgba.x / 255,rgba.y / 255,rgba.z / 255,rgba.w / 255)
}
fun hex2rgb(hex:String): Vector4f {
    var t : String = hex.drop(1)
    var a : List<String> = t.chunked(1)
    var nums : IntBuffer = BufferUtils.createIntBuffer(8)
    for (c in a) {
        when(c) {
            "1" -> nums.put(1)
            "2" -> nums.put(2)
            "3" -> nums.put(3)
            "4" -> nums.put(4)
            "5" -> nums.put(5)
            "6" -> nums.put(6)
            "7" -> nums.put(7)
            "8" -> nums.put(8)
            "9" -> nums.put(9)
            "0" -> nums.put(0)
            "A" -> nums.put(10)
            "B" -> nums.put(11)
            "C" -> nums.put(12)
            "D" -> nums.put(13)
            "E" -> nums.put(14)
            "F" -> nums.put(15)
        }
    }
    return rgb2decimal(Vector4f(((nums[0] * 16) + nums[1]).toFloat(),((nums[2] * 16) + nums[3]).toFloat(),((nums[4] * 16) + nums[5]).toFloat(),((nums[6] * 16) + nums[7]).toFloat()))
}
fun c2MA (a : Vector4f,b : Vector4f, c : Vector4f,d : Vector4f?) : Array<Float> {
    var carr = arrayOfNulls<Float>(16)
    carr.fill(0f)
    carr[0] = a.x; carr[1] = a.y; carr[2] = a.z; carr[3] = a.w; carr[4] = b.x; carr[5] = b.y; carr[6] = b.z; carr[7] = b.w; carr[8] = c.x; carr[9] = c.y; carr[10] = c.z; carr[11] = c.w; carr[12] = d?.x; carr[13] = d?.y; carr[14] = d?.z; carr[15] = d?.w;
    return carr as Array<Float>;
}
