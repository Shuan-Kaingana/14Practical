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

    public ChainedHash(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public int hash(String key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % capacity;
    }

    private int probe(int index, String key) {
        int i = index;
        int count = 0;
        
        while (count < capacity) {
            if (table[i] == null || table[i].isDeleted) {
                return i;
            }
            if (table[i].key.equals(key)) {
                return i;
            }
            i = (i + 1) % capacity;
            count++;
        }
        
        return -1;
    }

    public void insert(String key, String value) {
        if (key == null) {
            return;
        }
        
        int index = hash(key);
        int probeIndex = probe(index, key);
        
        if (probeIndex == -1) {
            System.err.println("Hash table is full!");
            return;
        }
        if (table[probeIndex] != null && !table[probeIndex].isDeleted) {
            table[probeIndex].value = value;
        } else {
            table[probeIndex] = new Entry(key, value);
            size++;
        }
    }

    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        int i = index;
        int count = 0;
        
        while (count < capacity) {
            if (table[i] == null) {
                return null;
            }
            
            if (!table[i].isDeleted && table[i].key.equals(key)) {
                return table[i].value;
            }

            i = (i + 1) % capacity;
            count++;
        }

        return null;
    }

    public String remove(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        int i = index;
        int count = 0;
        
        while (count < capacity) {
            if (table[i] == null) {
                return null;
            }
            
            if (!table[i].isDeleted && table[i].key.equals(key)) {
                String value = table[i].value;
                table[i].isDeleted = true;
                size--;
                return value;
            }

            i = (i + 1) % capacity;
            count++;
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

    public double getLoadFactor() {
        return (double) size / capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClosedHash[capacity=").append(capacity)
          .append(", size=").append(size)
          .append(", load_factor=").append(String.format("%.2f", getLoadFactor()))
          .append("]\n");
        
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !table[i].isDeleted) {
                sb.append("[").append(i).append("]: ").append(table[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
