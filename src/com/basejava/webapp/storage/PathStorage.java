package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serialization.StreamSerializerStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final StreamSerializerStrategy streamSerializerStrategy;
    private final Path directory;

    protected PathStorage(String dir, StreamSerializerStrategy streamSerializerStrategy) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        this.streamSerializerStrategy = streamSerializerStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    private Stream<Path> getListFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerializerStrategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getListFiles().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        getListFiles().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getListFiles().count();
    }
}
