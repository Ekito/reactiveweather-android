package fr.ekito.myweatherlibrary.di;

/**
 * Injector Module
 * Help define instances
 */
public abstract class Module {

    /**
     * load module definition
     */
    public abstract void load();

    /**
     * modules to load
     * @param modules
     */
    public void extend(Class<? extends Module>... modules) {
        for (Class<? extends Module> m : modules) {
            Injector.load(m);
        }
    }

    /**
     * provides several object instances
     * @param instances
     */
    public void provides(Object... instances) {
        for (Object o : instances) {
            Injector.add(o);
        }
    }

    /**
     * provide one object instance
     * @param instance
     * @param clazz
     */
    public void provide(Object instance, Class clazz){
        Injector.add(instance,clazz);
    }
}
