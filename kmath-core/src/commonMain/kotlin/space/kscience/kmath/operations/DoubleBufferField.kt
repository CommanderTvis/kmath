/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.operations

import space.kscience.kmath.misc.UnstableKMathAPI
import space.kscience.kmath.operations.DoubleField.pow
import space.kscience.kmath.structures.Buffer
import space.kscience.kmath.structures.DoubleBuffer

/**
 * [ExtendedField] over [DoubleBuffer].
 *
 * @property size the size of buffers to operate on.
 */
public class DoubleBufferField(public val size: Int) : ExtendedField<Buffer<Double>>, DoubleBufferOps() {
    override val zero: Buffer<Double> by lazy { DoubleBuffer(size) { 0.0 } }
    override val one: Buffer<Double> by lazy { DoubleBuffer(size) { 1.0 } }

    override fun sinh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.sinh(arg)

    override fun cosh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.cosh(arg)

    override fun tanh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.tanh(arg)

    override fun asinh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.asinh(arg)

    override fun acosh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.acosh(arg)

    override fun atanh(arg: Buffer<Double>): DoubleBuffer = super<DoubleBufferOps>.atanh(arg)

    override fun power(arg: Buffer<Double>, pow: Number): DoubleBuffer = if (pow.isInteger()) {
        arg.mapInline { it.pow(pow.toInt()) }
    } else {
        arg.mapInline {
            if(it<0) throw IllegalArgumentException("Negative argument $it could not be raised to the fractional power")
            it.pow(pow.toDouble())
        }
    }

    override fun unaryOperationFunction(operation: String): (arg: Buffer<Double>) -> Buffer<Double> =
        super<ExtendedField>.unaryOperationFunction(operation)

    //    override fun number(value: Number): Buffer<Double> = DoubleBuffer(size) { value.toDouble() }
//
//    override fun Buffer<Double>.unaryMinus(): Buffer<Double> = DoubleBufferOperations.run {
//        -this@unaryMinus
//    }
//
//    override fun add(a: Buffer<Double>, b: Buffer<Double>): DoubleBuffer {
//        require(a.size == size) { "The buffer size ${a.size} does not match context size $size" }
//        return DoubleBufferOperations.add(a, b)
//    }
//

//
//    override fun multiply(a: Buffer<Double>, b: Buffer<Double>): DoubleBuffer {
//        require(a.size == size) { "The buffer size ${a.size} does not match context size $size" }
//        return DoubleBufferOperations.multiply(a, b)
//    }
//
//    override fun divide(a: Buffer<Double>, b: Buffer<Double>): DoubleBuffer {
//        require(a.size == size) { "The buffer size ${a.size} does not match context size $size" }
//        return DoubleBufferOperations.divide(a, b)
//    }
//
//    override fun sin(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.sin(arg)
//    }
//
//    override fun cos(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.cos(arg)
//    }
//
//    override fun tan(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.tan(arg)
//    }
//
//    override fun asin(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.asin(arg)
//    }
//
//    override fun acos(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.acos(arg)
//    }
//
//    override fun atan(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.atan(arg)
//    }
//
//    override fun sinh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.sinh(arg)
//    }
//
//    override fun cosh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.cosh(arg)
//    }
//
//    override fun tanh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.tanh(arg)
//    }
//
//    override fun asinh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.asinh(arg)
//    }
//
//    override fun acosh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.acosh(arg)
//    }
//
//    override fun atanh(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.atanh(arg)
//    }
//
//    override fun power(arg: Buffer<Double>, pow: Number): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.power(arg, pow)
//    }
//
//    override fun exp(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.exp(arg)
//    }
//
//    override fun ln(arg: Buffer<Double>): DoubleBuffer {
//        require(arg.size == size) { "The buffer size ${arg.size} does not match context size $size" }
//        return DoubleBufferOperations.ln(arg)
//    }

}