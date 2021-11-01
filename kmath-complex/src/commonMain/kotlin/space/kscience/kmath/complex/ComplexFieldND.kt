/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package space.kscience.kmath.complex

import space.kscience.kmath.misc.UnstableKMathAPI
import space.kscience.kmath.nd.*
import space.kscience.kmath.operations.*
import space.kscience.kmath.structures.Buffer
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * An optimized nd-field for complex numbers
 */
@OptIn(UnstableKMathAPI::class)
public sealed class ComplexFieldOpsND : BufferedFieldOpsND<Complex, ComplexField>(ComplexField.bufferAlgebra),
    ScaleOperations<StructureND<Complex>>, ExtendedFieldOps<StructureND<Complex>> {

    override fun StructureND<Complex>.toBufferND(): BufferND<Complex> = when (this) {
        is BufferND -> this
        else -> {
            val indexer = indexerBuilder(shape)
            BufferND(indexer, Buffer.complex(indexer.linearSize) { offset -> get(indexer.index(offset)) })
        }
    }

    //TODO do specialization

    override fun scale(a: StructureND<Complex>, value: Double): BufferND<Complex> =
        mapInline(a.toBufferND()) { it * value }

    override fun power(arg: StructureND<Complex>, pow: Number): BufferND<Complex> =
        mapInline(arg.toBufferND()) { power(it, pow) }

    override fun exp(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { exp(it) }
    override fun ln(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { ln(it) }

    override fun sin(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { sin(it) }
    override fun cos(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { cos(it) }
    override fun tan(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { tan(it) }
    override fun asin(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { asin(it) }
    override fun acos(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { acos(it) }
    override fun atan(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { atan(it) }

    override fun sinh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { sinh(it) }
    override fun cosh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { cosh(it) }
    override fun tanh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { tanh(it) }
    override fun asinh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { asinh(it) }
    override fun acosh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { acosh(it) }
    override fun atanh(arg: StructureND<Complex>): BufferND<Complex> = mapInline(arg.toBufferND()) { atanh(it) }

    public companion object : ComplexFieldOpsND()
}

@UnstableKMathAPI
public val ComplexField.bufferAlgebra: BufferFieldOps<Complex, ComplexField>
    get() = bufferAlgebra(Buffer.Companion::complex)


@OptIn(UnstableKMathAPI::class)
public class ComplexFieldND(override val shape: Shape) :
    ComplexFieldOpsND(), FieldND<Complex, ComplexField>, NumbersAddOps<StructureND<Complex>> {

    override fun number(value: Number): BufferND<Complex> {
        val d = value.toDouble() // minimize conversions
        return structureND(shape) { d.toComplex() }
    }
}

public val ComplexField.ndAlgebra: ComplexFieldOpsND get() = ComplexFieldOpsND

public fun ComplexField.ndAlgebra(vararg shape: Int): ComplexFieldND = ComplexFieldND(shape)

/**
 * Produce a context for n-dimensional operations inside this real field
 */
public inline fun <R> ComplexField.withNdAlgebra(vararg shape: Int, action: ComplexFieldND.() -> R): R {
    contract { callsInPlace(action, InvocationKind.EXACTLY_ONCE) }
    return ComplexFieldND(shape).action()
}
