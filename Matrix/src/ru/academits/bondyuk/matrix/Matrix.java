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

    public Matrix(double[][] arrays) {
        if (arrays.length <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше нуля, получено: " + arrays.length);
        }

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
        if (rows.length == 0) {
            throw new IllegalArgumentException("Размер матрицы должен быть больше нуля");
        }

        int rowsCount = rows.length;
        this.rows = new Vector[rowsCount];

        System.arraycopy(rows, 0, this.rows, 0, rowsCount);
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
        int columnsCount = getColumnsCount();
        Vector[] transposedVectors = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; ++i) {
            transposedVectors[i] = getColumn(i);
        }

        rows = transposedVectors;
    }

    public void multiply(double number) {
        for (Vector vector : rows) {
            vector.multiply(number);
        }
    }

    // ToDo выяснить в чем проблема
    public Vector multiply(Vector vector) {
        if (rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Для умножени вектора на матрицу число столбцов матрицы должно совпадать " +
                    "с размерностью вектора. Число столбцов: " + rows[0].getSize() + ", размерность вектора: " + vector.getSize());
        }

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
            throw new IllegalArgumentException("Для выполнения разности размерность матриц должна совпадать. " +
                    "Начальная матрица: строк: " + rowsCount + "столбцов: " + columnsCount +
                    ". Вычитаемая матрица: строк: " + addedMatrixRowsCount + "столбцов: " + addedMatrixColumnsCount);
        }

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j <= addedMatrixRowsCount; ++j) {
                rows[i].setElement(j, rows[i].getElement(j) + matrix.rows[i].getElement(j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int addedMatrixRowsCount = matrix.getRowsCount();
        int addedMatrixColumnsCount = matrix.getColumnsCount();

        if (rowsCount != addedMatrixRowsCount || columnsCount != addedMatrixColumnsCount) {
            throw new IllegalArgumentException("Для выполнения разности размерность матриц должна совпадать. " +
                    "Начальная матрица: строк: " + rowsCount + "столбцов: " + columnsCount +
                    ". Вычитаемая матрица: строк: " + addedMatrixRowsCount + "столбцов: " + addedMatrixColumnsCount);
        }

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j <= addedMatrixRowsCount; ++j) {
                rows[i].setElement(j, rows[i].getElement(j) - matrix.rows[i].getElement(j));
            }
        }
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

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);

        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);

        matrix.subtract(matrix2);

        return matrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        int matrix1ColumnsCount = matrix1.getColumnsCount();
        int matrix2RowsCount = matrix2.getRowsCount();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Для выполнения умножения число столбцов матрицы 1 должно быть равно числу строк матрицы 2. "
                    + "Получено число столбцов: " + matrix1ColumnsCount + ". Получено число строк: " + matrix2RowsCount);
        }

        int multiplyMatrixRowsCount = matrix1.getRowsCount();
        int multiplyMatrixColumnsCount = matrix2.getColumnsCount();

        Matrix multiplyMatrix = new Matrix(multiplyMatrixRowsCount, multiplyMatrixColumnsCount);

        for (int i = 0; i < multiplyMatrixRowsCount; ++i) {
            for (int j = 0; j < multiplyMatrixColumnsCount; ++j) {
                int sum = 0;

                for (int k = 0; k < matrix1ColumnsCount; ++k) {
                    sum += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                multiplyMatrix.rows[i].setElement(j, sum);
            }
        }

        return multiplyMatrix;
    }
}