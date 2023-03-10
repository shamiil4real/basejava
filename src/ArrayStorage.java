import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int currentIndex = 0;

    void clear() {
        Arrays.fill(storage, 0, currentIndex, null);
        currentIndex = 0;
    }

    void save(Resume r) {
        storage[currentIndex] = r;
        currentIndex++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < currentIndex; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < currentIndex; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < currentIndex - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[currentIndex] = null;
            }
        }
        currentIndex--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, currentIndex);
    }

    int size() {
        return currentIndex;
    }
}
