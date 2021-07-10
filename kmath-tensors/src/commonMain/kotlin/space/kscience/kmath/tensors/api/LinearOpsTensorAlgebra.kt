/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.tensors.api

/**
 * Common linear algebra operations. Operates on [Tensor].
 *
 * @param T the type of items closed under division in the tensors.
 */
public interface LinearOpsTensorAlgebra<T> : TensorPartialDivisionAlgebra<T> {

    /**
     * Computes the determinant of a square matrix input, or of each square matrix in a batched input.
     * For more information: https://pytorch.org/docs/stable/linalg.html#torch.linalg.det
     *
     * @return the determinant.
     */
    public fun Tensor<T>.det(): Tensor<T>

    /**
     * Computes the multiplicative inverse matrix of a square matrix input, or of each square matrix in a batched input.
     * Given a square matrix `A`, return the matrix `AInv` satisfying
     * `A dot AInv  = AInv dot A = eye(a.shape[0])`.
     * For more information: https://pytorch.org/docs/stable/linalg.html#torch.linalg.inv
     *
     * @return the multiplicative inverse of a matrix.
     */
    public fun Tensor<T>.inv(): Tensor<T>

    /**
     * Cholesky decomposition.
     *
     * Computes the Cholesky decomposition of a Hermitian (or symmetric for real-valued matrices)
     * positive-definite matrix or the Cholesky decompositions for a batch of such matrices.
     * Each decomposition has the form:
     * Given a tensor `input`, return the tensor `L` satisfying `input = L dot L.H`,
     * where L is a lower-triangular matrix and L.H is the conjugate transpose of L,
     * which is just a transpose for the case of real-valued input matrices.
     * For more information: https://pytorch.org/docs/stable/linalg.html#torch.linalg.cholesky
     *
     * @return the batch of L matrices.
     */
    public fun Tensor<T>.cholesky(): Tensor<T>

    /**
     * QR decomposition.
     *
     * Computes the QR decomposition of a matrix or a batch of matrices, and returns a pair `(Q, R)` of tensors.
     * Given a tensor `input`, return tensors (Q, R) satisfying ``input = Q dot R``,
     * with `Q` being an orthogonal matrix or batch of orthogonal matrices
     * and `R` being an upper triangular matrix or batch of upper triangular matrices.
     * For more information: https://pytorch.org/docs/stable/linalg.html#torch.linalg.qr
     *
     * @return pair of Q and R tensors.
     */
    public fun Tensor<T>.qr(): Pair<Tensor<T>, Tensor<T>>

    /**
     * LUP decomposition
     *
     * Computes the LUP decomposition of a matrix or a batch of matrices.
     * Given a tensor `input`, return tensors (P, L, U) satisfying :
     * `P dot input = L dot  U` or `input = P dot L dot  U`
     * depending on the implementation, with :
     * `P` being a permutation matrix or batch of matrices,
     * `L` being a lower triangular matrix or batch of matrices,
     * `U` being an upper triangular matrix or batch of matrices.
     *
     * * @return triple of P, L and U tensors
     */
    public fun Tensor<T>.lu(): Triple<Tensor<T>, Tensor<T>, Tensor<T>>

    /**
     * Singular Value Decomposition.
     *
     * Computes the singular value decomposition of either a matrix or batch of matrices `input`.
     * The singular value decomposition is represented as a triple `(U, S, V)`,
     * such that `input = U dot diagonalEmbedding(S) dot  V.H`,
     * where V.H is the conjugate transpose of V.
     * If input is a batch of tensors, then U, S, and Vh are also batched with the same batch dimensions as input.
     * For more information: https://pytorch.org/docs/stable/linalg.html#torch.linalg.svd
     *
     * @return triple `(U, S, V)`.
     */
    public fun Tensor<T>.svd(): Triple<Tensor<T>, Tensor<T>, Tensor<T>>

    /**
     * Returns eigenvalues and eigenvectors of a real symmetric matrix input or a batch of real symmetric matrices,
     * represented by a pair (eigenvalues, eigenvectors).
     * For more information: https://pytorch.org/docs/stable/generated/torch.symeig.html
     *
     * @return a pair (eigenvalues, eigenvectors)
     */
    public fun Tensor<T>.symEig(): Pair<Tensor<T>, Tensor<T>>

}
