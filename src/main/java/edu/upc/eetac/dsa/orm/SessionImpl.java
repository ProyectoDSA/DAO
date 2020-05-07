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
        ResultSet rs;
        Object object;

        String selectQuery = QueryHelper.createQuerySELECTALL(theClass);
        System.out.println(selectQuery);

        Statement statement = null;

        try {
            object = theClass.newInstance();
            statement = this.conn.createStatement();
            statement.execute(selectQuery);
            rs = statement.getResultSet();
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i=1; i<=rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object,field,rs.getObject(i));
                    res.add(rs.getObject(i));
                    System.out.println("Objects founded: "+ rs.getObject(i).toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        return res;
    }

    public Object findByID(Class theClass, String id) {
        ResultSet rs;
        Object object = null;

        String selectByIdQuery = QueryHelper.createQuerySELECTbyID(theClass);
        System.out.println(selectByIdQuery);

        PreparedStatement pstm;

        try {
            object = theClass.newInstance();
            pstm = this.conn.prepareStatement(selectByIdQuery);
            pstm.setObject(1, id);
            rs = pstm.executeQuery();
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object,field,rs.getObject(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Object founded: "+object);
        return object;
    }

    public void update(Object object) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(updateQuery);
            statement.setObject(1, ObjectHelper.getter(object, "id"));
            statement.setObject(2, ObjectHelper.getter(object, "nombre"));
            statement.setObject(3, ObjectHelper.getter(object, "mail"));
            System.out.println(updateQuery);
            statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(Object object) {
        String deleteQuery = QueryHelper.createQueryDELETE(object);
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(deleteQuery);
            statement.setObject(1, ObjectHelper.getter(object, "id"));
            System.out.println(deleteQuery);
            statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
    }
}
