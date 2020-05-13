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
        newHead.setNext(mHead);
        mHead = newHead;
        mSize++;
    }

    public T pop() {
        if(mSize <= 0) {throw new EmptyStackException();}

        T result = mHead.getData();
        mHead = mHead.getNext();
        mSize--;
        return result;
    }

    public T peek() { 
        if(mSize <= 0) {throw new EmptyStackException();}

        return mHead.getData();
    }

    public int size() { return mSize; }

    private class Node {
        private T mData;
        private Node mNext;

        public Node(final T tData) {
            mData = tData;
            mNext = null;
        }

        public T getData() { return mData; }
        public Node getNext() { return mNext; }

        public void setNext(final Node tNext) { mNext = tNext; }
    }
}