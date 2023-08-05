import Rendering.*
import org.joml.Vector2i
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.GL_LINEAR
import org.lwjgl.opengl.GL11.GL_REPEAT


fun main () {
    val win = Window("WiorkTestApp",480,600)
    val WH = Vector2i(win.w,win.h)
    win.run()
    val mesh = Mesh(
        arrayOf(
            Vertex(Vector3f(-0.5f, 0.5f, 0.0f)),
            Vertex(Vector3f(-0.5f, -0.5f, 0.0f)),
            Vertex(Vector3f(0.5f, -0.5f, 0.0f)),
            Vertex(Vector3f(0.5f, 0.5f, 0.0f))
        ), intArrayOf(
            0,1,2,
            0,3,2
        ),
        c2MA(hex2rgb("#FF0000"), hex2rgb("#00FF00"),hex2rgb("#0000FF"),hex2rgb("#FFFFFFFF")),
    )
    val renderer = DefaultRenderer(mesh)
    renderer.init()
    while(!glfwWindowShouldClose(win.window)) {
       // GL.createCapabilities()
        renderer.draw()
        glfwSwapBuffers(win.window)
        glfwPollEvents()

    }
}