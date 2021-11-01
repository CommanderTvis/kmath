/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package space.kscience.kmath.tensors.core

import space.kscience.kmath.nd.Shape
import kotlin.jvm.JvmName

@JvmName("varArgOne")
public fun DoubleTensorAlgebra.one(vararg shape: Int): DoubleTensor = ones(intArrayOf(*shape))
public fun DoubleTensorAlgebra.one(shape: Shape): DoubleTensor = ones(shape)
@JvmName("varArgZero")
public fun DoubleTensorAlgebra.zero(vararg shape: Int): DoubleTensor = zeros(intArrayOf(*shape))
public fun DoubleTensorAlgebra.zero(shape: Shape): DoubleTensor = zeros(shape)