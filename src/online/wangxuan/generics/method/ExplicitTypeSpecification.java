package online.wangxuan.generics.method;

import online.wangxuan.typeinfo.pets.Person;
import online.wangxuan.typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

/**
 * 在泛型方法中，可以显式的指明类型，不过这种语法很少使用。
 *
 * 要显式的指明类型，必须在点操作符与方法名之间插入尖括号，然后把类型置于尖括号内。
 * 如果是在定义该方法的类的内部，必须在点操作符之前使用this关键字，如果是static
 * 方法，必须在点操作符之前加上类名。
 * Created by wangxuan on 2017/8/11.
 */
public class ExplicitTypeSpecification {

    static void f(Map<Person, List<Pet>> petPeple) {}

    public static void main(String[] args) {
        f(New.<Person, List<Pet>>map());
    }
}
