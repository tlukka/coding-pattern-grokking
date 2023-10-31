package facebook;

import java.util.Arrays;

public class NumberCyclesinArray {
    // number of cycles in a given array of integers

    // Given an array of size N which contains integers from range 0 to N-1. (No duplicates).
    // Write a program to find the number of cycles in the array.

    // Cycles in Array: Since the array is of size N and elements are from 0 to N-1 without any duplicates means all
    // the elements appear exactly once. Create a graph with n vertices. Create an edge from vertex Vi to Vj
    // if the element at position i should be in position j in the sorted ordering, With the logic above find
    // the number of cycles in the given array. See the image below for better understanding.

    public static void main(String[] args) {
        NumberCyclesinArray sl = new NumberCyclesinArray();
        int input[] = {3, 2, 1, 0};
        System.out.println("Given Array : " + Arrays.toString(input) + " No of cycles: " + sl.findCycles(input));
        int input1[] = {2, 0, 1};
        System.out.println("Given Array : " + Arrays.toString(input) + " No of cycles: " + sl.findCycles(input1));
        int input2[] = {1, 3, 4, 0, 2, 6, 5};
        System.out.println("Given Array : " + Arrays.toString(input) + " No of cycles: " + sl.findCycles(input2));
    }

    static class Pair {
        int value;
        int index;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    int findCycles(int[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(i, arr[i]);
        }

        int noCycles = 0;
        for (int i = 0; i < n; i++) {
            if (pairs[i].value != i) {
                if (!visited[i]) {
                    dfsCycles(visited, pairs, i);
                    noCycles++;
                }
            }
        }
        return noCycles;
    }

    void dfsCycles(boolean[] visited, Pair[] pairs, int start) {
        visited[start] = true;
        int next = pairs[start].value;
        if (!visited[next]) {
            dfsCycles(visited, pairs, next);
        }
        return;
    }
}
