package online.wangxuan.containers.ref;


import java.util.WeakHashMap;

/**
 * Created by wangxuan on 2017/6/19.
 */
public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();
        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0)
                keys[i] = k; // save as real reference
            map.put(k, v);
        }
        System.gc();
    }
}

class Element {
    private String ident;
    public Element(String ident) {
        this.ident = ident;
    }
    public String toString() {
        return ident;
    }
    public int hashCode() {
        return ident.hashCode();
    }
    public boolean equals(Object r) {
        return r instanceof Element && ident.equals(((Element) r).ident);
    }
    protected void finalize() {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}
