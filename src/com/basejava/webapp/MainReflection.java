package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("name");

        try {
            Method method = resume.getClass().getDeclaredMethod("toString");
            System.out.println(method.invoke(resume));
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
