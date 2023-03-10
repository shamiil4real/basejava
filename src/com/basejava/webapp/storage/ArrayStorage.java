package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size;
    private static final int STORAGE_LIMIT = 10000;

    public void update(Resume r) {
        int tmp = isPresent(r.getUuid());
        if (tmp >= 0) {
            storage[tmp] = r;
        } else {
            System.out.println("Ошибка! Разюме " + r.getUuid() + " не существует");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int tmp = isPresent(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка! Нет места для сохранения разюме");
        } else {
            if (tmp < 0) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Ошибка! Разюме " + r.getUuid() + " уже существует");
            }
        }
    }

    public Resume get(String uuid) {
        int tmp = isPresent(uuid);
        if (tmp >= 0) {
            return storage[tmp];
        } else {
            System.out.println("Ошибка! Разюме " + uuid + " не существует");
        }
        return null;
    }

    public void delete(String uuid) {
        int tmp = isPresent(uuid);
        if (tmp >= 0) {
            storage[tmp] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка! Разюме " + uuid + " не существует");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int isPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
