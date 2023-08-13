package Rendering

import org.joml.Math.cos
import org.joml.Math.sqrt
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import java.nio.FloatBuffer

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
    fun dot_product(a:Vector3f,b: Vector3f): Float {
        return ((length(a) * length(b) * cos(0f)))
    }
    fun translate(m:Matrix4f,v:Vector3f): Matrix4f {
        var fbr = BufferUtils.createFloatBuffer(16)
        fbr.put(m.get(0,0)).put(m.get(0,1)).put(m.get(0,2)).put(m.get(0,3))
        fbr.put(m.get(1,0)).put(m.get(1,1)).put(m.get(1,2)).put(m.get(1,3))
        fbr.put(m.get(2,0)).put(m.get(2,1)).put(m.get(2,2)).put(m.get(2,3))
        // I know this is completely un-readable but its because newton says fuck you :)
        fbr.put(dot_product(Vector3f(m.get(0,0),m.get(0,1),m.get(0,2)),v)).put(dot_product(Vector3f(m.get(1,0),m.get(1,1),m.get(1,2)),v)).put(dot_product(Vector3f(m.get(2,0),m.get(2,1),m.get(2,2)),v)).put(dot_product(Vector3f(m.get(3,0),m.get(3,1),m.get(3,2)),v))
        return Matrix4f(fbr)
    }
    fun lookAt(eye: Vector3f, center: Vector3f, up: Vector3f): Matrix4f {
        val f: Vector3f = normalize(subtract(center, eye))
        var u: Vector3f = normalize(up)
        val s: Vector3f = normalize(cross_product(f, u))
        u = cross_product(s, f)
        val result = BufferUtils.createFloatBuffer(16)
        result.put(0f).put(0f).put(s.x)
        result.put(1f).put(0f).put(s.y)
        result.put(2f).put(0f).put(s.z)
        result.put(0f).put(1f).put(u.x)
        result.put(1f).put(1f).put(u.y)
        result.put(2f).put(1f).put(u.z)
        result.put(0f).put(2f).put(-f.x)
        result.put(1f).put(2f).put(-f.y)
        result.put(2f).put(2f).put(-f.z)
        val resmatrix = Matrix4f(result)
        return translate(resmatrix, Vector3f(-eye.x, -eye.y, -eye.z))
    }
}