package com.urise.webapp.storage;

import com.urise.webapp.exceptions.ExistStorageException;
import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(r3);
        storage.save(r2);
        storage.save(r1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void saveNonExistent() {
        int initialSize = storage.size();
        Resume r4 = new Resume("UUID_4");
        storage.save(r4);
        assertEquals(1, storage.size() - initialSize);
        assertEquals(storage.get(r4.getUuid()).getUuid(), r4.getUuid());
    }

    @Test
    public void saveExistent() {
        try {
            storage.save(r1);
            fail();
        } catch (ExistStorageException es) {
            assertEquals(3, storage.size());
        }
    }

    @Test
    public void saveOverflow() throws NoSuchFieldException, IllegalAccessException {
        int maxSize = getMaxSize();
        for (int i = storage.size() + 1; i <= maxSize; i++) {
            storage.save(new Resume());
        }
        try {
            storage.save(new Resume("UUID_TO_MUCH"));
            fail();
        } catch (StorageException s) {
            assertEquals(maxSize, storage.size());
        }
    }

    @Test
    public void deleteExistent() {
        int initialSize = storage.size();
        storage.delete(r1.getUuid());
        try {
            assertEquals(UUID_1, storage.get(UUID_1).toString());
            fail();
        } catch (NotExistStorageException nes) {
            assertEquals(1, initialSize - storage.size());
        }
    }

    @Test
    public void deleteNonExistent() {
        int initialSize = storage.size();
        try {
            storage.delete("UUID_NOT_THERE");
            fail();
        } catch (NotExistStorageException nes) {
            assertEquals(storage.size(),initialSize);
        }
    }

    @Test
    public void updateExistent() {
        Resume r4 = new Resume(UUID_3);
        storage.update(r4);
        assertEquals(r4.getUuid(), storage.get(UUID_3).getUuid());
    }

    @Test
    public void updateNonExistent() {
        Resume r4 = new Resume("UUID_NOT_THERE");
        try {
            storage.update(r4);
            fail();
        } catch (NotExistStorageException ignored) {

        }
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        assertTrue(contain(resumes, r1));
        assertTrue(contain(resumes, r2));
        assertTrue(contain(resumes, r3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getExistent() {
        Resume r = storage.get(r2.getUuid());
        assertEquals(r.getUuid(), r2.getUuid());
    }

    @Test
    public void getNonExistent() {
        try {
            storage.get("UUID_NOT_THERE");
            fail();
        } catch (NotExistStorageException ignored) {

        }
    }

    private int getMaxSize() throws NoSuchFieldException, IllegalAccessException {
        Field field = storage.getClass().getSuperclass().getDeclaredField("MAX_SIZE");
        field.setAccessible(true);
        int maxSize = (int) field.get(storage);
        field.setAccessible(false);
        return maxSize;
    }

    private boolean contain(Resume[] resumes, Resume resume) {
        for (int i = 0; i < resumes.length; i++) {
            if(resumes[i].equals(resume)) {
                return true;
            }
        }
        return false;
    }
}