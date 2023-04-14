package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.SerializationStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new SerializationStrategy()));
    }
}
