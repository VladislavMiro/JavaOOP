/*
Лабораторная работа 7
Выполнил: Мирошниченко В.А.
Группа: в3530904/80322
С использованием streamAPI реализовать следующие методы:
a.	метод, возвращающий среднее значение списка целых чисел
b.	метод, приводящий все строки в списке в верхний регистр
c.	метод возвращающий список квадратов всех уникальных элементов списка
*/

package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        System.out.println(midVal(list));

        List<String> strList = Arrays.asList("ваывывмывывиаи","ваывывмывывиаи","ваывывмывывиаи","ваывывмывывиаи");
        System.out.println(getUpperRegister(strList));

        List<Integer> list1 = Arrays.asList(1,1,2,3,4,5,6,6,7,8,9);
        System.out.println(getPowOfElem(list1));

    }

    private static double midVal(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum) / (double) list.size();
    }

    private static List<String> getUpperRegister(List<String> list) {
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    private static List<Integer> getPowOfElem(List<Integer> list) {
        return list.stream().filter(x -> Collections.frequency(list, x) == 1).map(x -> x*x).collect(Collectors.toList());
    }

}