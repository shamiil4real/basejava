package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "UUID1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "UUID2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "UUID3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume4 = new Resume(UUID_1);
        storage.update(resume4);
        assertSame(resume4, storage.get(UUID_1));
    }

    @Test
    public void updateNotExistedResume() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            Resume resume = new Resume();
            storage.update(resume);
        });
    }

    @Test
    public void getAll() {
        Resume[] arrayResume1 = storage.getAll();
        Resume[] arrayResume2 = storage.getAll();
        assertArrayEquals(arrayResume1, arrayResume2);
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        assertEquals(4, storage.size());
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void saveExistedResume() {
        Resume resume4 = new Resume(UUID_2);
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(resume4);
        });
        assertEquals(3, storage.size());
    }

    @Test
    public void saveExtraResume() {
        for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }

        try {
            storage.save(new Resume());
        } catch (StorageException e) {
            fail("Array overflow before adding extra resume");
        }
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_1);
        });
    }

    @Test
    public void deleteNotExistedResume() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }
}