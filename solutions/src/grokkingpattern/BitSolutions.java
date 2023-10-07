package grokkingpattern;

public class BitSolutions {
    public static void main(String[] args) {
        BitSolutions bs = new BitSolutions();
       /* System.out.println(bs.countBits(100));
        System.out.println(bs.isEven(100));
        System.out.println(bs.multiply(100, 14));
        System.out.println(bs.multiplyRecursion(100, 14));
        */
        int k = 10;
        //k = 1<<k;
        System.out.println(k);
        k ^= 1<<k;
        System.out.println(k);
    }

    int countBits(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n >>= 1;
        }
        return count;
    }

    boolean isEven(int n) {
        if ((n ^ 1) == n + 1)
            return true;
        else
            return false;
    }

    long multiply(int a, int b) {
        long result = 0;
        while (b > 0) {
            // b is odd
            if ((b ^ 1) == b - 1) {
                result += a;
            }
            a <<= 1; // multiple a;
            b >>= 1; // divide b
        }
        return result;
    }

    long multiplyRecursion(int a, int b) {
        // base case
        if (b == 1)
            return a;

        if ((b ^ 1) == b - 1)
            return a + multiplyRecursion(2 * a, b / 2);
        return multiplyRecursion(2 * a, b / 2);
    }
}