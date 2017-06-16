package online.wangxuan.reusing;

import static net.mindview.util.Print.*;

/**
 * Created by wangxuan on 2017/6/16.
 */
public class FinalOverridingIllusion {

    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
        // you can upcast:
        OverridingPrivate op = op2;
        // But you can't call the methods:
        // op.f();
        // op.g();
        // Same here:
        WithFinals wf = op2;
        // wf.f();
        // wf.g();
    }
}

class WithFinals {

    private final void f() {
        print("WithFinals.f()");
    }

    private void g() {
        print("WithFinals.g()");
    }
}

class OverridingPrivate extends WithFinals {

    private final void f() {
        print("OverridingPrivate.f()");
    }

    private void g() {
        print("OverridingPrivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {

    public final void f() {
        print("OverridingPrivate2.f()");
    }

    public void g() {
        print("OverridingPrivate2.g()");
    }
}

