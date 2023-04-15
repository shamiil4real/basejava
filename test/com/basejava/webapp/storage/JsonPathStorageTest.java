package com.basejava.webapp.storage;


import com.basejava.webapp.storage.serialization.JsonSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonSerializer()));
    }
}
