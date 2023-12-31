package Rendering
import org.lwjgl.opengl.GL46.*
import java.nio.charset.Charset

class DefaultRenderer(Mesh : Mesh) : Renderer() {
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
    fun wireframe(on:Boolean) {
        if(on) {
            glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            glPolygonMode(GL_FRONT, GL_LINE);
            glPolygonMode(GL_BACK, GL_LINE);
            mesh.texture.whiteMat()
        }
        else {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)
            mesh.texture.load()
        }
    }
    override fun draw() {
            glBindVertexArray(mesh.vAO);
            glEnableVertexAttribArray(0)
            glEnableVertexAttribArray(1)
            glEnableVertexAttribArray(2)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.iBO);
            glUseProgram(shader)
            glActiveTexture(GL_TEXTURE0)
            glDrawElements(GL_TRIANGLES, mesh.indices.count(), GL_UNSIGNED_INT, 0);
            glUseProgram(0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1)
            glDisableVertexAttribArray(2)
            glBindVertexArray(0);
    }
}