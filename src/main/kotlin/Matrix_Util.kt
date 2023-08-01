import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector2i
import java.nio.FloatBuffer


// Calculate orthographic projection of a 4d matrix
fun mat4_projection_ortho (Left : Float, Right : Float, Top : Float, Bottom : Float,Near : Float, Far : Float): Matrix4f {
     var width = Right - Left
     var height = Top - Bottom
     var depth = Far - Near
     var Matrix : Matrix4f = Matrix4f(
         2f/width,0f,0f,0f,
         0f,2f/height,0f,0f,
         0f,0f,2f/depth,0f,
         -(Right + Left) / width,-(Top + Bottom) / height,-(Near + Far) / depth,1f)
    return Matrix
}
fun matrixToBuffer(m: Matrix4f, dest: FloatBuffer) {
    matrixToBuffer(m, 0, dest)
}

private fun matrixToBuffer(m: Matrix4f, offset: Int, dest: FloatBuffer) {
    dest.put(offset, m.m00())
    dest.put(offset + 1, m.m01())
    dest.put(offset + 2, m.m02())
    dest.put(offset + 3, m.m03())
    dest.put(offset + 4, m.m10())
    dest.put(offset + 5, m.m11())
    dest.put(offset + 6, m.m12())
    dest.put(offset + 7, m.m13())
    dest.put(offset + 8, m.m20())
    dest.put(offset + 9, m.m21())
    dest.put(offset + 10, m.m22())
    dest.put(offset + 11, m.m23())
    dest.put(offset + 12, m.m30())
    dest.put(offset + 13, m.m31())
    dest.put(offset + 14, m.m32())
    dest.put(offset + 15, m.m33())
}

fun vec2_init(x:Float,y:Float) : Vector2f {
    return Vector2f(x,y)
}
