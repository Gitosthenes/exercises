package DataStructures;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] mArray;
    private int mSize;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        mArray = (T[]) new Object[INITIAL_CAPACITY];
        mSize = 0;
    }

    public void add(final T tData) {
        if(mSize == mArray.length) { increaseSize(); }
        mArray[mSize] = tData;
        mSize++;
    }

    public void add(int tIndex, final T tData) {
        if(tIndex < 0 || tIndex > mSize) { throw new IndexOutOfBoundsException(); }
        if(mSize >= mArray.length) { increaseSize(); }
        
        for(int i = mSize; tIndex < i; i--) {
            mArray[i] = mArray[i-1];
        }
        mArray[tIndex] = tData;
        mSize++;
    }

    public T get(int tIndex) {
        if(tIndex < 0 || tIndex > mSize) { throw new IndexOutOfBoundsException(); }
        return mArray[tIndex];
    }

    public void remove(int tIndex) {
        if(tIndex < 0 || tIndex > mSize) { throw new IndexOutOfBoundsException(); }

        for(int i = tIndex; i < mSize - 1; i++) {
            mArray[i] = mArray[i + 1];
        }
        mSize--;
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