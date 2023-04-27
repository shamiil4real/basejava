package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

public class MainStream {

    // Вернуть минимальное возможное число, составленное из уникальных чисел array
    static public int minValue(int[] values) {
        return Integer.parseInt(Arrays.stream(values)
                .sorted()
                .distinct()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining()));
    }

    // Если сумма всех чисел в list нечетная -> удалить все нечетные и наоборот
    public static List<Integer> oddOrEven(List<Integer> integers) {
        // 1-й способ:
        return integers.stream()
                .collect(Collectors
                        .collectingAndThen(partitioningBy(i -> i % 2 != 0), result ->
                                result.get(true).size() % 2 == 0 ? result.get(true) : result.get(false)));

        /* 2-й способ:
        return integers.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(i -> i % 2 == 0,
                                Collectors.toList()),
                        Collectors.filtering(i -> i % 2 == 1,
                                Collectors.toList()),
                        (even, odd) -> odd.size() % 2 == 1 ? even : odd));
         */
    }

    public static int sumOfElements(List<Integer> input) {
        return input.stream()
                .mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 1, 3, 3, 2, 3};
        int[] nums2 = new int[]{9, 8};
        System.out.println("array: " + Arrays.toString(nums1) + ", smallest number: " + minValue(nums1));
        System.out.println("array: " + Arrays.toString(nums2) + ", smallest number: " + minValue(nums2));

        List<Integer> list1 = List.of(1, 1, 1, 2, 2, 1);
        List<Integer> list2 = List.of(1, 1, 1, 2, 2, 2);
        System.out.println("original: " + list1 + ", sum: " + sumOfElements(list1) + ", new: " + oddOrEven(list1));
        System.out.println("original: " + list2 + ", sum: " + sumOfElements(list2) + ", new: " + oddOrEven(list2));
    }
}
