package space.kscience.kmath.tensorflow


import org.tensorflow.Graph
import org.tensorflow.Operand
import org.tensorflow.Output
import org.tensorflow.Session
import org.tensorflow.ndarray.NdArray
import org.tensorflow.op.Ops
import org.tensorflow.op.core.Constant
import org.tensorflow.op.core.Max
import org.tensorflow.op.core.Min
import org.tensorflow.op.core.Sum
import org.tensorflow.types.TInt32
import org.tensorflow.types.family.TType
import space.kscience.kmath.misc.PerformancePitfall
import space.kscience.kmath.misc.UnstableKMathAPI
import space.kscience.kmath.nd.Shape
import space.kscience.kmath.nd.StructureND
import space.kscience.kmath.operations.Ring
import space.kscience.kmath.tensors.api.Tensor
import space.kscience.kmath.tensors.api.TensorAlgebra

internal fun IntArray.toLongArray() = LongArray(size) { get(it).toLong() }
internal fun LongArray.toIntArray() = IntArray(size) { get(it).toInt() }

internal val <T> NdArray<T>.scalar: T get() = getObject()


public sealed interface TensorFlowTensor<T> : Tensor<T>

@JvmInline
public value class TensorFlowArray<T>(public val tensor: NdArray<T>) : Tensor<T> {
    override val shape: Shape get() = tensor.shape().asArray().toIntArray()

    override fun get(index: IntArray): T = tensor.getObject(*index.toLongArray())

    //TODO implement native element sequence

    override fun set(index: IntArray, value: T) {
        tensor.setObject(value, *index.toLongArray())
    }
}

public abstract class TensorFlowOutput<T, TT : TType>(
    protected val graph: Graph,
    output: Output<TT>,
) : TensorFlowTensor<T> {

    public var output: Output<TT> = output
        internal set

    override val shape: Shape get() = output.shape().asArray().toIntArray()

    protected abstract fun org.tensorflow.Tensor.actualizeTensor(): NdArray<T>

    internal val actualTensor by lazy {
        Session(graph).use { session ->
            TensorFlowArray(session.runner().fetch(output).run().first().actualizeTensor())
        }
    }

    override fun get(index: IntArray): T = actualTensor[index]

    @PerformancePitfall
    override fun elements(): Sequence<Pair<IntArray, T>> = actualTensor.elements()

    override fun set(index: IntArray, value: T) {
        actualTensor[index] = value
    }

}


public abstract class TensorFlowAlgebra<T, TT : TType, A : Ring<T>> internal constructor(
    protected val graph: Graph,
) : TensorAlgebra<T, A> {

    protected val ops: Ops by lazy { Ops.create(graph) }

    protected abstract fun StructureND<T>.asTensorFlow(): TensorFlowOutput<T, TT>

    protected abstract fun Output<TT>.wrap(): TensorFlowOutput<T, TT>

    protected abstract fun const(value: T): Constant<TT>

    override fun StructureND<T>.valueOrNull(): T? = if (shape contentEquals intArrayOf(1))
        get(Shape(0)) else null

    private inline fun StructureND<T>.biOp(
        other: StructureND<T>,
        operation: (left: Operand<TT>, right: Operand<TT>) -> Operand<TT>,
    ): TensorFlowOutput<T, TT> {
        val left = asTensorFlow().output
        val right = other.asTensorFlow().output
        return operation(left, right).asOutput().wrap()
    }

    private inline fun T.biOp(
        other: StructureND<T>,
        operation: (left: Operand<TT>, right: Operand<TT>) -> Operand<TT>,
    ): TensorFlowOutput<T, TT> {
        val left = const(this)
        val right = other.asTensorFlow().output
        return operation(left, right).asOutput().wrap()
    }

    private inline fun StructureND<T>.biOp(
        value: T,
        operation: (left: Operand<TT>, right: Operand<TT>) -> Operand<TT>,
    ): TensorFlowOutput<T, TT> {
        val left = asTensorFlow().output
        val right = const(value)
        return operation(left, right).asOutput().wrap()
    }

    private inline fun Tensor<T>.inPlaceOp(
        other: StructureND<T>,
        operation: (left: Operand<TT>, right: Operand<TT>) -> Operand<TT>,
    ): Unit {
        val origin = asTensorFlow()
        val left = origin.output
        val right = other.asTensorFlow().output
        origin.output = operation(left, right).asOutput()
    }

    private inline fun Tensor<T>.inPlaceOp(
        value: T,
        operation: (left: Operand<TT>, right: Operand<TT>) -> Operand<TT>,
    ): Unit {
        val origin = asTensorFlow()
        val left = origin.output
        val right = const(value)
        origin.output = operation(left, right).asOutput()
    }

    private inline fun StructureND<T>.unOp(operation: (Operand<TT>) -> Operand<TT>): TensorFlowOutput<T, TT> =
        operation(asTensorFlow().output).asOutput().wrap()

    override fun T.plus(arg: StructureND<T>): TensorFlowOutput<T, TT> = biOp(arg, ops.math::add)

    override fun StructureND<T>.plus(arg: T): TensorFlowOutput<T, TT> = biOp(arg, ops.math::add)

    override fun StructureND<T>.plus(arg: StructureND<T>): TensorFlowOutput<T, TT> = biOp(arg, ops.math::add)

    override fun Tensor<T>.plusAssign(value: T): Unit = inPlaceOp(value, ops.math::add)

    override fun Tensor<T>.plusAssign(arg: StructureND<T>): Unit = inPlaceOp(arg, ops.math::add)

    override fun StructureND<T>.minus(arg: T): TensorFlowOutput<T, TT> = biOp(arg, ops.math::sub)

    override fun StructureND<T>.minus(arg: StructureND<T>): TensorFlowOutput<T, TT> = biOp(arg, ops.math::sub)

    override fun T.minus(arg: StructureND<T>): Tensor<T> = biOp(arg, ops.math::sub)

    override fun Tensor<T>.minusAssign(value: T): Unit = inPlaceOp(value, ops.math::sub)

    override fun Tensor<T>.minusAssign(arg: StructureND<T>): Unit = inPlaceOp(arg, ops.math::sub)

    override fun T.times(arg: StructureND<T>): TensorFlowOutput<T, TT> = biOp(arg, ops.math::mul)

    override fun StructureND<T>.times(arg: T): TensorFlowOutput<T, TT> = biOp(arg, ops.math::mul)

    override fun StructureND<T>.times(arg: StructureND<T>): TensorFlowOutput<T, TT> = biOp(arg, ops.math::mul)

    override fun Tensor<T>.timesAssign(value: T): Unit = inPlaceOp(value, ops.math::mul)

    override fun Tensor<T>.timesAssign(arg: StructureND<T>): Unit = inPlaceOp(arg, ops.math::mul)

    override fun StructureND<T>.unaryMinus(): TensorFlowOutput<T, TT> = unOp(ops.math::neg)

    override fun Tensor<T>.get(i: Int): Tensor<T> = unOp {
        TODO("Not yet implemented")
    }

    override fun Tensor<T>.transpose(i: Int, j: Int): Tensor<T> = unOp {
        ops.linalg.transpose(it, ops.constant(intArrayOf(i, j)))
    }

    override fun Tensor<T>.view(shape: IntArray): Tensor<T> = unOp {
        ops.reshape(it, ops.constant(shape))
    }

    override fun Tensor<T>.viewAs(other: StructureND<T>): Tensor<T> = biOp(other) { l, r ->
        ops.reshape(l, ops.shape(r))
    }

    override fun StructureND<T>.dot(other: StructureND<T>): TensorFlowOutput<T, TT> = biOp(other) { l, r ->
        ops.linalg.matMul(
            if (l.asTensor().shape().numDimensions() == 1) ops.expandDims(l,ops.constant(0)) else l,
            if (r.asTensor().shape().numDimensions() == 1) ops.expandDims(r,ops.constant(-1)) else r)
    }

    override fun diagonalEmbedding(
        diagonalEntries: Tensor<T>,
        offset: Int,
        dim1: Int,
        dim2: Int,
    ): TensorFlowOutput<T, TT> = diagonalEntries.unOp {
        TODO()
    }

    override fun StructureND<T>.sum(): T = unOp {
        ops.sum(it, ops.constant(intArrayOf()))
    }.value()

    override fun StructureND<T>.sum(dim: Int, keepDim: Boolean): TensorFlowOutput<T, TT> = unOp {
        ops.sum(it, ops.constant(dim), Sum.keepDims(keepDim))
    }

    override fun StructureND<T>.min(): T = unOp {
        ops.min(it, ops.constant(intArrayOf()))
    }.value()

    override fun StructureND<T>.min(dim: Int, keepDim: Boolean): Tensor<T> = unOp {
        ops.min(it, ops.constant(dim), Min.keepDims(keepDim))
    }

    override fun StructureND<T>.max(): T = unOp {
        ops.max(it, ops.constant(intArrayOf()))
    }.value()

    override fun StructureND<T>.max(dim: Int, keepDim: Boolean): Tensor<T> = unOp {
        ops.max(it, ops.constant(dim), Max.keepDims(keepDim))
    }

    override fun StructureND<T>.argMax(dim: Int, keepDim: Boolean): Tensor<Int> = IntTensorFlowOutput(
        graph,
        ops.math.argMax(asTensorFlow().output, ops.constant(dim), TInt32::class.java).output()
    ).actualTensor

    @OptIn(UnstableKMathAPI::class)
    override fun export(arg: StructureND<T>): StructureND<T> =
        if (arg is TensorFlowOutput<T, *>) arg.actualTensor else arg
}

//TODO add TensorFlow expressions