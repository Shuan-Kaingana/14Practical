import java.util.*;

public class OpenHash{
    private static class Entry<K, V> {
        K key;
        V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private final int capacity;
        private final double loadFactor;
        private int size;

        private int hash(K key) {
            if (key == null) return 0;
            return Math.abs(key.hashCode()) % capacity;
        }
        private LinkedList<Entry<K, V>>[] buckets;
    }
    public OpenHash(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.buckets = new LinkedList[capacity];
        }
}