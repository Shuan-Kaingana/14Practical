public class ChainedHash {
    private static class Entry {
        String key;
        String value;
        boolean isDeleted;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return "(" + key + " -> " + value + ")";
        }
    }

    // Fields for the hash table
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry[] table;
    private int size;
    private int capacity;
    private double loadFactor;
}