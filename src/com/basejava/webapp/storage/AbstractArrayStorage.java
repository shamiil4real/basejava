package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            STORAGE[index] = r;
        } else {
            System.out.println("Ошибка! Резюме " + r.getUuid() + " не существует");
        }
    }

    public abstract void save(Resume r);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка! Резюме " + uuid + " не существует");
            return null;
        }
        return STORAGE[index];
    }

    public abstract void delete(String uuid);

    public final Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
