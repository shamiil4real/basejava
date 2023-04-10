package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("File write error ", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", file.getAbsolutePath(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error ", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (file.exists()) {
            file.delete();
        } else {
            throw new StorageException("File does not exist: ", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        if (directory.listFiles() == null) {
            throw new StorageException("Directory is empty: ", null);
        }
        List<Resume> listResume = new ArrayList<>();
        for (File file : directory.listFiles()) {
            listResume.add(doGet(file));
        }
        return listResume;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        if (directory.listFiles() != null) {
            for (File file : directory.listFiles()) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        if (directory.listFiles() == null) {
            throw new StorageException("Directory is empty: ", null);
        }
        return directory.listFiles().length;
    }
}
