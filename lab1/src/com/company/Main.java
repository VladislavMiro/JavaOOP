/*
Лабораторная работа 1
Выполнил: Мирошниченко В.А.
руппа: в3530904/80322
Дан массив без повторяющихся элементов. Перемешать его элементы случайным образом так,
чтобы каждый элемент оказался на новом месте. Вывести исходный и получившийся массивы в консоль.
*/

package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int size = inputSize();

        int[] array = generateArray(size);

        outputArray(array, "Original array:");

        var newArray = shuffleSattolo(array);

        outputArray(newArray, "Shuffled array:");

    }
//Метод ввода размера массива с клавиатуры
    private static int inputSize() {

        Scanner inputVal = new Scanner(System.in);

        System.out.print("Input size of array: ");
        var size = inputVal.nextInt();
        System.out.print("\n");

        return size;
    }
//метод создает массив и заполняет его случайными целочисленными значениями
    private static int[] generateArray(int size) {

        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }
//Метод выводит массив и сообщение
    private static void outputArray(int[] array, String message) {

        System.out.print(message + " ");
        for (int i : array)
            System.out.print(i + " ");
        System.out.println("\n");

    }
//Перетасовка массива алгоритмом Саттоло.
    private static int[] shuffleSattolo(int[] array) {

        Random random = new Random();
        int i = array.length;

        while(i > 1) {
            int j = random.nextInt(--i);
            var tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }

        return array;
    }

}
