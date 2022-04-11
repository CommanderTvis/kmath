/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.tensors.api

import space.kscience.kmath.nd.StructureND
import space.kscience.kmath.operations.ExtendedFieldOps
import space.kscience.kmath.operations.Field


/**
 * Analytic operations on [Tensor].
 *
 * @param T the type of items closed under analytic functions in the tensors.
 */
public interface AnalyticTensorAlgebra<T, A : Field<T>> :
    TensorPartialDivisionAlgebra<T, A>, ExtendedFieldOps<StructureND<T>> {

    /**
     * @return the mean of all elements in the input tensor.
     */
    public fun StructureND<T>.mean(): T

    /**
     * Returns the mean of each row of the input tensor in the given dimension [dim].
     *
     * If [keepDim] is true, the output tensor is of the same size as
     * input except in the dimension [dim] where it is of size 1.
     * Otherwise, [dim] is squeezed, resulting in the output tensor having 1 fewer dimension.
     *
     * @param dim the dimension to reduce.
     * @param keepDim whether the output tensor has [dim] retained or not.
     * @return the mean of each row of the input tensor in the given dimension [dim].
     */
    public fun StructureND<T>.mean(dim: Int, keepDim: Boolean): Tensor<T>

    /**
     * @return the standard deviation of all elements in the input tensor.
     */
    public fun StructureND<T>.std(): T

    /**
     * Returns the standard deviation of each row of the input tensor in the given dimension [dim].
     *
     * If [keepDim] is true, the output tensor is of the same size as
     * input except in the dimension [dim] where it is of size 1.
     * Otherwise, [dim] is squeezed, resulting in the output tensor having 1 fewer dimension.
     *
     * @param dim the dimension to reduce.
     * @param keepDim whether the output tensor has [dim] retained or not.
     * @return the standard deviation of each row of the input tensor in the given dimension [dim].
     */
    public fun StructureND<T>.std(dim: Int, keepDim: Boolean): Tensor<T>

    /**
     * @return the variance of all elements in the input tensor.
     */
    public fun StructureND<T>.variance(): T

    /**
     * Returns the variance of each row of the input tensor in the given dimension [dim].
     *
     * If [keepDim] is true, the output tensor is of the same size as
     * input except in the dimension [dim] where it is of size 1.
     * Otherwise, [dim] is squeezed, resulting in the output tensor having 1 fewer dimension.
     *
     * @param dim the dimension to reduce.
     * @param keepDim whether the output tensor has [dim] retained or not.
     * @return the variance of each row of the input tensor in the given dimension [dim].
     */
    public fun StructureND<T>.variance(dim: Int, keepDim: Boolean): Tensor<T>

    override fun sin(arg: StructureND<T>): Tensor<T>

    override fun cos(arg: StructureND<T>): Tensor<T>

    override fun asin(arg: StructureND<T>): Tensor<T>

    override fun acos(arg: StructureND<T>): Tensor<T>

    override fun tan(arg: StructureND<T>): Tensor<T>

    override fun atan(arg: StructureND<T>): Tensor<T>

    override fun exp(arg: StructureND<T>): Tensor<T>

    override fun ln(arg: StructureND<T>): Tensor<T>

    override fun sinh(arg: StructureND<T>): Tensor<T>

    override fun cosh(arg: StructureND<T>): Tensor<T>

    override fun tanh(arg: StructureND<T>): Tensor<T>

    override fun asinh(arg: StructureND<T>): Tensor<T>

    override fun acosh(arg: StructureND<T>): Tensor<T>

    override fun atanh(arg: StructureND<T>): Tensor<T>

    //For information: https://pytorch.org/docs/stable/generated/torch.sqrt.html
    public fun StructureND<T>.sqrt(): Tensor<T>

    //For information: https://pytorch.org/docs/stable/generated/torch.ceil.html#torch.ceil
    public fun StructureND<T>.ceil(): Tensor<T>

    //For information: https://pytorch.org/docs/stable/generated/torch.floor.html#torch.floor
    public fun StructureND<T>.floor(): Tensor<T>

//    //For information: https://pytorch.org/docs/stable/generated/torch.exp.html
//    public fun StructureND<T>.exp(): Tensor<T> = exp(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.log.html
//    @JvmName("lnChain")
//    public fun StructureND<T>.ln(): Tensor<T> = ln(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.acos.html#torch.cos
//    @JvmName("cosChain")
//    public fun StructureND<T>.cos(): Tensor<T> = cos(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.acos.html#torch.acos
//    @JvmName("acosChain")
//    public fun StructureND<T>.acos(): Tensor<T> = acos(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.acosh.html#torch.cosh
//    @JvmName("coshChain")
//    public fun StructureND<T>.cosh(): Tensor<T> = cosh(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.acosh.html#torch.acosh
//    @JvmName("acoshChain")
//    public fun StructureND<T>.acosh(): Tensor<T> = acosh(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.asin.html#torch.sin
//    @JvmName("sinChain")
//    public fun StructureND<T>.sin(): Tensor<T> = sin(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.asin.html#torch.asin
//    @JvmName("asinChain")
//    public fun StructureND<T>.asin(): Tensor<T> = asin(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.asin.html#torch.sinh
//    @JvmName("sinhChain")
//    public fun StructureND<T>.sinh(): Tensor<T> = sinh(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.asin.html#torch.asinh
//    @JvmName("asinhChain")
//    public fun StructureND<T>.asinh(): Tensor<T> = asinh(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.atan.html#torch.tan
//    @JvmName("tanChain")
//    public fun StructureND<T>.tan(): Tensor<T> = tan(this)
//
//    //https://pytorch.org/docs/stable/generated/torch.atan.html#torch.atan
//    @JvmName("atanChain")
//    public fun StructureND<T>.atan(): Tensor<T> = atan(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.atanh.html#torch.tanh
//    @JvmName("tanhChain")
//    public fun StructureND<T>.tanh(): Tensor<T> = tanh(this)
//
//    //For information: https://pytorch.org/docs/stable/generated/torch.atanh.html#torch.atanh
//    @JvmName("atanhChain")
//    public fun StructureND<T>.atanh(): Tensor<T> = atanh(this)
}