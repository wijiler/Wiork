<div align = "center">    

# Wiork - A lightweight opengl game engine   

![Wiork Logo](https://media.discordapp.net/attachments/1128403479199744092/1135975939973587047/image.png)   
**Wiork is focused on extensibility, maintainability, speed, and ease of use**
</div>   

### Here's an example program for drawing a colored square
```kotlin
import Rendering.*
import org.joml.Vector2f
import org.joml.Vector2i
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL


fun main () {
    val win = Window("WiorkTestApp",480,600)
    val WH = Vector2i(win.w,win.h)
    win.run()
    val material = Material("./res/Testingimgs/frogs.png")
    val mesh = Primitives.Rectangle(Vector2f(0.5f,0.5f),material, c2MA(hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF"),hex2rgb("#FFFFFF")))
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
### How to import?
# IntelliJ Idea:
make a folder called lib in the root directory than make another folder called Wiork, you then put the jar into the lib/Wiork/ directory   
Press ctrl + shift + alt + s then click on dependencies and add the jar as a dependency to main and the entire project
if you're using gradle to build add this as a dependency as well

# Eclipse
If you use Eclipse in 2023 thats a fucking you problem ngl
# TODO

- [x] Texture support + Sprite rendering from png
- [X] Pre defined meshes for simple operations
- [ ] Camera system 
- [ ] Font rendering
- [ ] Hot code reloading (game logic get turned into some sort of file that can be used at runtime (ex. dll) and when dll changes reload the code)
- [ ] 3d renderer + stl/obj file loading