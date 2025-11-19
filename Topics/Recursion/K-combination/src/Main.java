import java.util.Scanner;

public class Main {

    public static int comb(int n, int k) {
        // base cases
        if (k == 0) {
            return 1;          // C(n, 0) = 1
        }
        if (k > n) {
            return 0;          // C(n, k) = 0 when k > n
        }
        // optional optimization: C(n, n) = 1
        if (k == n) {
            return 1;
        }
        // recursive case: C(n, k) = C(n-1, k) + C(n-1, k-1)
        return comb(n - 1, k) + comb(n - 1, k - 1);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        System.out.println(comb(n, k));
    }
}
