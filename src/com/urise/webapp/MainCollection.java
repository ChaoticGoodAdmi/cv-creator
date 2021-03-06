package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorageByUuid;
import com.urise.webapp.storage.Storage;

import java.util.List;

public class MainCollection {

    private final static Storage LIST_STORAGE = new MapStorageByUuid();

    public static void main(String[] args) {

        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        LIST_STORAGE.save(r3);
        LIST_STORAGE.save(r1);
        LIST_STORAGE.save(r2);

        System.out.println("Get r1: " + LIST_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + LIST_STORAGE.size());

        printAll();
        LIST_STORAGE.update(r1);
        LIST_STORAGE.delete(r1.getUuid());
        printAll();
        LIST_STORAGE.clear();
        printAll();
        System.out.println("Size: " + LIST_STORAGE.size());

    }

    private static void printAll() {
        System.out.println("\nGet All");
        List<Resume> resumeList = LIST_STORAGE.getAllSorted();
        resumeList.forEach(System.out::println);
    }
}
