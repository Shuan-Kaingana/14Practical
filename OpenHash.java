import java.util.*;

public class OpenHash<K, V> {
        private static class Entry<K, V> {
            K key;
            V value;

            @SuppressWarnings("unused")
            Entry(K key, V value) {
                this.key   = key;
                this.value = value;
            }
            @Override
            public String toString() {
                return "(" + key + " -> " + value + ")";
            }
        }

        //fields for the hash table
        private static final int    DEFAULT_CAPACITY    = 16;
        private static final double DEFAULT_LOAD_FACTOR = 0.75;
        private LinkedList<Entry<K, V>>[] buckets;
        private final int capacity;
        private final double loadFactor;
        private int size;

        // constructors
        public OpenHash() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }

        @SuppressWarnings("unchecked")
        public OpenHash(int capacity, double loadFactor) {
            this.capacity = capacity;
            this.loadFactor = loadFactor;
            this.buckets = new LinkedList[capacity];
            this.size = 0;
        }


        //hash function
        private int hash(K key) {
            if (key == null) return 0;
            return Math.abs(key.hashCode()) % capacity;
        }

        private void insert(K key, V value) {
            int index = hash(key);
            if (buckets[index] == null) {
                buckets[index] = new LinkedList<>();
            }
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    entry.value = value; // Update existing key
                    return;
                }
            }
            buckets[index].add(new Entry<>(key, value)); // Add new key-value pair
            size++;
        }

        private V search(K key) {
            int index = hash(key);
            if (buckets[index] != null) {
                for (Entry<K, V> entry : buckets[index]) {
                    if (entry.key.equals(key)) {
                        return entry.value; // Key found
                    }
                }
            }
            return null; // Key not found
        }

        private void remove(K key) {
            int index = hash(key);
            if (buckets[index] != null) {
                Iterator<Entry<K, V>> iterator = buckets[index].iterator();
                while (iterator.hasNext()) {
                    Entry<K, V> entry = iterator.next();
                    if (entry.key.equals(key)) {
                        iterator.remove(); // Remove the entry
                        size--;
                        return;
                    }
                }
            }
        }

        private boolean isEmpty() {
            return size == 0;
        }

        private boolean isFull() {
            return size >= capacity * loadFactor;
        }

        private isInTable(K key) {
            return search(key) != null;
        }


}
