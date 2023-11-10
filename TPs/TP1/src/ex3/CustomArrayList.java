package ex3;

public class CustomArrayList<T> {
	private static final int DEFAULT_INITIAL_SIZE = 10;
    private Object[] array;
    private int size=0;

    public CustomArrayList() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public CustomArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Initial size cannot be negative.");
        }
        this.array = new Object[initialSize];
    }

    public void add(T obj) {
    	checkCapacity(size + 1);
        array[size] = obj;
        size++;
    }

    public void add(int index, T obj) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        checkCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (T) array[index];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public int find(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(T x) {
        int index = find(x);
        if (index != -1) {
            removeAt(index);
        }
    }

    private void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        size--;
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        //array[size] = null;
    }

    private void checkCapacity(int newSize) {
        if (newSize > array.length) {
        	//array = Arrays.copyOf(array, 2*size);
        	//System.out.println("size doubled");

        	int newCapacity = 1;
            while (newCapacity < newSize) {
                newCapacity *= 2;
            }
            Object[] newArray = new Object[newCapacity];
            
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            result += array[i];
            if (i < size - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    
    
    // additional methods
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public void set(int index, T x) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        array[index] = x;
    }
    public void addAll(T[] elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public void removeAll(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                removeAt(i);
                i--;
            }
        }
    }
    public CustomArrayList<T> copy() {
        CustomArrayList<T> copyList = new CustomArrayList<>(size);
        for (int i = 0; i < size; i++) {
            copyList.add(get(i));
        }
        return copyList;
    }

    public void removeRedundancy() {
        CustomArrayList<T> uniqueList = new CustomArrayList<>(size);
        for (int i = 0; i < size; i++) {
            T element = get(i);
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }
        array = uniqueList.array;
        size = uniqueList.size;
    }

    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            T temp = (T) array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
    }

    public void resize(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException("New size cannot be negative.");
        }

        if (newSize < size) {
            for (int i = newSize; i < size; i++) {
                array[i] = null;
            }
        } else if (newSize > size) {
            checkCapacity(newSize);
        }
        size = newSize;
    }
    
    public void sort() {
        if (isComparable()) {
            quickSort(0, size - 1);
        } else {
            throw new UnsupportedOperationException("Elements of type T are not comparable.");
        }
    }

    private boolean isComparable() {
        return get(0) instanceof Comparable;
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        T pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (((Comparable) get(j)).compareTo(pivot) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }


    
    
}
