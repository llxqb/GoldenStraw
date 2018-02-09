package com.bhxx.goldenstraw.utils;
/**
 * 解析json数据
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtils {
    /**
     * @param url
     */
    public static String getJsonContent(InputStream is) {
        return ConvertStream2Json(is);
    }

    public static String Object2Json(Object obj) {//map 可以转换成字符串
        final Gson gsonBuilder = new GsonBuilder().setDateFormat(
                "yyyy-MM-dd HH:mm:ss").create();
        return gsonBuilder.toJson(obj);
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    public static <T, V> T getBean(String jsonStr, Class<T> cls, Class<V> rows) {
        try {
            Gson gson = new Gson();
            if (rows == null) {
                return gson.fromJson(jsonStr, cls);
            }


            Type objectType = type(cls, rows);
            return gson.fromJson(jsonStr, objectType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 将Map转换成Javabean
     *
     * @param javabean javaBean
     * @param data     Map数据
     */
    public static Object toJavaBean(Class cla, Map data) throws Exception {

        Method[] methods = cla.getDeclaredMethods();
        Object claobj = null;


        claobj = cla.newInstance();

        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(claobj, new Object[]{
                            data.get(field)

                    });
                }
            } catch (Exception e) {
            }

        }

        return claobj;

    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static <T, V> T getBeans(String json, Class<T> cls, Class<V> rows) {
        try {
            Gson gson = new Gson();
            if (rows == null) {
                return gson.fromJson(json, cls);
            }
            Type objectType = type(cls, rows);
            return gson.fromJson(json, objectType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
