package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка! Нет места для сохранения резюме");
        } else if (index >= 0) {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " уже существует");
        } else {
            STORAGE[size] = r;
            size++;
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            STORAGE[index] = STORAGE[size - 1];
            STORAGE[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
