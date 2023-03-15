package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void update(Resume r) {
        int tmp = resumeIndex(r.getUuid());
        if (tmp >= 0) {
            storage[tmp] = r;
        } else {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " не существует");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int tmp = resumeIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка! Нет места для сохранения резюме");
        } else {
            if (tmp < 0) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Ошибка! Резюме " + r.getUuid() + " уже существует");
            }
        }
    }

    public Resume get(String uuid) {
        int tmp = resumeIndex(uuid);
        if (tmp >= 0) {
            return storage[tmp];
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
        return null;
    }

    public void delete(String uuid) {
        int tmp = resumeIndex(uuid);
        if (tmp >= 0) {
            storage[tmp] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int resumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
