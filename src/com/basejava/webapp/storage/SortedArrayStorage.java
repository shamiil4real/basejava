package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }

    @Override
    protected void addElement(Resume r, int index) {
        index = index * -1 - 1;
        if (size != 0) {
            for (int i = size; i > index; i--) {
                STORAGE[i] = STORAGE[i - 1];
            }
        }
        STORAGE[index] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        for (int i = index; i < size; i++) {
            STORAGE[i] = STORAGE[i + 1];
        }
    }
}
