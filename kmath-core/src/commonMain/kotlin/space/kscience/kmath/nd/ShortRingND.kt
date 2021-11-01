/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package space.kscience.kmath.nd

import space.kscience.kmath.misc.UnstableKMathAPI
import space.kscience.kmath.operations.NumbersAddOps
import space.kscience.kmath.operations.ShortRing
import space.kscience.kmath.operations.bufferAlgebra
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public sealed class ShortRingOpsND : BufferedRingOpsND<Short, ShortRing>(ShortRing.bufferAlgebra) {
    public companion object : ShortRingOpsND()
}

@OptIn(UnstableKMathAPI::class)
public class ShortRingND(
    override val shape: Shape
) : ShortRingOpsND(), RingND<Short, ShortRing>, NumbersAddOps<StructureND<Short>> {

    override fun number(value: Number): BufferND<Short> {
        val d = value.toShort() // minimize conversions
        return structureND(shape) { d }
    }
}

public inline fun <R> ShortRing.withNdAlgebra(vararg shape: Int, action: ShortRingND.() -> R): R {
    contract { callsInPlace(action, InvocationKind.EXACTLY_ONCE) }
    return ShortRingND(shape).run(action)
}
