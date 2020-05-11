package DataStructures;

class MyLinkedList<T> {
    private Node mHead;
    private Node mTail;
    private int mSize;

    public MyLinkedList() {
        mHead = null;
        mTail = null;
        mSize = 0;
    }

    public void insertHead(final T tData) {
        Node newNode = new Node(tData);
        if(mSize == 0) {
            mHead = mTail = newNode;
        } else {
            newNode.setNext(mHead);
            mHead = newNode;
        }
        mSize++;
    }

    public void insertTail(final T tData) {
        if(mSize == 0) {
            mHead = mTail = new Node(tData);
        } else {
            mTail.setNext(new Node(tData));
        }
        mSize++;
    }

    public void removeHead() { 
        mHead = mHead.getNext();
        mHead.setPrev(null);
        mSize--;
    }

    public void removeTail() {
        mTail = mTail.getPrev();
        mTail.setNext(null);
        mSize--;
    }

    public T getHead() { return mHead.getData(); }
    public T getTail() { return mTail.getData(); }
    public int size() { return mSize; }

    private class Node {
        private T mData;
        private Node mPrev;
        private Node mNext;

        public Node(final T tData) {
            mData = tData;
            mPrev = null;
            mNext = null;
        }

        public T getData() { return mData; }
        public Node getPrev() { return mPrev; }
        public Node getNext() { return mNext; }

        public void setPrev(final Node tPrev) { mPrev = tPrev; }
        public void setNext(final Node tNext) { mNext = tNext; }
    }
}