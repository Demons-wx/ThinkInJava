package online.wangxuan.generics.simple;

/**
 * 不使用LinkedList，来实现自己的内部链式存储机制
 * Created by wangxuan on 2017/8/10.
 */
public class LinkedStack<T> {

    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }
        // 末端哨兵
        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    // 每调用一次push()方法，就会创建一个Node<T>对象，并将其链接到前一个Node<T>对象。
    public void push(T item) {
        top = new Node<T>(item, top);
    }

    // 当调用pop()方法时，总是返回top.item，然后丢弃当前top所指的Node<T>
    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next; // 将top转移到下一个Node<T>，除非碰到了末端哨兵
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<>();
        for (String s : "Phasers or stun!".split(" "))
            lss.push(s);

        String s;
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}


