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

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

}
