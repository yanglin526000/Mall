package com.mall.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Param Util
 * </p>
 *
 * @author yanglin
 * @date 2020-06-22 18:56:03
 */
public final class ParamUtil {

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
    public static Object putField(Object o, String fieldName, Object fieldValue) {
        // Null value handle
        if (o == null || fieldValue == null) {
            return null;
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
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * <p>
     * Get Field Value From Object
     * </p>
     *
     * @param o         Object
     * @param fieldName fieldName
     * @return java.lang.Object
     * @author yanglin
     * @date 2020-06-24 23:05:30
     */
    public static Object getField(Object o, String fieldName) {
        // Null value handle
        if (o == null || fieldName == null) {
            return null;
        }
        for (Field f : getSelfAndSuperClassFields(o)) {
            if (fieldName.equals(f.getName())) {
                f.setAccessible(true);
                try {
                    return f.get(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * <p>
     * Get Field Value From Object
     * </p>
     *
     * @param o
     * @param field
     * @return java.lang.Object
     * @author yanglin
     * @date 2020-07-05 22:49:49
     */
    public static Object getField(Object o, Field field) {
        // Null value handle
        if (o == null || field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * <p>
     * Get Self And Super Class Fields
     * </p>
     *
     * @param o Object
     * @return java.util.List<java.lang.reflect.Field>
     * @author yanglin
     * @date 2020-06-24 23:19:18
     */
    public static List<Field> getSelfAndSuperClassFields(Object o) {
        // Null value handle
        if (o == null) {
            return null;
        }
        List<Field> fieldList = new ArrayList<>();
        Class<?> suCl = o.getClass();
        do {
            for (Field f : suCl.getDeclaredFields()) {
                fieldList.add(f);
            }
        } while ((suCl = suCl.getSuperclass()) != null);
        return fieldList;
    }

    /**
     * <p>
     * Put Values To Object
     * </p>
     *
     * @param from Object
     * @param to   Object
     * @author yanglin
     * @date 2020-06-24 23:45:06
     */
    public static Object putValuesToObject(Object from, Object to) {
        for (Field f : getSelfAndSuperClassFields(from)) {
            f.setAccessible(true);
            try {
                ParamUtil.putField(to, f.getName(), f.get(from));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return to;
    }

    /**
     * <p>
     * Get Custom Constructor
     * </p>
     *
     * @param c Class
     * @return java.lang.Object
     * @author yanglin
     * @date 2020-06-24 23:51:15
     */
    public static Object getCustomConstructor(Class c) {
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
