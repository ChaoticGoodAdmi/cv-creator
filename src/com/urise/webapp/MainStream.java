package com.urise.webapp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println("Задание 1:");
        int[] values1 = {1, 2, 3, 3, 2, 3};
        int[] values2 = {9, 8};
        System.out.println("Test 1: " + (minValue(values1) == 123));
        System.out.println("Test 2: " + (minValue(values2) == 89));

        System.out.println("Задание 2:");
        List<Integer> list1 = Arrays.stream(values1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(values2).boxed().collect(Collectors.toList());
        System.out.println("Test 1: " + (oddOrEven(list1).equals(Arrays.asList(1, 3, 3, 3))));
        System.out.println("Test 2: " + (oddOrEven(list2).equals(Collections.singletonList(8))));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .mapToObj(BigInteger::valueOf)
                .reduce((a, b) -> a.multiply(BigInteger.TEN).add(b))
                .orElse(BigInteger.ZERO)
                .intValue();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(integers.stream().reduce(0, Integer::sum) % 2 == 0 ? a -> a % 2 != 0 : a -> a % 2 == 0)
                .collect(Collectors.toList());
    }
}