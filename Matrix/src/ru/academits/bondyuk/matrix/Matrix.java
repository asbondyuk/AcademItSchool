package ru.academits.bondyuk.matrix;

import ru.academits.bondyuk.vector.Vector;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int linesCount, int columnsCount) {
        vectors = new Vector[linesCount];

        for (int i = 0; i < linesCount; ++i) {
            vectors[i] = new Vector(columnsCount);
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
        int[] matrixSize = matrix1.getSize();
        int linesCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Matrix matrix = new Matrix(linesCount, columnsCount);

        matrix.add(matrix1);
        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        int[] matrixSize = matrix1.getSize();
        int linesCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Matrix matrix = new Matrix(linesCount, columnsCount);

        matrix.add(matrix1);
        matrix.subtract(matrix2);

        return matrix;
    }

    public Vector[] getVectors() {
        return vectors;
    }

    public int[] getSize() {
        int linesCount = vectors.length;
        int columnsCount = vectors[0].getSize();

        return new int[]{linesCount, columnsCount};
    }

    public Vector getVector(int index) {
        return vectors[index];
    }

    public void setVector(Vector vector, int index) {
        int columnsCount = vectors[0].getSize();
        int setVectorSize = vector.getSize();

        for (int j = 0; j < columnsCount; ++j) {
            if (setVectorSize > j) {
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
        int columnsCount = vectors[0].getSize();

        Vector[] transposedVector = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; ++i) {
            transposedVector[i] = getColumn(i);
        }

        vectors = transposedVector;
    }

    public void multiply(double number) {
        int columnsCount = vectors[0].getSize();

        for (Vector vector : vectors) {
            for (int j = 0; j < columnsCount; ++j) {
                vector.setElement(j, vector.getElement(j) * number);
            }
        }
    }

    public Vector multiply(Vector vector) {
        int vectorSize = vector.getSize();
        Vector multiplyResult = new Vector(vectorSize);
        int matrixColumnsCount = vectors[0].getSize();

        for (int i = 0; i < vectorSize; ++i) {
            double sum = 0;

            for (int j = 0; j < matrixColumnsCount; ++j) {
                sum += vectors[i].getElement(j) * vector.getElement(i);
            }

            multiplyResult.setElement(i, sum);
        }

        return multiplyResult;
    }

    public void add(Matrix matrix) {
        int[] matrixSize = getSize();
        int[] addedMatrixSize = matrix.getSize();

        if (matrixSize[0] != addedMatrixSize[0] || matrixSize[1] != addedMatrixSize[1]) {
            throw new IllegalArgumentException("Для выполнения сложения размерность матриц должна совпадать");
        }

        int linesCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Vector[] addMatrix = new Vector[linesCount];

        for (int i = 0; i < linesCount; ++i) {
            addMatrix[i] = new Vector(columnsCount);

            for (int j = 0; j < columnsCount; ++j) {
                addMatrix[i].setElement(j, vectors[i].getElement(j) + matrix.getVectors()[i].getElement(j));
            }
        }

        vectors = addMatrix;
    }

    public void subtract(Matrix matrix) {
        int[] matrixSize = getSize();
        int[] addedMatrixSize = matrix.getSize();

        if (matrixSize[0] != addedMatrixSize[0] || matrixSize[1] != addedMatrixSize[1]) {
            throw new IllegalArgumentException("Для выполнения разности размерность матриц должна совпадать");
        }

        int linesCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Vector[] subtractMatrix = new Vector[linesCount];

        for (int i = 0; i < linesCount; ++i) {
            subtractMatrix[i] = new Vector(columnsCount);

            for (int j = 0; j < columnsCount; ++j) {
                subtractMatrix[i].setElement(j, vectors[i].getElement(j) - matrix.getVectors()[i].getElement(j));
            }
        }

        vectors = subtractMatrix;
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

//    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
//        int[] matrix1Size = matrix1.getSize();
//        int[] matrix2Size = matrix2.getSize();
//
//        int matrix1LinesCount = matrix1Size[1];
//        int matrix2ColumnsCount = matrix1Size[0];
//
//        if (matrix1LinesCount != matrix2ColumnsCount) {
//            throw new IllegalArgumentException("Для выполнения умножения число столбцов матрицы 1 должно быть равно числу строк матрицы 2");
//        }
//
//        int multiplyMatrixLinesCount = matrix1Size[1];
//        int multiplyMatrixColumnsCount = matrix2Size[0];
//
//        Matrix multiplyMatrix = new Matrix(multiplyMatrixLinesCount, multiplyMatrixColumnsCount);
//
//        for (int i = 0; i < multiplyMatrixLinesCount; ++i) {
//            for (int j = 0; j < multiplyMatrixColumnsCount; ++j) {
//                int sum = 0;
//
//                for (int k = 0; k < matrix1LinesCount; ++k) {
//                    double temp1 = 0;
//                    double temp2 = 0;
//
//                    if (matrix1.getVectors()[i].getSize() < k) {
//                        temp1 = matrix1.getVectors()[i].getElement(k);
//                    }
//
//                    if (matrix2.getVectors().length < k) {
//                        temp2 = matrix2.getVectors()[k].getElement(j);
//                    }
//
//                    System.out.println(temp1 + ", " + temp2);
//                    sum += (temp1 + temp2);
//                }
//
//                multiplyMatrix.vectors[i].setElement(j, sum);
//            }
//        }
//
//        return multiplyMatrix;
//    }
}