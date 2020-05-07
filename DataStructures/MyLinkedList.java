class MyLinkedList<T> {
    private Node<T> mHead;
    private Node<T> mTail;
    private int mSize;

    public MyLinkedList() {
        mHead = null;
        mTail = null;
        mSize = 0;
    }

    public void insert(final T tData) {
        if(tData == null) { return; }

        Node<T> newNode = new Node<>(tData);
        if(mSize == 0) {
            mHead = mTail = newNode;
        } else {
            Node<T> oldTail = mTail;
            mTail = newNode;
            oldTail.setNext(newNode);
            newNode.setPrev(oldTail);
        }
        mSize++;
    }

    public void delete(final T tData) {
        if(mSize == 0 || tData == null) { return; }

        Node<T> currNode = mHead;
        while(currNode.getNext() != null) {
            if(tData.equals(currNode.getData())) {
                if(currNode == mHead) { //elem to delete is head
                    mHead = currNode.getNext();
                } else {// elem to delete is in middle
                    Node<T> left = currNode.getPrev();
                    Node<T> right = currNode.getNext();
                    left.setNext(right);
                    right.setPrev(left);
                }
                mSize--;
                return;
            }
            currNode = currNode.getNext();
        }

        if(tData.equals(currNode.getData())) {// elem to delete is tail
            mTail = currNode.getPrev();
            mSize--;
        }
    }

    public T getHead() { return mHead.getData(); }
    public T getTail() { return mTail.getData(); }
    public int size() { return mSize; }
}

class Node<T> {
    private T mData;
    private Node<T> mPrev;
    private Node<T> mNext;

    public Node(final T tData) {
        mData = tData;
        mPrev = null;
        mNext = null;
    }

    public T getData() { return mData; }
    public Node<T> getPrev() { return mPrev; }
    public Node<T> getNext() { return mNext; }

    public void setData(final T tData) { mData = tData; }
    public void setPrev(final Node<T> tPrev) { mPrev = tPrev; }
    public void setNext(final Node<T> tNext) { mNext = tNext; }
}