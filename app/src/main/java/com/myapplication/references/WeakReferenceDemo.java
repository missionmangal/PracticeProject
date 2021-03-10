package com.myapplication.references;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        Person person = new Person("Vishnu",31);

        WeakReference<Person> weakReference = new WeakReference<>(new Person("Vishnu",31));

        System.out.println(weakReference.get().toString());
//        person = null;
        System.gc();
        System.out.println(weakReference.get());


    }
}
