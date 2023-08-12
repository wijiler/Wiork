import Rendering.*
import org.joml.Vector2f
import org.joml.Vector2i
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL


fun main () {
    val win = Window("WiorkTestApp",480,600,Resize = true,WireFrame = false)
    val WH = Vector2i(win.w,win.h)
    win.run()
    val material = Material("./res/Testingimgs/frogs.png")
    val mesh = Primitives.Rectangle(Vector2f(1.0f,1.0f),material, c2MA(hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF")))
    val renderer = DefaultRenderer(mesh)
    renderer.init()
    while(!glfwWindowShouldClose(win.window)) {
        GL.createCapabilities()
        win.resize()
        renderer.wireframe(win.WireFrame)
        renderer.draw()
        glfwSwapBuffers(win.window)
        glfwPollEvents()
    }
}