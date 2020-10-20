/*
Мирошниченко В.А.
группа: в3530904/80322
Дана матрица. В каждой строке найти наибольший элемент.
Из этих элементов найти наименьший и удалить ту строку, которой он принадлежит.
Вывести исходную и получившуюся матрицы в консоль.
*/

package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        int[][] matrix = inputMatrix();

        System.out.println("\nСгенерированная матрица:");

        printMatrix(matrix);

        int[] maxValInd = indexesOfMaxValueInMatrixRows(matrix);

        int row = indOfMinValueOfMaxElem(matrix, maxValInd);

        int[][] newMatrix = deleteRow(matrix, row);

        System.out.println("\nНовая матрица:");

        printMatrix(newMatrix);

    }

    private static int[][] inputMatrix() {
        Scanner inputVal = new Scanner(System.in);
        int row, col;

        System.out.print("Ведите колличество строк: ");
        row = inputVal.nextInt();
        System.out.print("\nВведите колличество столбцов: ");
        col = inputVal.nextInt();

        int[][] matrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                matrix[i][j] = ThreadLocalRandom.current().nextInt(0, 100);
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static int[] indexesOfMaxValueInMatrixRows(int[][] matrix) {
        int[] indexes = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    indexes[i] = j;
                }
            }
        }

        return indexes;
    }

    private static int indOfMinValueOfMaxElem(int[][] matrix, int[] indOfMaxVal) {
        int indOfMinElem = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][indOfMaxVal[i]] < min) {
                min = matrix[i][indOfMaxVal[i]];
                indOfMinElem = i;
            }
        }

        return indOfMinElem;
    }

    private static int[][] deleteRow(int[][] matrix, int row) {
        int[][] newMatrix = new int[matrix.length-1][matrix[0].length];

        for(int i = 0; i < newMatrix.length; i++) {
            if(i < row) {
                newMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
            } else {
                newMatrix[i] = Arrays.copyOf(matrix[i+1], matrix[i+1].length);
            }
        }

        return newMatrix;
    }

}
