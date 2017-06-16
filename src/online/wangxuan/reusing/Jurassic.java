package online.wangxuan.reusing;

/**
 * Created by wangxuan on 2017/6/16.
 */
public class Jurassic {

    public static void main(String[] args) {
        Dinosaur n = new Dinosaur();
        n.f();
        n.i = 40;
        n.j++;
    }
}

class SmallBrain {}

final class Dinosaur {
    int i = 7;
    int j = 1;
    SmallBrain x = new SmallBrain();
    void f() {}
}

//! class Futher extends Dinosaur {}
// Cannot extend final class Dinosaur


