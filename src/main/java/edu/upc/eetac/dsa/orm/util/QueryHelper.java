package edu.upc.eetac.dsa.orm.util;

import java.lang.reflect.Field;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("id");
        for (String field: fields) {
            if(!field.equals("id")) sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            if(!field.equals("id")) sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECTbyID(Class theClass) {
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE ID=?");
        return sb.toString();
    }

    public static String createQuerySELECTALL(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("id = ?");
        for (String field: fields) {
            if(!field.equals("id")) sb.append(", ").append(field).append(" = ?");
        }
        sb.append(" WHERE ID = '"+ObjectHelper.getter(entity, "id")).append("'");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");
        return sb.toString();
    }

}
