package com.mall.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * Param Util
 * </p>
 *
 * @author yanglin
 * @date 2020-06-22 18:56:03
 */
public class ParamUtil {

    /**
     * <p>
     * Put Field
     * </p>
     *
     * @param o          Object
     * @param fieldName  String
     * @param fieldValue Object
     * @author yanglin
     * @date 2020-06-22 18:56:18
     */
    public static void putField(Object o, String fieldName, Object fieldValue) {
        // 属性值为空，不设置值
        if (fieldValue == null) {
            return;
        }
        try {
            Field f = null;
            try {
                f = o.getClass().getDeclaredField(fieldName);
            } catch (Exception e) {
                f = o.getClass().getSuperclass().getDeclaredField(fieldName);
            }
            f.setAccessible(true);
            // 属性值为空字符串时候，将对应字段值为空，否则正常设置属性值
            if ("".equals(fieldValue.toString().trim())) {
                f.set(o, null);
            } else {
                Object fv = null;
                // 传过来的值是对应实例
                if (f.getType().isInstance(fieldValue)) {
                    fv = fieldValue;
                } else if (Date.class.getName().equals(f.getType().getName())) {
                    // 日期类型和其他常用类型的处理
                    fv = f.getType().getDeclaredConstructor(long.class)
                            .newInstance(Long.parseLong(fieldValue.toString().trim()));
                } else {
                    fv = f.getType().getDeclaredConstructor(String.class).newInstance(fieldValue.toString().trim());
                }
                if (ArrayList.class.equals(fieldValue.getClass())) {
                    o.getClass()
                            .getDeclaredMethod(
                                    "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1),
                                    f.getType())
                            .invoke(o, fv);
                } else {
                    f.set(o, fv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Generate Random Value
     * </p>
     *
     * @param o Object
     * @author yanglin
     * @date 2020-06-22 18:57:52
     */
    public static void randomObject(Object o) {
        // 值为空，不做任何操作
        if (o == null) {
            return;
        }
        String r = String.valueOf(System.currentTimeMillis());
        for (Field f : o.getClass().getDeclaredFields()) {
            if (!"serialVersionUID".equals(f.getName())) {
                for (int i = 0, len = r.length(); i < len - 1; i++) {
                    try {
                        f.setAccessible(true);
                        Object fv = null;
                        try {
                            fv = f.getType().getDeclaredConstructor(String.class).newInstance(r.substring(len - i - 1));
                        } catch (Exception e) {
                            fv = f.getType().getDeclaredConstructor(long.class)
                                    .newInstance(Long.parseLong(r.substring(len - i - 1)));
                        }
                        f.set(o, fv);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
