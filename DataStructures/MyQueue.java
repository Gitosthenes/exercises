package DataStructures;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private Node mHead;
    private Node mTail;
    private int mSize;

    public MyQueue() {
        mHead = null;
        mTail = null;
        mSize = 0;
    }

    public void queue(final T tData) {
        Node newNode = new Node(tData);
        if(mSize == 0) { mHead = mTail = newNode; }
        else {
            mTail.nNext = newNode;
            mTail = newNode;
        }
        mSize++;
    }

    public T dequeue() {
        if(mSize <= 0) { throw new NoSuchElementException(); }
        
        T result = mHead.nData;
        mHead = mHead.nNext;
        mSize--;
        return result;
    }

    public T peekFront() {
        if(mSize <= 0) { throw new NoSuchElementException(); }
        return mHead.nData;
    }

    public T peekEnd(){
        if(mSize <= 0) { throw new NoSuchElementException(); }
        return mTail.nData;
    }

    public int size() { return mSize; }

    private class Node {
        private T nData;
        private Node nNext;

        public Node(final T tData) {
            nData = tData;
            nNext = null;
        }
    }
}