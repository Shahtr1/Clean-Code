## Generating Prime Numbers:  using Sieve of Eratosthenes

1. **Getting Ready**: Imagine we have a list of numbers from 2 up to the maximum value we want to check for prime
   numbers. Let's call this maximum value "maxValue." So, if we want to find prime numbers up to 16, our list would
   be [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16].

2. **Crossing Out Multiples**: Now, we start with the first number in the list, which is 2. We know that 2 is a prime
   number because it has no other divisors except 1 and itself. So, we keep 2 and cross out all its multiples in the
   list. We cross out 4, 6, 8, 10, 12, 14, and 16 because they are multiples of 2.

3. **Moving to the Next Number**: Now, we move on to the next unmarked number, which is 3. We know 3 is a prime number
   too. So, we keep 3 and cross out all its multiples in the list. We cross out 6 and 9 because they are multiples of 3.

4. **Crossing Out More Multiples**: Next, we move to the next unmarked number, which is 4 (but we skip it since it's
   already marked). Then we move to 5, which is another prime number. We keep 5 and cross out 10 and 15 as they are
   multiples of 5.

5. **Determining the Limit**: At this point, we realize that we don't need to continue checking multiples beyond the
   square root of the maximum value (16 in our case). Any number greater than the square root will already have its
   multiples marked as crossed out by smaller prime numbers.

6. **Finishing Up**: After crossing out multiples, we are left with the remaining unmarked numbers in the list, which
   are the prime numbers we are looking for: 2, 3, 5, 7, 11, 13.

So, the process of finding prime numbers using this code involves crossing out the multiples of each prime number we
find, and then the remaining unmarked numbers are the prime numbers we want!
