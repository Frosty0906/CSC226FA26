package week10;

/**
 * Provided. Builds the three key sets the strategies race against.
 * Every key is positive and unique, and every dataset has the same number of keys.
 * The hash function for all strategies is: key % tableSize
 */
public final class Datasets {
    public static final int[] SIZES = {10_000, 30_000, 100_000, 200_000};
    public static final int DEFAULT_N = 100_000;

    public static final String[] NAMES = {
        "1. Collision on EVERY insert",
        "2. As few collisions as possible",
        "3. Half collide / half don't"
    };

    private Datasets() {}

    /** Prime, more than 2x n: load factor stays under 0.5 so quadratic probing always finds a slot. */
    public static int tableSize(int n) {
        for (int c = 2 * n + 1; ; c++) {
            boolean prime = true;
            for (int d = 2; (long) d * d <= c && prime; d++) {
                if (c % d == 0) prime = false;
            }
            if (prime) return c;
        }
    }

    public static long[] get(int which, int n) {
        int ts = tableSize(n);
        long[] keys = new long[n];
        for (int i = 0; i < n; i++) {
            switch (which) {
                // every key is a multiple of ts, so every key hashes to index 0
                case 0: keys[i] = (long) (i + 1) * ts; break;
                // keys 1..n each hash to their own index
                case 1: keys[i] = i + 1; break;
                // alternate: own index, then index 0
                default: keys[i] = (i % 2 == 0) ? (i / 2) + 1 : (long) ((i / 2) + 1) * ts;
            }
        }
        return keys;
    }
}
