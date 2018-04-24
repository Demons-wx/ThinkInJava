package online.wangxuan.reflect;

/**
 * @author wangxuan
 * @date 2018/4/24 14:36
 */
public class Food {

    private String foodName;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    private void f() {
        System.out.println("private Food Method");
    }
}
