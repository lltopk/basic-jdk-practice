package com.lyflexi.genericpractice.genericService;

import java.lang.reflect.InvocationTargetException;

public class Sample {
    public static void main(String[] args) {
        execute(Resolver1.class);
        execute(Resolver2.class);
    }
    public static void execute(Class<? extends IResolver> cls) {
        IResolver iResolver = null;
        try {
            iResolver = cls.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        iResolver.process();
    }
}
