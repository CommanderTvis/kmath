package kscience.kmath.gsl

import kotlinx.cinterop.*
import org.gnu.gsl.*

internal class GslRealVector(override val rawNativeHandle: CPointer<gsl_vector>, scope: AutofreeScope) : 
    GslVector<Double, gsl_vector>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): Double = 
        gsl_vector_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: Double): Unit = 
        gsl_vector_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslRealVector {
        val new = checkNotNull(gsl_vector_alloc(size.toULong()))
        gsl_vector_memcpy(new, nativeHandle)
        return GslRealVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslRealVector)
            return gsl_vector_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_free(nativeHandle)
}

internal class GslFloatVector(override val rawNativeHandle: CPointer<gsl_vector_float>, scope: AutofreeScope) : 
    GslVector<Float, gsl_vector_float>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): Float = 
        gsl_vector_float_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: Float): Unit = 
        gsl_vector_float_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslFloatVector {
        val new = checkNotNull(gsl_vector_float_alloc(size.toULong()))
        gsl_vector_float_memcpy(new, nativeHandle)
        return GslFloatVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslFloatVector)
            return gsl_vector_float_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_float_free(nativeHandle)
}

internal class GslShortVector(override val rawNativeHandle: CPointer<gsl_vector_short>, scope: AutofreeScope) : 
    GslVector<Short, gsl_vector_short>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): Short = 
        gsl_vector_short_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: Short): Unit = 
        gsl_vector_short_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslShortVector {
        val new = checkNotNull(gsl_vector_short_alloc(size.toULong()))
        gsl_vector_short_memcpy(new, nativeHandle)
        return GslShortVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslShortVector)
            return gsl_vector_short_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_short_free(nativeHandle)
}

internal class GslUShortVector(override val rawNativeHandle: CPointer<gsl_vector_ushort>, scope: AutofreeScope) : 
    GslVector<UShort, gsl_vector_ushort>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): UShort = 
        gsl_vector_ushort_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: UShort): Unit = 
        gsl_vector_ushort_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslUShortVector {
        val new = checkNotNull(gsl_vector_ushort_alloc(size.toULong()))
        gsl_vector_ushort_memcpy(new, nativeHandle)
        return GslUShortVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslUShortVector)
            return gsl_vector_ushort_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_ushort_free(nativeHandle)
}

internal class GslLongVector(override val rawNativeHandle: CPointer<gsl_vector_long>, scope: AutofreeScope) : 
    GslVector<Long, gsl_vector_long>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): Long = 
        gsl_vector_long_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: Long): Unit = 
        gsl_vector_long_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslLongVector {
        val new = checkNotNull(gsl_vector_long_alloc(size.toULong()))
        gsl_vector_long_memcpy(new, nativeHandle)
        return GslLongVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslLongVector)
            return gsl_vector_long_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_long_free(nativeHandle)
}

internal class GslULongVector(override val rawNativeHandle: CPointer<gsl_vector_ulong>, scope: AutofreeScope) : 
    GslVector<ULong, gsl_vector_ulong>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): ULong = 
        gsl_vector_ulong_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: ULong): Unit = 
        gsl_vector_ulong_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslULongVector {
        val new = checkNotNull(gsl_vector_ulong_alloc(size.toULong()))
        gsl_vector_ulong_memcpy(new, nativeHandle)
        return GslULongVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslULongVector)
            return gsl_vector_ulong_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_ulong_free(nativeHandle)
}

internal class GslIntVector(override val rawNativeHandle: CPointer<gsl_vector_int>, scope: AutofreeScope) : 
    GslVector<Int, gsl_vector_int>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): Int = 
        gsl_vector_int_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: Int): Unit = 
        gsl_vector_int_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslIntVector {
        val new = checkNotNull(gsl_vector_int_alloc(size.toULong()))
        gsl_vector_int_memcpy(new, nativeHandle)
        return GslIntVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslIntVector)
            return gsl_vector_int_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_int_free(nativeHandle)
}

internal class GslUIntVector(override val rawNativeHandle: CPointer<gsl_vector_uint>, scope: AutofreeScope) : 
    GslVector<UInt, gsl_vector_uint>(scope) {
    override val size: Int
        get() = nativeHandle.pointed.size.toInt()

    override operator fun get(index: Int): UInt = 
        gsl_vector_uint_get(nativeHandle, index.toULong())

    override operator fun set(index: Int, value: UInt): Unit = 
        gsl_vector_uint_set(nativeHandle, index.toULong(), value)

    override fun copy(): GslUIntVector {
        val new = checkNotNull(gsl_vector_uint_alloc(size.toULong()))
        gsl_vector_uint_memcpy(new, nativeHandle)
        return GslUIntVector(new, scope)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GslUIntVector)
            return gsl_vector_uint_equal(nativeHandle, other.nativeHandle) == 1

        return super.equals(other)
    }

    override fun close(): Unit = gsl_vector_uint_free(nativeHandle)
}

