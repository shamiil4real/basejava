package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка! Нет места для сохранения резюме");
        } else if (index >= 0) {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " уже существует");
        } else {
            index = index * -1 - 1;
            if (size != 0) {
                for (int i = size; i > index; i--) {
                    STORAGE[i] = STORAGE[i - 1];
                }
            }
            STORAGE[index] = r;
            size++;
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                STORAGE[i] = STORAGE[i + 1];
            }
            STORAGE[size] = null;
            size--;
        } else {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
        }
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
