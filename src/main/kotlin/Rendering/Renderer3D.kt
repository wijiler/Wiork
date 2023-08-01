package Rendering
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL46.*
import org.lwjgl.opengl.GL
import java.nio.IntBuffer
import java.nio.charset.Charset

class Renderer3D(Mesh : Mesh) : Renderer() {
    var mesh = Mesh
    override fun init() {
        mesh.create()
        shaderInit()
        glGenVertexArrays(vao)
        glBindVertexArray(vao[0])
        glGenBuffers(vbo)
        glBindBuffer(GL_ARRAY_BUFFER,vbo[0])
        println("Using Wiork 1.0.0")
    }
    // initialize and attach the shaders
    private fun shaderInit() {
        vshader = glCreateShader(GL_VERTEX_SHADER)
        fshader = glCreateShader(GL_FRAGMENT_SHADER)
        glShaderSource(vshader,readShader("./res/simpleVert.glsl", Charset.defaultCharset()))
        glShaderSource(fshader, readShader("./res/simpleFrag.glsl", Charset.defaultCharset()))
        glCompileShader(vshader)
        if(glGetShaderi(vshader, GL_COMPILE_STATUS) == GL_FALSE) {
                println("Failed to compile Vertex shader with Error: " + glGetShaderInfoLog(vshader))
        }
        glCompileShader(fshader)
        if (glGetShaderi(fshader, GL_COMPILE_STATUS) == GL_FALSE) {
            println("Failed to compile Fragmentation shader with Error: " + glGetShaderInfoLog(fshader))
        }
        shader = glCreateProgram()

        glAttachShader(shader,vshader)
        glAttachShader(shader,fshader)

        glLinkProgram(shader)
        if(glGetProgrami(shader, GL_LINK_STATUS) == GL_FALSE) {
            println("Failed to link shader with error: " + glGetProgramInfoLog(shader))
            glDeleteShader(vshader)
            glDeleteShader(fshader)
            glDeleteProgram(shader)
        }
        glValidateProgram(shader)


        println("Shader Initilization Succesful")
    }
    private fun sb (bind : Boolean) {
                if(bind) {
                    glUseProgram(shader)
                }
                else if(!bind) {
                    glUseProgram(0)
                }
    }
    override fun draw() {
            glBindVertexArray(mesh.vAO);
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.iBO);
            glUseProgram(shader)
            glDrawElements(GL_TRIANGLES, mesh.indices.count(), GL_UNSIGNED_INT, 0);
            glUseProgram(0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1)
            glBindVertexArray(0);
    }
}