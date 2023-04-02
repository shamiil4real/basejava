package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public final void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public final void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    protected abstract boolean isExist(Object searchKey);
}
