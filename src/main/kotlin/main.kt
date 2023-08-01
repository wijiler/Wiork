import Rendering.*
import org.joml.Vector2i
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL


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
            -2,3,2
        ),
        c2MA(hex2rgb("#4E12EB"), hex2rgb("#FF12EB"),hex2rgb("#4E12EB"),hex2rgb("#4E12EB"))
    )
    val renderer = Renderer3D(mesh)
    renderer.init()
    while(!glfwWindowShouldClose(win.window)) {
        GL.createCapabilities()
        renderer.draw()
        glfwSwapBuffers(win.window)
        glfwPollEvents()

    }
}