package com.news.utils.di;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStore {
    private static final Logger log = LoggerFactory.getLogger(DataStore.class);
    private Multimap<String, Object> map;

    /**
     * Static initializer block
     */
    public DataStore() {
        map = ArrayListMultimap.create();
    }


    /**
     * Adds a single object to the key mapping
     *
     * @param key   The key to set
     * @param value The value to set
     */
    public void add(String key, Object value) {
        map.put(key, value);
    }


    /**
     * Adds a single object to the key mapping ensuring that only object object will exist for the key
     *
     * @param key   The key to set
     * @param value The value to set
     */
    public void addSingleton(String key, Object value) {
        if (map.containsKey(key)) {
            map.removeAll(key);
        }

        map.put(key, value);
    }


    /**
     * Adds multiple objects to the key mapping
     *
     * @param key    The key to set
     * @param values The list of values to set
     */
    public void add(String key, List<Object> values) {
        map.putAll(key, values);
    }


    /**
     * Gets an object from the multimap - If the map contains multiple values for the given key, only the last is returned
     *
     * @param key  The key to locate in the structure
     * @param type The Class&lt;T&gt; to return the object as
     * @return The object that maps to the key, as an instance of T
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        if (map.containsKey(key)) {
            try {
                var values = map.get(key).stream().map(e -> (T) e).collect(Collectors.toList());
                return values.get(values.size() - 1); // return the last one in the list
            } catch (ClassCastException ex) {
                log.error("Cannot cast object to " + type.getTypeName());
            }
        } else {
            log.error("The DataObjectStore does not contain any values for key: " + key + "");
        }
        return null;
    }


    /**
     * Gets all objects from the multimap
     *
     * @param key  The key to locate in the structure
     * @param type The Class&lt;T&gt; to return the list of object as
     * @return The objects that maps to the key, as instances of T
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(String key, Class<T> type) {
        if (map.containsKey(key)) {
            try {
                return map.get(key).stream()
                        .map(e -> (T) e)
                        .collect(Collectors.toList());
            } catch (ClassCastException ex) {
                log.error("Cannot cast object to " + type.getTypeName());
            }
        } else {
            log.error("The DataObjectStore does not contain any values for key: " + key + "");
        }
        return null;
    }


    /**
     * Gets all objects from the multimap matching a specified filter criteria
     *
     * @param key    The key to locate in the structure
     * @param filter The filter criteria to select
     * @param type   The Class&lt;T&gt; to return the collection of object as
     * @return The object that maps to the key, as instances of T
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(String key, Predicate<? super T> filter, Class<T> type) {
        if (map.containsKey(key)) {
            try {
                return getFlattenedList(key, type).stream()
                        .filter(filter)
                        .collect(Collectors.toList());
            } catch (ClassCastException ex) {
                log.error("Cannot cast object to " + type.getTypeName());
            }
        } else {
            log.error("The DataObjectStore does not contain any values for key: " + key + "");
        }
        return null;
    }


    /**
     * Removes all values associated with a given key
     *
     * @param key the key of entries to remove from the multimap
     * @return the collection of removed values, or an empty collection if no values were associated with the provided key.
     * The collection may be modifiable, but updating it will have no effect on the multimap.
     */
    public Collection<Object> remove(String key) {
        return map.removeAll(key);
    }


    /**
     * Removes all mappings from the data object store structure
     */
    public void clear() {
        map.clear();
    }


    /**
     * Returns the set of all keys, each appearing once in the returned set. Changes to the returned set will update the underlying multimap, and vice versa.
     *
     * @return the collection of distinct keys
     */
    public Set<String> keySet() {
        return map.keySet();
    }


    /**
     * Determines whether the specified key exists in the data object store
     *
     * @param key The key to check the existance of
     * @return True if the key exists, false otherwise
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }


    /**
     * Gets the flattened map of the object represented by the specified key
     * This is needed because the multimap stores the object as a List&lt;List&lt;T&gt;&gt;
     * NOTE: Ensure the key exists in the multimap before calling this method
     *
     * @param key  the key to locate
     * @param type The Class&lt;T&gt; to return the collection of object as
     * @return A List&lt;T&gt; of the objects matching the specified key
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> getFlattenedList(String key, Class<T> type) {

        Stream<List<T>> streamObject = (Stream) map.get(key).stream();
        return streamObject.flatMap(List::stream).map(e -> (T) e).collect(Collectors.toList());
    }
}
