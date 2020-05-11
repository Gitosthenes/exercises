package DataStructures;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] mArray;
    private int mSize;
    private int mEndIdx;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        mArray = (T[]) new Object[INITIAL_CAPACITY];
        mSize = 0;
        mEndIdx = 0;
    }

    public void add(final T tData) {
        if(mEndIdx > mArray.length - 1) { increaseSize(); }

        mArray[mEndIdx] = tData;
        mEndIdx++;
        mSize++;
    }

    public void add(int tIndex, final T tData) {
        mArray[tIndex] = tData;
    }

    public T get(int tIndex) {
        return mArray[tIndex];
    }

    public int size() {
        return mSize;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {
        T[] newArray = (T[]) new Object[mArray.length * 2];
        for(int i = 0; i < mArray.length; i++) {
            newArray[i] = mArray[i];
        }
        mArray = newArray;
    }
}