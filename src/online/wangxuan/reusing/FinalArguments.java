package online.wangxuan.reusing;

/**
 * Created by wangxuan on 2017/6/14.
 */
public class FinalArguments {

    void with(final Gizmo g) {
        //! g = new Gizmo(); // Illegal g is final
    }
    void without(Gizmo g) {
        g = new Gizmo(); // OK g not final
        g.spin();
    }
    // void f(final int i) { i++; } // can't change
    // you can only read from a final primitive
    int g(final int i) {
        return i + 1;
    }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
}

class Gizmo {
    public void spin() {}
}