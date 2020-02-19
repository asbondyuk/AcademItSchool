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

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        int vectorsCount = Math.max(matrix1.getVectors().length, matrix2.getVectors().length);
        int matrixVectorSize = Math.max(matrix1.getVectors()[0].getSize(), matrix2.getVectors()[0].getSize());

        Matrix sumMatrix = new Matrix(matrixVectorSize, vectorsCount);

        for (int i = 0; i < vectorsCount; ++i) {
            sumMatrix.vectors[i] = new Vector(matrixVectorSize);

            for (int j = 0; j < matrixVectorSize; ++j) {

                double term1 = 0;
                double term2 = 0;

                if (matrix1.getVectors().length > i) {
                    if (matrix1.getVectors()[i].getSize() > j) {
                        term1 = matrix1.getVectors()[i].getElement(j);
                    }
                }

                if (matrix2.getVectors().length > i) {
                    if (matrix2.getVectors()[i].getSize() > j) {
                        term2 = matrix2.getVectors()[i].getElement(j);
                    }
                }

                sumMatrix.vectors[i].setElement(j, term1 + term2);
            }
        }

        return sumMatrix;
    }

    public static Matrix difference(Matrix matrix1, Matrix matrix2) {
        int vectorsCount = Math.max(matrix1.getVectors().length, matrix2.getVectors().length);
        int matrixVectorSize = Math.max(matrix1.getVectors()[0].getSize(), matrix2.getVectors()[0].getSize());

        Matrix differenceMatrix = new Matrix(matrixVectorSize, vectorsCount);

        for (int i = 0; i < vectorsCount; ++i) {
            differenceMatrix.vectors[i] = new Vector(matrixVectorSize);

            for (int j = 0; j < matrixVectorSize; ++j) {

                double term1 = 0;
                double term2 = 0;

                if (matrix1.getVectors().length > i) {
                    if (matrix1.getVectors()[i].getSize() > j) {
                        term1 = matrix1.getVectors()[i].getElement(j);
                    }
                }

                if (matrix2.getVectors().length > i) {
                    if (matrix2.getVectors()[i].getSize() > j) {
                        term2 = matrix2.getVectors()[i].getElement(j);
                    }
                }

                differenceMatrix.vectors[i].setElement(j, term1 - term2);
            }
        }

        return differenceMatrix;
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
        int vectorsCount = Math.max(vectors.length, matrix.getVectors().length);
        int matrixVectorSize = Math.max(vectors[0].getSize(), matrix.getVectors()[0].getSize());

        Vector[] addResult = new Vector[vectorsCount];

        for (int i = 0; i < vectorsCount; ++i) {
            addResult[i] = new Vector(matrixVectorSize);
            System.out.println("i = " + i);

            for (int j = 0; j < matrixVectorSize; ++j) {

                double term1 = 0;
                double term2 = 0;

                if (vectors.length > i) {
                    if (vectors[i].getSize() > j) {
                        System.out.println("j = " + j);
                        term1 = vectors[i].getElement(j);
                    }
                }

                if (matrix.getVectors().length > i) {
                    if (matrix.getVectors()[i].getSize() > j) {
                        term2 = matrix.getVectors()[i].getElement(j);
                    }
                }

                addResult[i].setElement(j, term1 + term2);
            }
        }

        vectors = addResult;
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