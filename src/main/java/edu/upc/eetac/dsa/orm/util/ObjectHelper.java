package edu.upc.eetac.dsa.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {

    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }

    public static int getNumberFields(Class theClass){

        Field[] fields = theClass.getDeclaredFields();
        int cont = 0;
        for(Field f: fields) cont++;
        return cont;
    }


    public static void setter(Object object, String property, Object value) {
        // Method // invoke
        Method method = null;
        try{
            method = object.getClass().getMethod(getSetter(property),value.getClass());
            method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //System.out.println(res);
    }

    private static String getGetter(String property){
        return "get"+property.substring(0,1).toUpperCase()+property.substring(1);
    }

    private static String getSetter(String property){
        return "set"+property.substring(0,1).toUpperCase()+property.substring(1);
    }

    public static Object getter(Object object, String property) {
        // Method // invoke
        Method method = null;
        Object res = null;
        try {
            method = object.getClass().getDeclaredMethod(getGetter(property), null);
            res = method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
}
