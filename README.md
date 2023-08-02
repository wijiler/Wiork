<div align = "center">    

# Wiork - A lightweight opengl game engine   

![Wiork Logo](https://media.discordapp.net/attachments/1128403479199744092/1135975939973587047/image.png)   
**Wiork is focused on extensibility, maintainability, speed, and ease of use**
</div>   

### Here's an example program for drawing a colored square
```kotlin
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
            0,3,2
        ),
        c2MA(hex2rgb("#4E12EB"), hex2rgb("#FF12EB"),hex2rgb("#4E12EB"),hex2rgb("#4E12EB"))
    )
    val renderer = DefaultRenderer(mesh)
    renderer.init()
    while(!glfwWindowShouldClose(win.window)) {
        GL.createCapabilities()
        renderer.draw()
        glfwSwapBuffers(win.window)
        glfwPollEvents()

    }
}
```

### Extensibility features in wiork

- Completely Customisable Renderer
- Editable Source code
- Access to the main VAO and VBO
- Custom vert and frag shaders

# TODO
? = being worked on, X = Not implemented, ✅ = completed

- Texture support + Sprite rendering from png - [?]
- Pre defined meshes for simple operations - [?]
- Camera system - [X]
- 3d renderer + stl/obj file loading [X]