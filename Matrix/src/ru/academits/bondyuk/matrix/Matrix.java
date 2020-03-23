package ru.academits.bondyuk.matrix;

import ru.academits.bondyuk.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowCount = matrix.getRows().length;
        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(matrix.getRows()[i]);
        }
    }

    public Matrix(double[][] arrays) {
        int rowsCount = arrays.length;
        rows = new Vector[rowsCount];

        int matrixVectorMaxSize = arrays[0].length;

        for (int i = 1; i < rowsCount; ++i) {
            if (matrixVectorMaxSize < arrays[i].length) {
                matrixVectorMaxSize = arrays[i].length;
            }
        }

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(arrays[matrixVectorMaxSize]);

            for (int j = 0; j < matrixVectorMaxSize; ++j) {
                if (arrays[i].length > j) {
                    rows[i].setElement(j, arrays[i][j]);
                } else {
                    rows[i].setElement(j, 0);
                }
            }
        }
    }

    public Matrix(Vector[] rows) {
        int rowsCount = rows.length;
        this.rows = new Vector[rowsCount];

        System.arraycopy(rows, 0, this.rows, 0, rowsCount);
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        int[] matrixSize = matrix1.getSize();
        int rowsCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Matrix matrix = new Matrix(rowsCount, columnsCount);

        matrix.add(matrix1);
        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        int[] matrixSize = matrix1.getSize();
        int rowsCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Matrix matrix = new Matrix(rowsCount, columnsCount);

        matrix.add(matrix1);
        matrix.subtract(matrix2);

        return matrix;
    }

    public Vector[] getRows() {
        return rows;
    }

    public int[] getSize() {
        int rowsCount = rows.length;
        int columnsCount = rows[0].getSize();

        return new int[]{rowsCount, columnsCount};
    }

    public Vector getVector(int index) {
        return rows[index];
    }

    public void setVector(Vector vector, int index) {
        int columnsCount = rows[0].getSize();
        int setRowSize = vector.getSize();

        for (int j = 0; j < columnsCount; ++j) {
            if (setRowSize > j) {
                rows[index].setElement(j, vector.getElement(j));
            } else {
                rows[index].setElement(j, 0);
            }
        }
    }

    public Vector getColumn(int columnIndex) {
        Vector vector = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            vector.setElement(i, rows[i].getElement(columnIndex));
        }

        return vector;
    }

    public void transpose() {
        int columnsCount = rows[0].getSize();

        Vector[] transposedVector = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; ++i) {
            transposedVector[i] = getColumn(i);
        }

        rows = transposedVector;
    }

    public void multiply(double number) {
        int columnsCount = rows[0].getSize();

        for (Vector vector : rows) {
            for (int j = 0; j < columnsCount; ++j) {
                vector.setElement(j, vector.getElement(j) * number);
            }
        }
    }

    public Vector multiply(Vector vector) {
        int rowSize = vector.getSize();
        Vector multiplyResult = new Vector(rowSize);
        int matrixColumnsCount = rows[0].getSize();

        for (int i = 0; i < rowSize; ++i) {
            double sum = 0;

            for (int j = 0; j < matrixColumnsCount; ++j) {
                sum += rows[i].getElement(j) * vector.getElement(i);
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

        int rowsCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Vector[] addMatrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            addMatrix[i] = new Vector(columnsCount);

            for (int j = 0; j < columnsCount; ++j) {
                addMatrix[i].setElement(j, rows[i].getElement(j) + matrix.getRows()[i].getElement(j));
            }
        }

        rows = addMatrix;
    }

    public void subtract(Matrix matrix) {
        int[] matrixSize = getSize();
        int[] addedMatrixSize = matrix.getSize();

        if (matrixSize[0] != addedMatrixSize[0] || matrixSize[1] != addedMatrixSize[1]) {
            throw new IllegalArgumentException("Для выполнения разности размерность матриц должна совпадать");
        }

        int rowsCount = matrixSize[0];
        int columnsCount = matrixSize[1];

        Vector[] subtractMatrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            subtractMatrix[i] = new Vector(columnsCount);

            for (int j = 0; j < columnsCount; ++j) {
                subtractMatrix[i].setElement(j, rows[i].getElement(j) - matrix.getRows()[i].getElement(j));
            }
        }

        rows = subtractMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < rows.length; ++i) {
            stringBuilder.append(rows[i].toString());

            if (i + 1 != rows.length) {
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
//        int matrix1RowsCount = matrix1Size[1];
//        int matrix2ColumnsCount = matrix1Size[0];
//
//        if (matrix1RowsCount != matrix2ColumnsCount) {
//            throw new IllegalArgumentException("Для выполнения умножения число столбцов матрицы 1 должно быть равно числу строк матрицы 2");
//        }
//
//        int multiplyMatrixRowsCount = matrix1Size[1];
//        int multiplyMatrixColumnsCount = matrix2Size[0];
//
//        Matrix multiplyMatrix = new Matrix(multiplyMatrixRowsCount, multiplyMatrixColumnsCount);
//
//        for (int i = 0; i < multiplyMatrixRowsCount; ++i) {
//            for (int j = 0; j < multiplyMatrixColumnsCount; ++j) {
//                int sum = 0;
//
//                for (int k = 0; k < matrix1RowsCount; ++k) {
//                    double temp1 = 0;
//                    double temp2 = 0;
//
//                    if (matrix1.getRows()[i].getSize() < k) {
//                        temp1 = matrix1.getRows()[i].getElement(k);
//                    }
//
//                    if (matrix2.getRows().length < k) {
//                        temp2 = matrix2.getRows()[k].getElement(j);
//                    }
//
//                    System.out.println(temp1 + ", " + temp2);
//                    sum += (temp1 + temp2);
//                }
//
//                multiplyMatrix.rows[i].setElement(j, sum);
//            }
//        }
//
//        return multiplyMatrix;
//    }
}