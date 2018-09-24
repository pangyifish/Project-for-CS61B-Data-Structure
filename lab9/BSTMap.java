package lab9;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
    @auther Yifan
**/
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root; /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree. */



    /* Create an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Remove all of the mappings from this this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) throw new IllegalArgumentException("key is null.");
        if (p == null) return null;
        if (key.compareTo(p.key) == 0)
            return p.value;
        else if (key.compareTo(p.key) < 0)
            return getHelper(key, p.left);
        else return getHelper(key, p.right);
    }

    /** Returns the value to which the specified key is mapped, or null if
     *  this map contains no mapping for the key.
     */

    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     *  Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) return new Node(key, value);
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        }
        else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        }
        else p.value = value;
        return p;

    }

    /** Insert the key KEY
     *  if it is already present, updates value to be VALUE.
     */
    @Override
    public void put (K key, V value) {
        if (key == null) throw new IllegalArgumentException(" null key. ");
        if (value == null) return;
        root = putHelper(key, value, root);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


}
