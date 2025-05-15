import java.util.AbstractList;
import java.util.Iterator;

public class CustomList <T> extends AbstractList<T> {
    private static class Element<T> {
        T value;
        Element<T> next;

        Element(T value) {
            this.value = value;
        }
    }
    private Element<T> start;
    private Element<T> end;

    @Override
    public T get(int index) {
        if (this.start == null) { return null; }
        if (index > this.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Element<T> current = this.start;
        while (index > 0) {
            index--;
            current = current.next;
        }
        return current.value;
    }
    @Override
    public int size() {
        if (this.start == null) { return 0; }
        int count = 1;
        Element<T> current = this.start;
        while (current.next != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean add(T t) {
        Element<T> newEnd = new Element<>(t);
        if (this.start == null) { this.start = newEnd; }
        else { this.end.next = newEnd; }
        this.end = newEnd;
        return true;
    }

    public void addLast(T value) {
        Element<T> newEnd = new Element<>(value);
        if (this.start == null) { this.start = newEnd; }
        else { this.end.next = newEnd; }
        this.end = newEnd;
    }

    public void addFirst(T value) {
        Element<T> newStart = new Element<>(value);
        newStart.next = this.start;
        this.start = newStart;
    }

    public T removeFirst() {
        if (this.start == null) {
            throw new IllegalStateException("List is empty");
        }
        Element<T> toReturn = this.start;
        this.start = this.start.next;
        return toReturn.value;
    }

    public T removeLast() {
        if (this.start == null) {
            throw new IllegalStateException("List is empty");
        }
        Element<T> currentElement = this.start;
        Element<T> target = this.end;
        if (currentElement == target) {
            this.end = null;
            this.start = null;
            return currentElement.value;
        } else {
            while (currentElement.next != this.end) {
                currentElement = currentElement.next;
            }
            this.end = currentElement;
            return target.value;
        }
    }

    public T getLast() {
        if (this.start == null) {
            throw new IllegalStateException("List is empty");
        }
        return this.end.value;
    }
    public T getFirst() {
        if (this.start == null) {
            throw new IllegalStateException("List is empty");
        }
        return this.start.value;
    }


    @Override
    public String toString() {
        String result = "Custom LinkedList\n[ ";
        Element<T> current = this.start;
        while (current != null) {
            result += current.value;
            if (current.next != null) result += ", ";
            current = current.next;
        }
        return result;
    }
}
