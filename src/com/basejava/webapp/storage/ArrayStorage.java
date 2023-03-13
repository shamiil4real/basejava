package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void update(Resume r) {
        if (isPresent(r.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("Ошибка! Разюме " + r.getUuid() + " не существует");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Ошибка! Нет места для сохранения разюме");
        } else {
            if (!isPresent(r.getUuid())) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Ошибка! Разюме " + r.getUuid() + " уже существует");
            }
        }
    }

    public Resume get(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("Ошибка! Разюме " + uuid + " не существует");
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else {
            System.out.println("Ошибка! Разюме " + uuid + " не существует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    boolean isPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
