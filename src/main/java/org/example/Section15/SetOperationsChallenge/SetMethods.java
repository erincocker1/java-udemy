package org.example.Section15.SetOperationsChallenge;

import java.util.*;

public class SetMethods {
    public static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    public static void sortAndPrint(String header, Collection<Task> collection,
                                     Comparator<Task> sorter) {

        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    public static <E> Set<E> getUnion(List<Set<E>> setList) {
        Set<E> union = new HashSet<>();
        for (Set<E> set: setList) {
            union.addAll(set);
        }
        return union;
    }

    public static <E> Set<E> getIntersect(Set<E> set1, Set<E> set2) {
        Set<E> intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    public static <E> Set<E> getDifference(Set<E> set1, Set<E> set2) {
        Set<E> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }
}
