package DataStructures;

class MyLinkedList<T> {
    private Node mHead;
    private Node mTail;
    private int mSize;

    public MyLinkedList() {
        mHead = mTail = new Node(null);
        mSize = 0;
    }

    public void insertHead(final T tData) {
        Node newNode = new Node(tData);
        if(mSize == 0) {
            mHead = mTail = newNode;
        } else {
            newNode.nNext = mHead;
            mHead.nPrev = newNode;
            mHead = newNode;
        }
        mSize++;
    }

    public void insertTail(final T tData) {
        Node newNode = new Node(tData);
        if(mSize == 0) {
            mHead = mTail = newNode;
        } else {
            newNode.nPrev = mTail;
            mTail.nNext = newNode;
            mTail = newNode;
        }
        mSize++;
    }

    public void removeHead() {
        if(mHead != null) {//If list is empty, don'd do anything
            mHead = mHead.nNext;
            if(mHead != null) {//If list had only 1 element, no need to update pointers
                mHead.nPrev = null;
            }
            mSize--;
        }
    }

    public void removeTail() {
        if(mTail != null) {//If list is empty, don'd do anything
            mTail = mTail.nPrev;
            if(mTail != null) {//If list had only 1 element, no need to update pointers
                mTail.nNext = null;
            }
            mSize--;
        }
    }

    public T get(int tIndex) {
        if(tIndex < 0 || tIndex >= mSize) {throw new IndexOutOfBoundsException(); }
        
        Node currNode = mHead;
        for(int i = 0; i < tIndex; i++) {
            currNode = currNode.nNext;
        }

        return currNode.nData;
    }

    public T getHead() { return mHead.nData; }
    public T getTail() { return mTail.nData; }
    public int size() { return mSize; }

    private class Node {
        private T nData;
        private Node nPrev;
        private Node nNext;

        private Node(final T tData) {
            nData = tData;
            nPrev = null;
            nNext = null;
        }
    }
}