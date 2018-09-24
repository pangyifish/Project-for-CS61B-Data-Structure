package lab9;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
     *  A hash table-backed Map implementation. Provides amortized constant time
     *  access to elements via get(), remove(), and put() in the best case.
     *
     *  @author Your name here
     */

    public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }


    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int n){
        buckets = new ArrayMap[n];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }



    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key is null. ");
        int i = hash(key);
        return buckets[i].get(key);
    }



    /* Associates the specified value with the specified key in this map. */

    private void resize(int num) {
        MyHashMap<K, V> temp = new MyHashMap<>(num);
        for (int i = 0; i < buckets.length; i++) {
                for (K key : buckets[i].keySet()) temp.put(key, buckets[i].get(key));
        }
        this.size = temp.size;
        this.buckets = temp.buckets;
    }


    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key is null.");
        if (value == null) return;
        if (loadFactor() > MAX_LF) resize(2 * buckets.length);
        int i = hash(key);
        if (buckets[i].containsKey(key) == false) size++;
        buckets[i].put(key, value);
    }



    /* Returns the number of key-value mappings in this map. */

    @Override

    public int size() {
        return size;
    }


    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////



    /* Returns a Set view of the keys contained in this map. */

    @Override

    public Set<K> keySet() {

        throw new UnsupportedOperationException();

    }



    /* Removes the mapping for the specified key from this map if exists.

     * Not required for this lab. If you don't implement this, throw an

     * UnsupportedOperationException. */

    @Override

    public V remove(K key) {

        throw new UnsupportedOperationException();

    }



    /* Removes the entry for the specified key only if it is currently mapped to

     * the specified value. Not required for this lab. If you don't implement this,

     * throw an UnsupportedOperationException.*/

    @Override

    public V remove(K key, V value) {

        throw new UnsupportedOperationException();

    }


    @Override

    public Iterator<K> iterator() {

        throw new UnsupportedOperationException();

    }

    public static void main(String args[]) {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        assertEquals(0, dictionary.size());

        // can put objects in dictionary and get them
        dictionary.put("hello", "world");
        assertTrue(dictionary.containsKey("hello"));
        assertEquals("world", dictionary.get("hello"));
        assertEquals(1, dictionary.size());


        // putting with existing key updates the value

        dictionary.put("hello", "kevin");
        assertEquals(1, dictionary.size());
        assertEquals("kevin", dictionary.get("hello"));

        // putting key in multiple times does not affect behavior

        MyHashMap<String, Integer> studentIDs = new MyHashMap<>();
        studentIDs.put("sarah", 12345);
        assertEquals(1, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        assertTrue(studentIDs.containsKey("sarah"));
        assertTrue(studentIDs.containsKey("alan"));

        // handle values being the same
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("evil alan", 345);
        assertEquals(345, studentIDs.get("evil alan").intValue());
        assertEquals(studentIDs.get("evil alan"), studentIDs.get("alan"));
    }
}