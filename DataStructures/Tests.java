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
    }

    @Test
    void loadFactorTest() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for(int i = 0; i < 10000; i++){
            map.put(i, i);
        }
        for(int i = 0; i < 10000; i++){
            assertEquals(i, map.get(i));
        }
    }
}