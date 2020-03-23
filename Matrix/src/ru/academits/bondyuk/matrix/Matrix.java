package ru.academits.bondyuk.matrix;

import ru.academits.bondyuk.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше нуля, получено: " + rowsCount);
        }

        if (columnsCount < 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше нуля, получено: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowCount = matrix.rows.length;
        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(int vectorDimension, double[] array) {
//        if (vectorDimension <= 0) {
//            throw new IllegalArgumentException("Количество строк должно быть больше нуля, получено: " + rowsCount);
//        }
//
//        int rowsCount = arrays.length;
//        rows = new Vector[rowsCount];
//
//        int matrixVectorMaxSize = arrays[0].length;
//
//        for (int i = 1; i < rowsCount; ++i) {
//            if (matrixVectorMaxSize < arrays[i].length) {
//                matrixVectorMaxSize = arrays[i].length;
//            }
//        }
//
//        for (int i = 0; i < rowsCount; ++i) {
//            rows[i] = new Vector(arrays[matrixVectorMaxSize]);
//
//            for (int j = 0; j < matrixVectorMaxSize; ++j) {
//                if (arrays[i].length > j) {
//                    rows[i].setElement(j, arrays[i][j]);
//                } else {
//                    rows[i].setElement(j, 0);
//                }
//            }
//        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Размер матрицы должен быть больше нуля");
        }

        int rowsCount = rows.length;
        this.rows = new Vector[rowsCount];

        System.arraycopy(rows, 0, this.rows, 0, rowsCount);
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix1.getColumnsCount();

        Matrix matrix = new Matrix(rowsCount, columnsCount);

        matrix.add(matrix1);
        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix1.getColumnsCount();

        Matrix matrix = new Matrix(rowsCount, columnsCount);

        matrix.add(matrix1);
        matrix.subtract(matrix2);

        return matrix;
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows.length > 0 ? rows[0].getSize() : 0;
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс массива должен быть больше нуля, получен: " + index);
        }

        if (rows.length < index) {
            throw new IllegalArgumentException("Не допустимое значение индекса массива: максимальное значение: " + (rows.length - 1)
                    + ", полученое: " + index);
        }

        return rows[index];
    }

    public void setRow(Vector row, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс массива должен быть больше нуля, получен: " + index);
        }

        if (rows.length < index) {
            throw new IllegalArgumentException("Не допустимое значение индекса массива: максимальное значение: " + (rows.length - 1)
                    + ", полученое: " + index);
        }

        int columnsCount = rows[0].getSize();
        int setRowSize = row.getSize();

        for (int j = 0; j < columnsCount; ++j) {
            if (setRowSize > j) {
                rows[index].setElement(j, row.getElement(j));
            } else {
                rows[index].setElement(j, 0);
            }
        }
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0) {
            throw new IllegalArgumentException("Значение индекса столбца должен быть неотрицательным, получено: " + columnIndex);
        }

        if (rows[0].getSize() < columnIndex) {
            throw new IllegalArgumentException("Не допустимое значение индекса столбца: максимальное значение: " + (rows.length - 1)
                    + ", получено: " + columnIndex);
        }

        Vector vector = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            vector.setElement(i, rows[i].getElement(columnIndex));
        }

        return vector;
    }

    public void transpose() {
        int columnsCount = rows[0].getSize();

        Vector[] transposedVectors = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; ++i) {
            transposedVectors[i] = getColumn(i);
        }

        rows = transposedVectors;
    }

    public void multiply(double number) {
        int columnsCount = rows[0].getSize();

        for (Vector vector : rows) {
            vector.multiply(number);
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
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int addedMatrixRowsCount = matrix.getRowsCount();
        int addedMatrixColumnsCount = matrix.getColumnsCount();

        if (rowsCount != addedMatrixRowsCount || columnsCount != addedMatrixColumnsCount) {
            throw new IllegalArgumentException("Для выполнения сложения размерность матриц должна совпадать");
        }

        int resultMatrixRowsCount = rowsCount;
        int resultMatrixColumnsCount = addedMatrixRowsCount;

        Vector[] resultMatrix = new Vector[resultMatrixRowsCount];

        for (int i = 0; i < resultMatrixRowsCount; ++i) {
            resultMatrix[i] = new Vector(resultMatrixColumnsCount);

            for (int j = 0; j < resultMatrixColumnsCount; ++j) {
                resultMatrix[i].setElement(j, rows[i].getElement(j) + matrix.rows[i].getElement(j));
            }
        }

        rows = resultMatrix;
    }

    public void subtract(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int addedMatrixRowsCount = matrix.getRowsCount();
        int addedMatrixColumnsCount = matrix.getColumnsCount();

        if (rowsCount != addedMatrixRowsCount || columnsCount != addedMatrixColumnsCount) {
            throw new IllegalArgumentException("Для выполнения разности размерность матриц должна совпадать");
        }

        Vector[] resultMatrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            resultMatrix[i] = new Vector(addedMatrixRowsCount);

            for (int j = 0; j < addedMatrixRowsCount; ++j) {
                resultMatrix[i].setElement(j, rows[i].getElement(j) - matrix.rows[i].getElement(j));
            }
        }

        rows = resultMatrix;
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
//                    if (matrix1.rows[i].getSize() < k) {
//                        temp1 = matrix1.rows[i].getElement(k);
//                    }
//
//                    if (matrix2.rows.length < k) {
//                        temp2 = matrix2.rows[k].getElement(j);
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