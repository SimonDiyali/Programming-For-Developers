package QuestionNo4.LFUCache;

import java.util.*;

public class LFUCache<K, V> {
    private final int capacity;
    private int minFrequency;
    private int size;
    private final Map<K, V> keyToValue;
    private final Map<K, Integer> keyToFrequency;
    private final Map<Integer, LinkedHashSet<K>> frequencyToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.keyToValue = new HashMap<>();
        this.keyToFrequency = new HashMap<>();
        this.frequencyToKeys = new HashMap<>();
        this.frequencyToKeys.put(1, new LinkedHashSet<>());
    }

    public V get(K key) {
        if (!keyToValue.containsKey(key)) {
            return null;
        }

        int frequency = keyToFrequency.get(key);
        keyToFrequency.put(key, frequency + 1);
        frequencyToKeys.get(frequency).remove(key);

        if (frequency == minFrequency && frequencyToKeys.get(frequency).isEmpty()) {
            minFrequency++;
        }

        if (!frequencyToKeys.containsKey(frequency + 1)) {
            frequencyToKeys.put(frequency + 1, new LinkedHashSet<>());
        }

        frequencyToKeys.get(frequency + 1).add(key);
        return keyToValue.get(key);
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        if (keyToValue.containsKey(key)) {
            keyToValue.put(key, value);
            get(key);
            return;
        }

        if (keyToValue.size() >= capacity) {
            K keyToRemove = frequencyToKeys.get(minFrequency).iterator().next();
            frequencyToKeys.get(minFrequency).remove(keyToRemove);
            keyToValue.remove(keyToRemove);
            keyToFrequency.remove(keyToRemove);
        }

        keyToValue.put(key, value);
        keyToFrequency.put(key, 1);
        minFrequency = 1;
        frequencyToKeys.get(1).add(key);
    }

    public V remove(K key) {
        if (!keyToValue.containsKey(key)) {
            return null;
        }
        V value = keyToValue.remove(key);
        int frequency = keyToFrequency.remove(key);
        frequencyToKeys.get(frequency).remove(key);
        if (frequencyToKeys.get(frequency).isEmpty()) {
            frequencyToKeys.remove(frequency);
            if (minFrequency == frequency) {
                minFrequency++;
            }
        }
        size--;
        return value;
    }

    public Iterable<Entry<K,V,Integer>> getEntries() {
        List<Entry<K,V,Integer>> entries = new ArrayList<>();
        for (Map.Entry<K,V> entry : keyToValue.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            int frequency = keyToFrequency.get(key);
            entries.add(new Entry<>(key, value, frequency));
        }
        return entries;
    }



    public static class Entry<K, V, F> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private final F frequency;

        public Entry(K key, V value, F frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public F getFrequency() {
            return frequency;
        }
    }

}
