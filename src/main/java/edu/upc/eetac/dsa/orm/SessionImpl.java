package edu.upc.eetac.dsa.orm;

import edu.upc.eetac.dsa.orm.util.ObjectHelper;
import edu.upc.eetac.dsa.orm.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.*;
import static java.sql.Types.BOOLEAN;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);
        System.out.println(insertQuery);

        PreparedStatement pstm = null;

        try {
            String idValue = (String) ObjectHelper.getter(entity, "id");
            pstm = this.conn.prepareStatement(insertQuery);
            pstm.setObject(1, idValue);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                if(!field.equals("id")) pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Object> findAll(Class theClass) {
        System.out.println("Preguntar metadata SessionImpl/findAll");
        List<Object> res = new ArrayList<>();
        ResultSet rs = null;
        Object object;

        String selectQuery = QueryHelper.createQuerySELECTALL(theClass);
        System.out.println(selectQuery);

        Statement statement = null;

        try {
            statement = this.conn.createStatement();
            statement.execute(selectQuery);
            rs = statement.getResultSet();
            while(rs.next()) {
                for (int i=1; i<=ObjectHelper.getNumberFields(theClass); i++) {
                    System.out.println(rs.getString(i));
                    res.add(rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*while(true){
            try {
                if (!rs.next()){
                    object = theClass.newInstance();
                    for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                        String columnName = rs.getMetaData().getColumnName(i);
                        columnName = columnName.substring(0,1).toUpperCase() + columnName.substring(1);
                        ObjectHelper.setter(object, columnName, rs.getString(rs.getMetaData().getColumnLabel(i)));
                        res.add(object);
                    }
                    break;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }*/
        return res;
    }

    public Object findByID(Class theClass, String id) {
        ResultSet rs = null;
        Object object;

        String selectByIdQuery = QueryHelper.createQuerySELECTbyID(theClass);
        System.out.println(selectByIdQuery);

        PreparedStatement pstm;

        try {
            pstm = this.conn.prepareStatement(selectByIdQuery);
            pstm.setObject(1, id);
            pstm.executeQuery();
            rs = pstm.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                object = theClass.newInstance();
                if (!rs.next()){
                    for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                        String columnName = rs.getMetaData().getColumnName(i);
                        columnName = columnName.substring(0,1).toUpperCase() + columnName.substring(1);
                        theClass.getMethod("set" + columnName, String.class).invoke(object);
                    }
                    break;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Object founded: "+object);
        return object;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public void close() {
    }
}
