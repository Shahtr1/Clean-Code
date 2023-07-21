package avoid.comments;

import java.util.Arrays;

public class GeneratePrimesProblem {
    public static int[] generatePrimes(int maxValue) {
        if (maxValue >= 2) { // the only valid case
            int s = maxValue; // size of array
            boolean[] f = new boolean[s];
            int i;

//            initialize array to true
            for (i = 0; i < s; i++)
                f[i] = true;

//            get rid of known non-primes
            f[0] = f[1] = false;

//            sieve
            int j;
            for (i = 2; i < Math.sqrt(s); i++) {
                if (f[i]) { // if i is uncrossed, cross its multiples
                    for (j = 2 * i; j < s; j += i) {
                        f[j] = false; // multiple is not a prime
                    }
                }
            }

            // how many primes are there?
            int count = 0;
            for (i = 0; i < s; i++) {
                if (f[i])
                    count++; // bump count
            }

            int[] primes = new int[count];

//            move the primes into the result
            for (i = 0, j = 0; i < s; i++) {
                if (f[i]) // if prime
                    primes[j++] = i;
            }

            return primes; // return the primes
        } else
            return new int[0]; // return null array if bad input
    }

    public static void main(String[] args) {
        int[] primes = generatePrimes(16);
        System.out.println(Arrays.toString(primes));
    }
}
