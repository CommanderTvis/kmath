/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package space.kscience.kmath.tensors.core.internal

import space.kscience.kmath.nd.MutableBufferND
import space.kscience.kmath.nd.StructureND
import space.kscience.kmath.structures.asMutableBuffer
import space.kscience.kmath.tensors.api.Tensor
import space.kscience.kmath.tensors.core.BufferedTensor
import space.kscience.kmath.tensors.core.DoubleTensor
import space.kscience.kmath.tensors.core.IntTensor
import space.kscience.kmath.tensors.core.TensorLinearStructure

internal fun BufferedTensor<Int>.asTensor(): IntTensor =
    IntTensor(this.shape, this.mutableBuffer.array(), this.bufferStart)

internal fun BufferedTensor<Double>.asTensor(): DoubleTensor =
    DoubleTensor(this.shape, this.mutableBuffer.array(), this.bufferStart)

internal fun <T> StructureND<T>.copyToBufferedTensor(): BufferedTensor<T> =
    BufferedTensor(
        this.shape,
        TensorLinearStructure(this.shape).asSequence().map(this::get).toMutableList().asMutableBuffer(), 0
    )

internal fun <T> StructureND<T>.toBufferedTensor(): BufferedTensor<T> = when (this) {
    is BufferedTensor<T> -> this
    is MutableBufferND<T> -> if (this.indices == TensorLinearStructure(this.shape)) {
        BufferedTensor(this.shape, this.buffer, 0)
    } else {
        this.copyToBufferedTensor()
    }
    else -> this.copyToBufferedTensor()
}

@PublishedApi
internal val StructureND<Double>.tensor: DoubleTensor
    get() = when (this) {
        is DoubleTensor -> this
        else -> this.toBufferedTensor().asTensor()
    }

internal val Tensor<Int>.tensor: IntTensor
    get() = when (this) {
        is IntTensor -> this
        else -> this.toBufferedTensor().asTensor()
    }
