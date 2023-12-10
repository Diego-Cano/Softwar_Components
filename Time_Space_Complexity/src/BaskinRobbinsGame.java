import java.util.HashMap;
import java.util.Map;

public class BaskinRobbinsGame {

    // Task 1: Recursive Solution
    public static int baskin(int n) {
        // Base case: if n is less than 0, there are no ways to play
        if (n < 0) {
            return 0;
        }

        // Base case: if n is 0, there is one way to play (no move)
        if (n == 0) {
            return 1;
        }

        // Recursive case: sum up the possibilities for the next moves
        int ways = 0;
        for (int i = 1; i <= 2; i++) {
            ways += baskin(n - i);
        }

        return ways;
    }

    // Task 2: Optimize with Memoization
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int baskinMemo(int n) {
        // Base case: if n is less than 0, there are no ways to play
        if (n < 0) {
            return 0;
        }

        // Base case: if n is 0, there is one way to play (no move)
        if (n == 0) {
            return 1;
        }

        // Check if the result for the current n is already memoized
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int ways = 0;
        for (int i = 1; i <= 2; i++) {
            ways += baskinMemo(n - i);
        }

        // Memoize the result for the current n
        memo.put(n, ways);

        return ways;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();

        // Example usage: counting ways to reach 31 in the Baskin Robbins game
        int waysToReach31 = baskin(31);
        System.out.println("Number of ways to play Baskin Robbins 31: " + waysToReach31);

        // Example usage with memoization
        int waysToReach31Memo = baskinMemo(31);
        System.out.println("Number of ways to play Baskin Robbins 31 with memoization: " + waysToReach31Memo);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Execution Time: " + executionTime + " milliseconds");
        System.out.println("Used Memory: " + usedMemory + " bytes");
    }
}