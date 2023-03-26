package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void makeUpdate(Resume r, int index);

    protected abstract void makeSave(Resume r, int index);

    protected abstract Resume makeGet(int index);

    protected abstract void makeDelete(int index);

    public abstract void clear();

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (isExist(r.getUuid())) {
            makeUpdate(r, index);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (!isExist(r.getUuid())) {
            makeSave(r, index);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExist(uuid)) {
            return makeGet(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(uuid)) {
            makeDelete(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public abstract Resume[] getAll();

    public abstract int size();

    private boolean isExist(String uuid) {
        return getIndex(uuid) >= 0;
    }
}
