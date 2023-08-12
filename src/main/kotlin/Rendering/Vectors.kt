package Rendering

import org.joml.Math.sqrt
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.BufferUtils

object Vectors {
    fun length(vector:Vector3f): Float {
        return sqrt((vector.x * vector.x) + (vector.y * vector.y) + (vector.z * vector.z))
    }
    fun normalize(vector: Vector3f): Vector3f {
        val a = length(vector)
        return(Vector3f((vector.x / a),(vector.y / a),(vector.z / a)))
    }
    fun subtract (a:Vector3f,b:Vector3f): Vector3f {
            return Vector3f((a.x - b.x),(a.y - b.y),(a.z - b.z))
    }
    fun cross_product(a:Vector3f,b:Vector3f): Vector3f {
        return Vector3f(
            (a.y * b.z) - (a.z * b.y),
            (a.z * b.x) - (a.x * b.z),
            (a.x * b.y) - (a.y * b.x)
        )
    }
}