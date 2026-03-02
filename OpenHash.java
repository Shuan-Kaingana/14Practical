import java.util.*;

public class OpenHash{
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


        //hash function
        private int hash(K key) {
            if (key == null) return 0;
            return Math.abs(key.hashCode()) % capacity;
        }

        private put(K key, V value) {
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
        

}
