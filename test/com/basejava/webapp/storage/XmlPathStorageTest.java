package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.XmlSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerializer()));
    }
}
