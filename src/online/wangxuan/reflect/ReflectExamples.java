package online.wangxuan.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangxuan
 * @date 2018/4/24 14:48
 */
public class ReflectExamples {

    static Cake appleCake = new Cake("cake", "smallAppleCake", "bigAppleCake");

    public static void accessPrivateFiled() {


        try {
            // 访问本类公有方法
            Cake.class.getMethod("h").invoke(appleCake);

            // 访问本类私有方法
            Method privateMethod = Cake.class.getDeclaredMethod("g");
            privateMethod.setAccessible(true);
            privateMethod.invoke(appleCake);

            // 访问本类的私有变量
            Field privateField = Cake.class.getDeclaredField("privateCakeName");
            privateField.setAccessible(true);
            System.out.println(privateField.get(appleCake));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void accessParentPrivate() {

        try {
            // 访问父类私有方法
            Method privateMethod = Cake.class.getSuperclass().getDeclaredMethod("f");
            privateMethod.setAccessible(true);
            privateMethod.invoke(appleCake);

            // 访问父类私有变量
            Field privateField = Cake.class.getSuperclass().getDeclaredField("foodName");
            privateField.setAccessible(true);
            System.out.println(privateField.get(appleCake));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Field> getInheritedPrivateFields(Class<?> type) {
        List<Field> result = new ArrayList<>();
        Class<?> i = type;
        while (i != null && i != Object.class) {
            Collections.addAll(result, i.getDeclaredFields());
            i = i.getSuperclass();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("===========private field=========");
        accessPrivateFiled();
        System.out.println("===========parent private field===========");
        accessParentPrivate();
        System.out.println("===========inherited fields===========");
        List<Field> fields = getInheritedPrivateFields(Cake.class);
        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }
}
