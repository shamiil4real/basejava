package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void addElement(Resume r, int index) {
        STORAGE[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        STORAGE[index] = STORAGE[size - 1];
    }
}
