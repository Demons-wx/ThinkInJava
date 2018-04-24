package online.wangxuan.reflect;

/**
 * @author wangxuan
 * @date 2018/4/24 14:38
 */
public class Cake extends Food {

    private String privateCakeName;
    private String publicCakeName;

    public Cake(String foodName, String privateCakeName, String publicCakeName) {
        super(foodName);
        this.privateCakeName = privateCakeName;
        this.publicCakeName = publicCakeName;
    }

    private void g() {
        System.out.println("private Cake Method");
    }

    public void h() {
        System.out.println("public Cake Method");
    }
}
