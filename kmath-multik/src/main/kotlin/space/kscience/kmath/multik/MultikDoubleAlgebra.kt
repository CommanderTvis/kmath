/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.multik

import org.jetbrains.kotlinx.multik.ndarray.data.DataType
import space.kscience.kmath.nd.StructureND
import space.kscience.kmath.operations.*

public object MultikDoubleAlgebra : MultikDivisionTensorAlgebra<Double, DoubleField>(),
    TrigonometricOperations<StructureND<Double>>, ExponentialOperations<StructureND<Double>> {
    override val elementAlgebra: DoubleField get() = DoubleField
    override val type: DataType get() = DataType.DoubleDataType

    override fun sin(arg: StructureND<Double>): MultikTensor<Double> =
        multikMath.mathEx.sin(arg.asMultik().array).wrap()

    override fun cos(arg: StructureND<Double>): MultikTensor<Double> =
        multikMath.mathEx.cos(arg.asMultik().array).wrap()

    override fun tan(arg: StructureND<Double>): MultikTensor<Double> = sin(arg) / cos(arg)

    override fun asin(arg: StructureND<Double>): MultikTensor<Double> = arg.map { asin(it) }

    override fun acos(arg: StructureND<Double>): MultikTensor<Double> = arg.map { acos(it) }

    override fun atan(arg: StructureND<Double>): MultikTensor<Double> = arg.map { atan(it) }

    override fun exp(arg: StructureND<Double>): MultikTensor<Double> =
        multikMath.mathEx.exp(arg.asMultik().array).wrap()

    override fun ln(arg: StructureND<Double>): MultikTensor<Double> = multikMath.mathEx.log(arg.asMultik().array).wrap()

    override fun sinh(arg: StructureND<Double>): MultikTensor<Double> = (exp(arg) - exp(-arg)) / 2.0

    override fun cosh(arg: StructureND<Double>): MultikTensor<Double> = (exp(arg) + exp(-arg)) / 2.0

    override fun tanh(arg: StructureND<Double>): MultikTensor<Double> {
        val expPlus = exp(arg)
        val expMinus = exp(-arg)
        return divide((expPlus - expMinus), (expPlus + expMinus))
    }

    override fun asinh(arg: StructureND<Double>): MultikTensor<Double> = arg.map { asinh(it) }

    override fun acosh(arg: StructureND<Double>): MultikTensor<Double> = arg.map { acosh(it) }

    override fun atanh(arg: StructureND<Double>): MultikTensor<Double> = arg.map { atanh(it) }
}

public val Double.Companion.multikAlgebra: MultikTensorAlgebra<Double, DoubleField> get() = MultikDoubleAlgebra
public val DoubleField.multikAlgebra: MultikTensorAlgebra<Double, DoubleField> get() = MultikDoubleAlgebra
