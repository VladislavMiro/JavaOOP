/*
Мирошниченко В.А.
группа: в3530904/80322
Пользователь вводит некоторое число. Записать его цифры в стек.
Вывести число, у которого цифры идут в обратном порядке.
Предусмотреть возможность введения произвольного количества чисел.
*/

package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    var stack = inputNumber();
        System.out.println("Числа в стеке:");
        outputStack(stack);
    }

    private static Stack[] inputNumber() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.println("Введите числа:");
        boolean exit = false;

        while(!exit) {
            try {
                int number = scanner.nextInt();
                arr.add(number);
            } catch(Exception error) {
                exit = true;
            }
        }

        return getStackArray(arr);
    }

    private static Stack[] getStackArray(ArrayList<Integer> array) {
        Stack[] stack = new Stack[array.size()];

        for (int i = 0; i < array.size(); i++) {
            stack[i] = new Stack();
            for (char ch: array.get(i).toString().toCharArray()) {
                stack[i].push(Character.getNumericValue(ch));
            }
            /*while(array.get(i) != 0) {
                stack[i].push(array.get(i)%10);
                array.set(i, array.get(i)/10);
            }*/
        }

        return stack;
    }

    private static void outputStack(Stack[] stack) {
        for (Stack number : stack) {
            while(!number.isEmpty()) {
                System.out.print(number.pop());
            }
            System.out.println();
        }
    }

}
