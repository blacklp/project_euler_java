package main;

import java.util.Set;
import java.util.TreeSet;

public class Problem24 {
    public String lexicographicPermutations(int numDigits, int searched) {
        long numPermutations = factorial(numDigits);
        Set<Integer> allowedDigits = new TreeSet<Integer>();
        for (int i = 0; i < numDigits; i++) {
            allowedDigits.add(i);
        }
        String result = getPermutationDigits(numPermutations, numDigits, searched, 0, numDigits, allowedDigits);
        return result;
    }

    private String getPermutationDigits(long numPermutations, int numDigits, int searched, int offset, int numPossibleDigits,
            Set<Integer> allowedDigits) {
        long numPermStartingWithEachDigit = numPermutations / numDigits;
        Integer chosenDigit = 0;
        for (Integer digit : allowedDigits) {
            offset += numPermStartingWithEachDigit;
            if (searched <= offset) {
                chosenDigit = digit;
                break;
            }
        }
        offset -= numPermStartingWithEachDigit;
        if (numDigits > 1) {
            allowedDigits.remove(chosenDigit);
            return chosenDigit + getPermutationDigits(factorial(numDigits - 1), numDigits - 1, searched, offset, numPossibleDigits,
                    allowedDigits);
        }
        return chosenDigit + "";
    }

    private long factorial(int num) {
        if (num == 1 || num == 0) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        Problem24 o = new Problem24();
        String result = o.lexicographicPermutations(10, 1000000);
        System.out.println("result: " + result);
    }
}
