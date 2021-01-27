package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class TaskList implements List<Task> {

    private final List<Task> internalTasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(Collection<Task> tasks) {
        internalTasks.addAll(tasks);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return internalTasks.containsAll(c);
    }

    @Override
    public boolean add(Task task) {
        return internalTasks.add(task);
    }

    @Override
    public void add(int index, Task element) {
        internalTasks.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        return internalTasks.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Task> c) {
        return internalTasks.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return internalTasks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return internalTasks.retainAll(c);
    }

    @Override
    public void clear() {
        internalTasks.clear();
    }

    @Override
    public Task get(int index) {
        return internalTasks.get(index);
    }

    @Override
    public Task set(int index, Task element) {
        return internalTasks.set(index, element);
    }

    @Override
    public boolean remove(Object o) {
        return internalTasks.remove(o);
    }

    public Task remove(int index) {
        return internalTasks.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return internalTasks.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return internalTasks.lastIndexOf(o);
    }

    @Override
    public ListIterator<Task> listIterator() {
        return internalTasks.listIterator();
    }

    @Override
    public ListIterator<Task> listIterator(int index) {
        return internalTasks.listIterator(index);
    }

    @Override
    public List<Task> subList(int fromIndex, int toIndex) {
        return internalTasks.subList(fromIndex, toIndex);
    }

    @Override
    public int size() {
        return internalTasks.size();
    }

    @Override
    public boolean isEmpty() {
        return internalTasks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalTasks.contains(o);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalTasks.iterator();
    }

    @Override
    public Object[] toArray() {
        return internalTasks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return internalTasks.toArray(a);
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        internalTasks.forEach(action);
    }
}
