package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.DataSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataSerializer()));
    }
}
