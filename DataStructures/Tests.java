package DataStructures;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import org.junit.jupiter.api.Test;

class Tests {

    /*******************************************************
     *                  ARRAY LIST TESTS
     * *****************************************************/

     @Test
    void alTestCreation() {
        MyArrayList<Integer> al = new MyArrayList<>();
        assertEquals(0, al.size());
    }

    @Test
    void alTestAddAndGet() {
        MyArrayList<Integer> al = new MyArrayList<>();
        int numElements = 256;

        for(int i = 0; i < numElements; i++) {
            al.add(i);
        }
        assertEquals(numElements, al.size());

        for(int i = 0; i < numElements; i++) {
            assertEquals(i, al.get(i));
        }
        al.add(128, 0);
        assertEquals(0, al.get(128));

        for(int i = 129; i < al.size(); i++) {
            assertEquals(i - 1, al.get(i));
        }

        al.remove(128);
        al.add(256);
        for(int i = 0; i < al.size(); i++) {
            assertEquals(i, al.get(i));
        }

        assertThrows(IndexOutOfBoundsException.class, () -> {
            al.add(al.size()+1, 0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            al.get(al.size()+1);
        });
    }


    /*******************************************************
     *                  LINKED LIST TESTS
     * *****************************************************/

    @Test
    void llTestCreation() {
        MyLinkedList<String> ll = new MyLinkedList<>();
        assertEquals(0, ll.size());
        assertEquals(null, ll.getHead());
        assertEquals(null, ll.getTail());
    }

    @Test
    void llTestInsertAndRemove() {
       MyLinkedList<String> ll = new MyLinkedList<>();
       int numEntries = 500;
       int strLen = 20;
       String[] heads = new String[numEntries];
       String[] tails = new String[numEntries];

       for(int i = 0; i < numEntries; i++) {
           String rand = getRandString(strLen);
           ll.insertHead(rand);
           heads[i] = rand;
           assertEquals(rand, ll.getHead());

           rand = getRandString(strLen);
           ll.insertTail(rand);
           tails[i] = rand;
           assertEquals(rand, ll.getTail());

           assertEquals((i+1)*2, ll.size());
       }

       for(int i = numEntries-1; i >= 0; i--) {
           assertEquals(heads[i], ll.getHead());
           assertEquals(tails[i], ll.getTail());

           ll.removeHead();
           ll.removeTail();
       }

       assertEquals(0, ll.size());
    }

    @Test
    void llTestGet() {
        MyLinkedList<Integer> ll = new MyLinkedList<>();
        for(int i = 0; i < 500; i++) {
            ll.insertTail(i);
            assertEquals(i, ll.get(i));
        }
    }

    /*******************************************************
     *                   STACK TESTS                       
     * *****************************************************/

    @Test
    void stTestCreation() {
        MyStack<String> s = new MyStack<>();
        assertNotEquals(null, s);
        assertEquals(0, s.size());
    }

    @Test
    void stTestMethods() {
        MyStack<String> myS = new MyStack<>();
        Stack<String> otherS = new Stack<>();
        int numEntries = 500;
        int strLen = 20;
        
        for(int i = 0; i < numEntries; i++) {
            String rand = getRandString(strLen);
            myS.push(rand);
            otherS.push(rand);

            assertEquals(otherS.size(), myS.size());
            assertEquals(rand, myS.peek());
        }

        for(int i = 0; i < numEntries; i++) {
            assertEquals(otherS.pop(), myS.pop());
        }

        assertThrows(EmptyStackException.class, () -> {
            myS.peek();
        });

        assertThrows(EmptyStackException.class, () -> {
            myS.pop();
        });
    }

    /*******************************************************
     *                   QUEUE TESTS                       
     * *****************************************************/

    @Test
    void qTestCreation() {
        MyQueue<String> q = new MyQueue<>();
        assertNotEquals(null, q);
        assertEquals(0, q.size()); 
    }

    @Test
    void qTestMethods() {
        MyQueue<String> myQ = new MyQueue<>();
        Queue<String> otherQ = new LinkedList<>();
        int numEntries = 500;
        int strLen = 20;

        for(int i = 0; i < numEntries; i++) {
            String rand = getRandString(strLen);
            myQ.queue(rand);
            otherQ.add(rand);
            assertEquals(otherQ.size(), myQ.size());
            assertEquals(otherQ.peek(), myQ.peekFront());
        }

        for(int i = 0; i < numEntries; i++) {
            assertEquals(otherQ.poll(), myQ.dequeue());
        }

        assertEquals(0, myQ.size());

        assertThrows(NoSuchElementException.class, () -> {
            myQ.peekFront();
        });

        assertThrows(NoSuchElementException.class, () -> {
            myQ.peekEnd();
        });

        assertThrows(NoSuchElementException.class, () -> {
            myQ.dequeue();
        });
    }

    /*******************************************************
     *                  HASHMAP TESTS                       
     * *****************************************************/
    
    @Test
    void hmTestCreation() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertNotEquals(null, map);
        assertEquals(0, map.size());
    }

    @Test
    void hmTestPutAndGet() {
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
    void hmTestDelete() {
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
    void hmTestLoadFactor() {
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

    private String getRandString(int length) {
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