package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.User;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    @Override
    public User getUser(String id){
        Session session = null;
        User u = null;
        try{
            session = FactorySession.openSession();
            u = (User) session.findByID(User.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public String addUser(String name, String mail) {
        Session session = null;
        String userID = null;
        try {
            session = FactorySession.openSession();
            User user = new User(name, mail);
            userID = user.getId();
            session.save(user);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return userID;
    }

    @Override
    public void updateUser(String id, String nombre, String mail) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            User u = (User) session.findByID(User.class, id);
            if(u!=null) {
                u.setNombre(nombre);
                u.setMail(mail);
                session.update(u);
            }
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(String id) {
        User user = this.getUser(id);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(user);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> usersList=null;
        try {
            session = FactorySession.openSession();
            usersList = session.findAll(User.class);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return usersList;
    }
}
