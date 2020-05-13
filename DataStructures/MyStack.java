package DataStructures;

import java.util.EmptyStackException;

public class MyStack<T> {
    private Node mHead;
    private int mSize;

    public MyStack() {
        mHead = new Node(null);
        mSize = 0;
    }

    public void push(final T tData) {
        Node newHead = new Node(tData);
        newHead.nNext = mHead;
        mHead = newHead;
        mSize++;
    }

    public T pop() {
        if(mSize <= 0) {throw new EmptyStackException();}

        T result = mHead.nData;
        mHead = mHead.nNext;
        mSize--;
        return result;
    }

    public T peek() { 
        if(mSize <= 0) {throw new EmptyStackException();}

        return mHead.nData;
    }

    public int size() { return mSize; }

    private class Node {
        private T nData;
        private Node nNext;

        private Node(final T tData) {
            nData = tData;
            nNext = null;
        }
    }
}