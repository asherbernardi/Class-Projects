/**
 * List235Maker.java
 * 
 * Wrapper to instantiate by name a subclass of List235.
 *
 * @author CGG (with a start from TVD)
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 16, 2016
 */

import java.lang.reflect.*;

public class List235Maker {

    public static List235 makeInstance(String className) {
	String detail = "No detail for "+className;
        try {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    Class<?> listClass = loader.loadClass(className);
	    
	    Object theInstance = listClass.newInstance();
	    return (List235) theInstance;
	}
        catch (ClassNotFoundException cnfe) {
            detail = "class " + className + " not found.";
        }
        catch (InstantiationException nsme) {
            detail ="class " + className + " could not be instantiated";
        }
        catch (IllegalAccessException iae) {
            detail = "constructor for " + className + " must be public.";
        }
        catch (ClassCastException cce) {
            detail = "class " + className
		+ " must implement interface List235.";
        }
	throw new ClassCastException(detail);
    }



}
