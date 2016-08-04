package fr.ekito.myweatherlibrary.di;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


/**
 * Simple Dependency Injector
 * agiuliani
 */
public class Injector {

    final static String TAG = "Injector";

    static Map<Class, Object> instances = new HashMap<>();

    /**
     * get a component
     *
     * @param clazz component Class
     * @param <T>   Type
     * @return
     * @throw IllegalStateException if not present
     */
    @NonNull
    public static <T> T get(Class<T> clazz) {
        Object o = instances.get(clazz);
        if (o == null)
            throw new IllegalStateException("Instance not found for class : '" + clazz.getSimpleName() + "'"
            );
        return (T) o;
    }

    /**
     * get a component
     *
     * @param clazz component Class
     * @param <T>   Type
     * @return Component if present
     */
    @Nullable
    public static <T> T getOrNull(Class<T> clazz) {
        T t = (T) instances.get(clazz);
        Log.v(TAG, "get " + clazz.getSimpleName() + " :: " + t);
        return t;
    }

    /**
     * Add new component
     *
     * @param o
     */
    public static void add(Object o) {
        if (o != null) {
            add(o, o.getClass());
        }
    }

    /**
     * Replace an existing or not component
     *
     * @param o
     * @param clazz
     */
    public static void add(Object o, Class clazz) {
        Object existingInstance = getOrNull(clazz);
        if (existingInstance != null) {
            remove(clazz);
        }
        if (o != null && clazz != null) {
            Log.v(TAG, "Add instance for " + clazz.getSimpleName());
            instances.put(clazz, o);
        } else {
            throw new IllegalStateException("can't add instance for : object:" + o + " & class:" + clazz);
        }
    }

    /**
     * Intialize components from Module definition
     *
     * @param module
     */
    public static void load(Class<? extends Module> module) {
        try {
            Module mod = module.newInstance();
            mod.load();
        } catch (Throwable e) {
            throw new IllegalStateException("Module prepare " + module.getSimpleName() + " error : " + e);
        }
    }

    /**
     * remove an instance
     *
     * @param clazz
     */
    public static void remove(Class clazz) {
        Log.v(TAG, "Remove class : " + clazz.getSimpleName());
        instances.remove(clazz);
    }

    /**
     * clear all instances
     */
    public static void clear() {
        Log.v(TAG, "Clear All instances !");
        instances.clear();
    }

}
