package online.wangxuan.generics.method;

/**
 * 泛型方法使得该方法能够独立于类而产生变化，以下是一个基本的指导原则：
 * 无论何时，只要你能做到，就应该尽量使用泛型方法。也就是说，如果使用
 * 泛型方法可以取代将整个类泛型化，那就应该只使用泛型方法，因为它可以使
 * 事情更清楚明白。另外，对于一个static方法而言，无法访问泛型类的类型
 * 参数，所以如果static方法需要使用泛型能力，就必须使其成为泛型方法。
 * Created by wangxuan on 2017/8/11.
 */
public class GenericMethods {

     // 要定义泛型方法，只需将泛型参数列表置于返回值之前：
     public <T> void f(T x) {
         System.out.println(x.getClass().getName());
     }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
