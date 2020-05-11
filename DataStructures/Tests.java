package DataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class Tests {
    @Test
    void creationTest() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertNotEquals(null, map);
        assertEquals(0, map.size());
    }

    @Test
    void putAndGetTest() {
        MyHashMap<Double, Float> map = new MyHashMap<>();
        double[] keys = new double[]{2.2987654d, Double.MIN_VALUE, 56.43243d, -1000.44d, 321321d};
        float[] vals = new float[]{32.23f, -5666.1111f, 6f, 0.000000001f, Float.MAX_VALUE};

        for(int i = 0; i < keys.length; i++) {
            map.put(keys[i], vals[i]);
            assertEquals(vals[i], map.get(keys[i]));
        }
        assertEquals(keys.length, map.size());
    }

    @Test
    void deleteTest() {
        //Initialize variables
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        int numEntries = 500;
        boolean[] deleted = new boolean[numEntries];
        
        //Insert entries into map
        for(int i = 0; i < numEntries; i++) {
            map.put(i, i);
        }

        //Remove random subset of entries, keeping track of deleted keys
        for(int i = 0; i < numEntries; i += (int)(10 * Math.random()) + 1) {
            map.delete(i);
            deleted[i] = true;
        }

        //Verify that only random subset was removed and other entries are in tact
        for(int i = 0; i < numEntries; i++) {
            if(deleted[i]) {
                assertEquals(null, map.get(i));
            } else {
                assertEquals(i, map.get(i));
            }
        }
    }

    @Test
    void loadFactorTest() {
        //Initialize variables
        MyHashMap<String, Integer> map = new MyHashMap<>();
        int strLen = 20;
        int numEntries = 10000;
        String[] keys = new String[numEntries];

        //Insert entries and test that get works after
        //increasing the size of the table 10 times.
        //2^4 -> 2^14
        for(int i = 0; i < numEntries; i++){
            String randKey = getRandString(strLen);
            map.put(randKey, i);
            keys[i] = randKey;
        }
        for(int i = 0; i < numEntries; i++){
            assertEquals(i, map.get(keys[i]));
        }
        //Test that size is accurate
        assertEquals(numEntries, map.size());

        //Delete half of the entries from the map
        for(int i = 0; i < numEntries; i += 2){
            map.delete(keys[i]);
        }

        //Test for all keys, which should either have a value (i) or be null (deleted earlier)
        for(int i = 0; i < numEntries; i++) {
            if(i % 2 == 0) {
                assertEquals(null, map.get(keys[i]));
            } else {
                assertEquals(i, map.get(keys[i]));
            }
        }

        //Test for correct size again
        assertEquals(numEntries/2, map.size());
    }

    String getRandString(int length) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        + "0123456789"
                        + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            int idx = (int) (alpha.length() * Math.random());
            sb.append(alpha.charAt(idx));
        }

        return sb.toString();
    }
}