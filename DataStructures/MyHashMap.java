package DataStructures;

/**
 * Custom HashMap implementation with support for generic keys/value pairs,
 * and automatic resizing.
 * @param <K>   key
 * @param <V>   value
 */
class MyHashMap<K, V>{

    /** Constants & Instance Fields */
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] mBuckets;
    private int mSize;

    /** Overloaded Constructors */
    public MyHashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(final int tCapacity) {
        mBuckets = (Entry<K, V>[]) new Entry[tCapacity];
        mSize = 0;
    }

    /**
     * Inserts key/value pair into HashMap
     * @param tKey      key of new entry or entry to update
     * @param tValue    new value to associate with key
     */
    public void put(final K tKey, final V tValue) {
        if(tKey == null) { return; }

        int idx = (tKey.hashCode() & 0x7fffffff) % mBuckets.length;
        Entry<K, V> bucket = mBuckets[idx];
        Entry<K, V> newEntry = new Entry<>(tKey, tValue);

        if(bucket == null) {
            mBuckets[idx] = newEntry;
            mSize++;
        } else {
            //Checks all but last entry in bucket for a matching key
            while(bucket.getNext() != null) {
                if(tKey.equals(bucket.getKey())) {
                    bucket.setValue(tValue);
                    return;
                } else {
                    bucket = bucket.getNext();
                }
            }
            //Checks last entry in bucket for a matching key,
            //Appends entry to list in bucket if no match.
            if(tKey.equals(bucket.getKey())) {
                bucket.setValue(tValue);
            } else {
                bucket.setNext(newEntry);
                mSize++;
            }
        }
        resizeIfNeeded();
    }

    /**
     * Fetches value associated with a given key, if the entry exists.
     * @param tKey  key of the entry to fetch
     * @return      the value associated with the given key; null otherwise
     */
    public V get(final K tKey) {
        if(tKey == null) { return null;}
        //Get relavent bucket:
        int idx = (tKey.hashCode() & 0x7fffffff) % mBuckets.length;
        Entry<K, V> bucket = mBuckets[idx];
        //Search for matching key in bucket:
        while(bucket != null) {
            if(tKey.equals(bucket.getKey())) {
                return bucket.getValue();
            } else {
                bucket = bucket.getNext();
            }
        }
        //If no matching key, entry doesn't exist.
        return null;
    }

    /**
     * @return  the number of keys currently in the HashMap.
     */
    public int size() { return mSize; }

    /**
     * Doubles the size of the array holding the entry buckets when the number 
     * of entries exceeds 75% the number of indexes in the array.
     */
    @SuppressWarnings("unchecked")
    private void resizeIfNeeded() {
        if(mSize/mBuckets.length > LOAD_FACTOR) {
            int newCapacity = mBuckets.length * 2;
            Entry<K, V>[] newBuckets = (Entry<K, V>[]) new Entry[newCapacity];

            for(int i = 0; i < mBuckets.length; i++) {
                newBuckets[i] = mBuckets[i];
            }

            mBuckets = newBuckets;
        }
    }
}

/**
 * Entry objects contain information about a specific key/value pair in the HashMap
 * and function like a node in LinkedList, also holding information about the next
 * entry object in the "bucket".
 * @param <K>   key
 * @param <V>   value
 */
class Entry<K, V> {

    /** Instance Fields */
    private final K mKey;
    private V mValue;
    private Entry<K, V> mNext;

    /**
     * Constructor
     * @param tKey      key of new entry
     * @param tValue    value of new entry
     */
    public Entry(final K tKey, final V tValue) {
        this.mKey = tKey;
        this.mValue = tValue;
        this.mNext = null;
    }

    //Getters, Setters, hashCode, equals & toString
    public K getKey() { return mKey; }
    public V getValue() { return mValue; }
    public Entry<K, V> getNext() { return mNext; }

    public void setValue(final V tNewVal) { mValue = tNewVal; }
    public void setNext(final Entry<K, V> tNewNext) { mNext = tNewNext; }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (mKey != null ? mKey.hashCode() : 0);
        hash = hash * prime + (mValue != null ? mValue.hashCode() : 0);

        return hash;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object tObj) {
        if (this == tObj) { return true; }
        if (tObj == null) { return false; }
        if (getClass() != tObj.getClass()) { return false; }

        Entry<K, V> other = (Entry<K, V>) tObj;
        if(other.getKey() == null || !other.getKey().equals(mKey)) { return false; }
        if(other.getValue() == null || !other.getValue().equals(mValue)) { return false; }
        
        return true;
    }

    @Override
    public String toString() {
        return "[" + mKey.toString() + ", " + mValue.toString() + "]"; 
    }
}