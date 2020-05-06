package edu.upc.eetac.dsa.orm;

import edu.upc.eetac.dsa.orm.util.ObjectHelper;
import edu.upc.eetac.dsa.orm.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void close() {
    }

    @Override
    public Object get(Class theClass, String id) {
        Object object = null;
        PreparedStatement statement;
        //Instantiating a object of type class for the getters
        try {
            String selectQuery = QueryHelper.createQuerySELECT(theClass.newInstance());
            object = theClass.newInstance();
            statement = conn.prepareStatement(selectQuery);
            statement.setObject(1, id);
            ResultSet rs =  statement.executeQuery();
            int i = 0;
            //INVOKE SETTER FOR EACH CORRESPONDING PROPERTY OF THE TABLE TO MAP WITH OBJECT
            while (rs.next()){
                //SQL WILL NEVER RETURN LIST AS A RESULT
                ResultSetMetaData rsmd = rs.getMetaData();
                for(i=1;i<=rsmd.getColumnCount();i++){
                    String name = rs.getString(i);
                    ObjectHelper.setter(object, name, rs.getObject(i));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {
        String delete = QueryHelper.createQueryDELETE(object);
        PreparedStatement pstm = null;
        try {
            pstm=conn.prepareStatement(delete);
            for(String field: ObjectHelper.getFields(object)){
                if(field.equals("id")) {
                    pstm.setObject(1, ObjectHelper.getter(object, field));
                }
                pstm.executeQuery();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
