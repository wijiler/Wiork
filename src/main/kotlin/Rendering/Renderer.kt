package Rendering

import Memory
import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.BufferUtils
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

open class Renderer {
    val vertsize = Memory.sizeOf(Vertex(Vector3f(),Vector2f()))
    var vao = BufferUtils.createIntBuffer(1)
    var vbo = BufferUtils.createIntBuffer(1)
    protected var fshader : Int = 0
    protected var vshader : Int = 0
    // shader program
    var shader : Int = 0
    open fun init() {}
    open fun draw() {}
    open fun free() {}
}

fun readShader(path: String, encoding: Charset): String {
    val encoded = Files.readAllBytes(Paths.get(path))
    return String(encoded, encoding)
}