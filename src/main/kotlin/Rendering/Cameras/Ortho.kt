package Rendering.Cameras

import Rendering.Vectors
import org.joml.Vector3f
// Orthographic camera -> https://learnopengl.com/Getting-started/Camera
class Ortho {
    var pos : Vector3f = Vector3f(0f,0f,0f)
    private var cameraTarget = Vector3f(0f,0f,0f)
    var cameraDir = Vectors.normalize((Vectors.subtract(pos,cameraTarget)))
    private var up = Vector3f(0f,1f,0f)
    var camRight = Vectors.normalize(Vectors.cross_product(up,cameraDir))
    var camUp = Vectors.cross_product(cameraDir,camRight)

}