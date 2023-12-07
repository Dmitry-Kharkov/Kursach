package com.example.searchteam.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseEntityTest {

    @BeforeAll
    protected void initTest() throws Exception {
        checkCollectionInit();
        checkLongConstructor();

    }


    private void checkCollectionInit() throws ClassNotFoundException {

        Class<?> clazz = Class.forName(this.getClass().getName().replace("Test", ""));
        Class<?> collectionType = java.util.Collection.class;

        Object objectTest;

        try {
            objectTest = clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка при создании экземпляра класса: " + ex.getMessage());
        }

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> collectionType.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    String getterNameMethod = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);

                    try {
                        Method getter = clazz.getDeclaredMethod(getterNameMethod);
                        Assertions.assertNotNull(getter.invoke(objectTest), String.format("В классе %s поле %s должно быть проинициализировано  Collections.emptyList()", clazz.getName(), field.getName()));
                    } catch (Exception e) {
                        throw new RuntimeException("Произошла ошибка при поиске или запуске метода: " + e.getMessage());
                    }

                });


    }


    protected void checkLongConstructor() throws Exception {
        Class<?> clazz = Class.forName(this.getClass().getName().replace("Test", ""));
        var longConstructor = clazz.getDeclaredConstructor(Long.class);
        Assertions.assertNotNull(longConstructor, String.format("В классе %s отсутствует конструктор с параметром типа Long", clazz.getName()));
    }


    protected void checkNumFields(int numFields) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(this.getClass().getName().replace("Test", ""));
        Assertions.assertEquals(numFields, getAllFields(clazz).size(), String.format("Колличество полей в классе %s не соответсвует заявленному", clazz.getName()));
    }


    private List<Field> getAllFields(Class<?> clazz) {

        var fields = new LinkedList<Field>();

        var isSuperClass = new AtomicBoolean(false);


        while (clazz.getSuperclass() != null) {

            fields.addAll(Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> !isSuperClass.get() || Modifier.isPublic(field.getModifiers()) || Modifier.isProtected(field.getModifiers()))
                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                    .collect(Collectors.toList())
            );

            clazz = clazz.getSuperclass();
            isSuperClass.set(true);
        }

        return fields;

    }


}

