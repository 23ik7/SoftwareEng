package reflection;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class ArrayListDummy extends AbstractList implements List, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = 0;
    private transient Object[] elementData = null;
    private int size = 0;
    private static final int MAX_ARRAY_SIZE = 0;

    public ArrayListDummy(Collection param0) {
        System.out.println("constructor");
    }

    public ArrayListDummy() {
        System.out.println("constructor");
    }

    public ArrayListDummy(int param0) {
        System.out.println("constructor");
    }


    public void add(int param0, Object param1) {
        System.out.println("add");
    }

    public boolean add(Object param0) {
        System.out.println("add");
        return false;
    }

    public Object get(int param0) {
        System.out.println("get");
        return null;
    }

    public Object clone() {
        System.out.println("clone");
        return null;
    }

    public int indexOf(Object param0) {
        System.out.println("indexOf");
        return 0;
    }

    public void clear() {
        System.out.println("clear");
    }

    static int access$100(ArrayList param0) {
        System.out.println("access$100");
        return 0;
    }

    public boolean addAll(Collection param0) {
        System.out.println("addAll");
        return false;
    }

    public boolean addAll(int param0, Collection param1) {
        System.out.println("addAll");
        return false;
    }

    public Iterator iterator() {
        System.out.println("iterator");
        return null;
    }

    static Object[] access$200(ArrayList param0) {
        System.out.println("access$200");
        return null;
    }

    public boolean remove(Object param0) {
        System.out.println("remove");
        return false;
    }

    public Object remove(int param0) {
        System.out.println("remove");
        return null;
    }


    private void readObject(ObjectInputStream param0) throws IOException, ClassNotFoundException{
        System.out.println("readObject");
    }

    private void writeObject(ObjectOutputStream param0) throws IOException{
        System.out.println("writeObject");
    }

    public Object set(int param0, Object param1) {
        System.out.println("set");
        return null;
    }

    public void ensureCapacity(int param0) {
        System.out.println("ensureCapacity");
    }

    private void ensureCapacityInternal(int param0) {
        System.out.println("ensureCapacityInternal");
    }

    public void trimToSize() {
        System.out.println("trimToSize");
    }

    private String outOfBoundsMsg(int param0) {
        System.out.println("outOfBoundsMsg");
        return null;
    }

    private void rangeCheckForAdd(int param0) {
        System.out.println("rangeCheckForAdd");
    }

    Object elementData(int param0) {
        System.out.println("elementData");
        return null;
    }

    private void grow(int param0) {
        System.out.println("grow");
    }

    private static int hugeCapacity(int param0) {
        System.out.println("hugeCapacity");
        return 0;
    }

    public ListIterator listIterator() {
        System.out.println("listIterator");
        return null;
    }

    public ListIterator listIterator(int param0) {
        System.out.println("listIterator");
        return null;
    }

    public boolean removeAll(Collection param0) {
        System.out.println("removeAll");
        return false;
    }

    protected void removeRange(int param0, int param1) {
        System.out.println("removeRange");
    }

    public boolean retainAll(Collection param0) {
        System.out.println("retainAll");
        return false;
    }

    private boolean batchRemove(Collection param0, boolean param1) {
        System.out.println("batchRemove");
        return false;
    }

    private void fastRemove(int param0) {
        System.out.println("fastRemove");
    }

    private void rangeCheck(int param0) {
        System.out.println("rangeCheck");
    }

    public boolean contains(Object param0) {
        System.out.println("contains");
        return false;
    }

    public boolean isEmpty() {
        System.out.println("isEmpty");
        return false;
    }

    public int lastIndexOf(Object param0) {
        System.out.println("lastIndexOf");
        return 0;
    }

    public int size() {
        System.out.println("size");
        return 0;
    }

    public List subList(int param0, int param1) {
        System.out.println("subList");
        return null;
    }

    public Object[] toArray(Object[] param0) {
        System.out.println("toArray");
        return null;
    }

    public Object[] toArray() {
        System.out.println("toArray");
        return null;
    }

    static void subListRangeCheck(int param0, int param1, int param2) {
        System.out.println("subListRangeCheck");
    }

}