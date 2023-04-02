package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void doUpdate(Resume r, Integer index) {
        list.set(index, r);
    }

    @Override
    public void doSave(Resume r, Integer index) {
        list.add(r);
    }

    @Override
    public Resume doGet(Integer index) {
        return list.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        list.remove((index).intValue());
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }
}
