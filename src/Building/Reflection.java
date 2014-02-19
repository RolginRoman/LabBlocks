/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Building;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Student
 */
public class Reflection {

    public static void main(String[] s) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cl = Class.forName(s[0]);
        Method m = cl.getMethod(s[1], Double.TYPE);
        System.out.println("Reflect Space " + m.invoke(null, Double.parseDouble(s[2])));
    }
}
