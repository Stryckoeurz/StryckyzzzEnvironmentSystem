package LibraryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private HashMap<String, Shelf> library;

    public Library() {
        library = new HashMap<>();
    }

    public void put(String key, Shelf shelf) {
        library.put(key, shelf);
    }

    public Shelf getShelf(String key) {
        return library.get(key);
    }

    public List<Shelf> getAllShelves() {
        return new ArrayList<>(library.values());
    }

    public List<TextSpace> getAllDictionaries() {
        List<TextSpace> result = new ArrayList<>();
        for (Shelf shelf : library.values()) {
            result.addAll(shelf.getAllTextSpace());
        }
        return result;
    }

    public void clear() {
        for (Shelf shelf : library.values()) {
            shelf.clear();
        }
        library.clear(); // Don't null it
    }
}
