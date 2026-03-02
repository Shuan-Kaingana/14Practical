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

    public ChainedHash() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ChainedHash(int defaultCapacity, double defaultLoadFactor) {
        this.capacity = defaultCapacity;
        this.loadFactor = defaultLoadFactor;
        this.table = new Entry[capacity];
        this.size = 0;
    }
    public int hash(String key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(String key, String value) {
        if (key == null) {
            return;
        }
        
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry(key, value));
        size++;
    }

    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public String remove(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        if (buckets[index] != null) {
            Iterator<Entry> iterator = buckets[index].iterator();
            while (iterator.hasNext()) {
                Entry entry = iterator.next();
                if (entry.key.equals(key)) {
                    String value = entry.value;
                    iterator.remove();
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size >= capacity * loadFactor;
    }

    public boolean isInTable(String key) {
        return lookup(key) != null;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OpenHash[capacity=").append(capacity).append(", size=").append(size).append("]\n");
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null && !buckets[i].isEmpty()) {
                sb.append("Bucket[").append(i).append("]: ");
                for (Entry entry : buckets[i]) {
                    sb.append(entry).append(" ");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
