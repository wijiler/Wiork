package Rendering

import org.joml.Vector2f
import org.joml.Vector3f

object Primitives {
    class Rectangle(var Size:Vector2f,Texture:Material,Color : Array<Float>) : Mesh(arrayOf(
        Vertex(Vector3f(-Size.x, Size.y, 0.0f), Vector2f(1.0f,1.0f)),
        Vertex(Vector3f(-Size.x, -Size.y, 0.0f),Vector2f(1.0f,0.0f)),
        Vertex(Vector3f(Size.x, -Size.x, 0.0f), Vector2f(0.0f,0.0f)),
        Vertex(Vector3f(Size.x, Size.y, 0.0f), Vector2f(0.0f,1.0f))
    ), intArrayOf(
        0,1,2,
        0,3,2
    ),
        Color,
        Texture)
    class Triangle(var Points:Vector2f,Texture:Material,Color : Array<Float>) : Mesh(arrayOf(
        Vertex(Vector3f(-Points.x, Points.y, 0.0f), Vector2f(1.0f,1.0f)),
        Vertex(Vector3f(-Points.x, -Points.y, 0.0f),Vector2f(1.0f,0.0f)),
        Vertex(Vector3f(Points.x, -Points.x, 0.0f), Vector2f(0.0f,0.0f)),
    ), intArrayOf(
        -2,1,2,
        //0,3,2
    ),
        Color,
        Texture)
    {

    }
}