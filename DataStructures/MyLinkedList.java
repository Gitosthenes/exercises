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
            newNode.setNext(mHead);
            mHead = newNode;
        }
        mSize++;
    }

    public void insertTail(final T tData) {
        Node newNode = new Node(tData);
        if(mSize == 0) {
            mHead = mTail = newNode;
        } else {
            newNode.setPrev(mTail);
            mTail = newNode;
        }
        mSize++;
    }

    public void removeHead() {
        if(mHead != null) {//If list is empty, don'd do anything
            mHead = mHead.getNext();
            if(mHead != null) {//If list had only 1 element, no need to update pointers
                mHead.setPrev(null);
            }
            mSize--;
        }
    }

    public void removeTail() {
        if(mTail != null) {//If list is empty, don'd do anything
            mTail = mTail.getPrev();
            if(mTail != null) {//If list had only 1 element, no need to update pointers
                mTail.setNext(null);
            }
            mSize--;
        }
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