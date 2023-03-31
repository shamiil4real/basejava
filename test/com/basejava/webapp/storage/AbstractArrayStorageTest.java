package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled
public class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        storage.clear();
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            try {
                for (int i = 0; i < STORAGE_LIMIT; i++) {
                    storage.save(new Resume("name"));
                }
                storage.save(new Resume("name"));
            } catch (StorageException e) {
                fail("Array overflow before adding extra resume");
            }
        });
        assertSize(STORAGE_LIMIT);
    }
}
