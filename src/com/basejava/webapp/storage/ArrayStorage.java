package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;

    private final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    private int size;

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            STORAGE[index] = r;
        } else {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " не существует");
        }
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка! Нет места для сохранения резюме");
        } else if (index != -1) {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " уже существует");
        } else {
            STORAGE[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return STORAGE[index];
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            STORAGE[index] = STORAGE[size - 1];
            STORAGE[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
