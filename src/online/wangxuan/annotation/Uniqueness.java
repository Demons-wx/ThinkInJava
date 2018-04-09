package online.wangxuan.annotation;

/**
 * @author wangxuan
 * @date 2018/4/9 下午11:21
 */

public @interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
}
