import org.joml.Vector2f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.Serializable
import java.lang.NullPointerException
import java.nio.FloatBuffer



//class Render_vertex: Serializable {
//    // 2d position of vertex
//    public var pos = Vector2f()
//    // RGBA color
//    public var color = Vector4f()
//    //texture stuff
//    //public var uv = Vector2f()
//    //public var texIndex : Float = 0f;
//}
//fun RV_toFB (RVarr : Array<Render_vertex?>) : FloatBuffer {
//    try {
//        val Destbuf = BufferUtils.createFloatBuffer(RVarr.count() * 6)
//        for (vert in RVarr) {
//            Destbuf.put(vert!!.pos.x).put(vert.pos.y).put(vert.color.x).put(vert.color.y).put(vert.color.z)
//                .put(vert.color.w)
//        }
//        return Destbuf
//    } catch (e: NullPointerException) {
//        System.err.println(e)
//        return BufferUtils.createFloatBuffer(RVarr.count() * 6)
//    }
//}

// Memory shit
/**
 * Implements functions useful to check
 * Memory usage.
 *
 * @author Pierre Malarme
 * @version 1.0
 */
internal object Memory {
    /**
     * Function that get the size of an object.
     *
     * @param object
     * @return Size in bytes of the object or -1 if the object
     * is null.
     * @throws IOException
     */
    @Throws(IOException::class)
    fun sizeOf(`object`: Any?): Int {
        if (`object` == null) return -1

        // Special output stream use to write the content
        // of an output stream to an internal byte array.
        val byteArrayOutputStream = ByteArrayOutputStream()

        // Output stream that can write object
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)

        // Write object and close the output stream
        objectOutputStream.writeObject(`object`)
        objectOutputStream.flush()
        objectOutputStream.close()

        // Get the byte array
        val byteArray = byteArrayOutputStream.toByteArray()

        // TODO can the toByteArray() method return a
        // null array ?
        return byteArray?.size ?: 0
    }
}