package ru.academits.bondyuk.matrix;

import ru.academits.bondyuk.vector.Vector;

// TODO 2: f, i, j
public class Matrix {
    private Vector[] vectors;

    public Matrix(int matrixVectorSize, int vectorsCount) {
        vectors = new Vector[vectorsCount];

        for (int i = 0; i < vectorsCount; ++i) {
            vectors[i] = new Vector(matrixVectorSize);
        }
    }

    public Matrix(Matrix matrix) {
        int vectorsCount = matrix.getVectors().length;
        vectors = new Vector[vectorsCount];

        for (int i = 0; i < vectorsCount; ++i) {
            vectors[i] = new Vector(matrix.getVectors()[i]);
        }
    }

    public Matrix(double[][] arrays) {
        int vectorsCount = arrays.length;
        vectors = new Vector[vectorsCount];

        int matrixVectorMaxSize = arrays[0].length;

        for (int i = 1; i < vectorsCount; ++i) {
            if (matrixVectorMaxSize < arrays[i].length) {
                matrixVectorMaxSize = arrays[i].length;
            }
        }

        for (int i = 0; i < vectorsCount; ++i) {
            vectors[i] = new Vector(arrays[matrixVectorMaxSize]);

            for (int j = 0; j < matrixVectorMaxSize; ++j) {
                if (arrays[i].length > j) {
                    vectors[i].setElement(j, arrays[i][j]);
                } else {
                    vectors[i].setElement(j, 0);
                }
            }
        }
    }

    public Matrix(Vector[] vectors) {
        int vectorsCount = vectors.length;
        this.vectors = new Vector[vectorsCount];

        System.arraycopy(vectors, 0, this.vectors, 0, vectorsCount);
    }

    public Vector[] getVectors() {
        return vectors;
    }

    public int[] getSize() {
        int vectorsCount = vectors.length;
        int matrixVectorSize = vectors[0].getSize();

        return new int[]{matrixVectorSize, vectorsCount};
    }

    public Vector getVector(int index) {
        return vectors[index];
    }

    public void setVector(Vector vector, int index) {
        int matrixVectorSize = vectors[0].getSize();

        for (int j = 0; j < matrixVectorSize; ++j) {
            if (vector.getSize() > j) {
                vectors[index].setElement(j, vector.getElement(j));
            } else {
                vectors[index].setElement(j, 0);
            }
        }
    }

    public Vector getColumn(int columnIndex) {
        Vector vector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; ++i) {
            vector.setElement(i, vectors[i].getElement(columnIndex));
        }

        return vector;
    }

    public void transpose() {
        int matrixVectorSize = vectors[0].getSize();

        Vector[] transposedVector = new Vector[matrixVectorSize];

        for (int i = 0; i < matrixVectorSize; ++i) {
            transposedVector[i] = getColumn(i);
        }

        vectors = transposedVector;
    }


    public void multiply(double number) {
        int matrixVectorSize = vectors[0].getSize();

        for (Vector vector : vectors) {
            for (int j = 0; j < matrixVectorSize; ++j) {
                vector.setElement(j, vector.getElement(j) * number);
            }
        }
    }

    public Vector multiply(Vector vector) {
        int vectorSize = vector.getSize();
        Vector multiplyResult = new Vector(vectorSize);
        int matrixVectorSize = vectors[0].getSize();

        for (int i = 0; i < vectorSize; ++i) {
            double sum = 0;

            for (int j = 0; j < matrixVectorSize; ++j) {
                sum += vectors[i].getElement(j) * vector.getElement(i);
            }

            multiplyResult.setElement(i, sum);
        }

        return multiplyResult;
    }

    public void add(Matrix matrix) {
        int m = Math.max(vectors.length, matrix.getVectors().length);
        int n = Math.max(vectors[0].getSize(), matrix.getVectors()[0].getSize());

        Vector[] result = new Vector[m];

        for (int i = 0; i < m; ++i) {
            result[i] = new Vector(n);

            for (int j = 0; j < n; ++j) {

                double term1 = 0;
                double term2 = 0;

                if (vectors.length > i) {
                    if (vectors[i].getLength() > j) {
                        term1 = vectors[i].getElement(j);
                    }
                }

                if (matrix.getVectors().length > i) {
                    if (matrix.getVectors()[i].getLength() > j) {
                        term2 = matrix.getVectors()[i].getElement(j);
                    }
                }

                result[i].setElement(j, term1 + term2);
            }
        }

        vectors = result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < vectors.length; ++i) {
            stringBuilder.append(vectors[i].toString());

            if (i + 1 != vectors.length) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}