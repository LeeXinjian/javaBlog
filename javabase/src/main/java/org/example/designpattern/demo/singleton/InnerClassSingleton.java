package org.example.designpattern.demo.singleton;

import java.lang.reflect.Field;

/**
 * 静态内部类方式获取单例
 */
public class InnerClassSingleton {

    private static class InnerClassHolder {
        static{
            System.out.println("初始化InnerClassHolder");
        }
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
        System.out.println("实例化InnerClassSingleton");
        if (InnerClassHolder.instance != null) {
            throw new RuntimeException(" 单例不允许多个实例 ");
        }
    }

    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.instance;
    }

    public static void main(String[] args) {
        InnerClassSingleton instance1 = getInstance();
//
//        Class<?>[] declaredClasses = InnerClassSingleton.class.getDeclaredClasses();
//        for (Class<?> aClass : declaredClasses) {
//            try {
//                Field instance = aClass.getDeclaredField("instance");
//                instance.set(null,new InnerClassSingleton());
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//        InnerClassSingleton instance = getInstance();
//
//        System.out.println(instance1.equals(instance));
//        System.out.println(instance1.hashCode());
//        System.out.println(instance.hashCode());
    }

}
