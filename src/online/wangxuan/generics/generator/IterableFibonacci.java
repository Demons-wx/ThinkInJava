package online.wangxuan.generics.generator;

import java.util.Iterator;

/**
 * 实现Iterable的Fibonacci生成器
 *
 * 通过继承来创建适配器类：
 * Created by wangxuan on 2017/8/11.
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {

    private int n;
    public IterableFibonacci(int count) {
        n = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return IterableFibonacci.this.next();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (int i : new IterableFibonacci(18))
            System.out.print(i + " ");
    }
}
