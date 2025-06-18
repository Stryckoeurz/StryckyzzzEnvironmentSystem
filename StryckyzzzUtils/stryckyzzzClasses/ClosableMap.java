package stryckyzzzClasses;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public class ClosableMap implements Closeable {
    private Map<String, String> data;
    private boolean isClosed;

    public ClosableMap() {
        this.data = new HashMap<>();
        this.isClosed = false;
    }
    
    public void put(String key, String value) {
        ensureOpen();
        data.put(key, value);
    }

    public String get(String key) {
        ensureOpen();
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return data.get(key);
    }

    public void close() {
        if (!isClosed) {
            data.clear();
            data = null;
            isClosed = true;
        }
    }

    public boolean isClosed() {
        return isClosed;
    }
    
    private void ensureOpen() {
        if (isClosed) {
            throw new IllegalStateException("Map is closed.");
        }
    }
}
