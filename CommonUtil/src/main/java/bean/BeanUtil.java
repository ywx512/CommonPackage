package bean;

import java.lang.reflect.Field;

/**
 * @author yuweixiong
 * @date 2021/01/11 18:00
 * @description
 */
public class BeanUtil {

    /**
     * 复制对象，将源对象的同名字段复制到目标对象
     *
     * @param source
     * @param target
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void copyProperties(Object source, Object target) throws NoSuchFieldException, IllegalAccessException {
        Class sourceClass = source.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();

        Class targetClass = target.getClass();

        for (Field field : sourceFields) {
            field.setAccessible(true);
            Object value = field.get(source);
            if (value != null) {
                String fieldName = field.getName();
                Field targetField = targetClass.getDeclaredField(fieldName);
                targetField.setAccessible(true);
                targetField.set(target, value);
            }
        }
    }
}
