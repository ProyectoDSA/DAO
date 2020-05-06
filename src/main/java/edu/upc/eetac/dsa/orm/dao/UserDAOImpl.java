package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.User;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    @Override
    public User getUser(String id){
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.get(User.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return user;
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
    public void updateUser(String id, String name, String mail) {
        User user = this.getUser(id);
        user.setNombre(name);
        user.setMail(mail);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(String userID) {
        User user = this.getUser(userID);
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
    public List<User> getUsers() {
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
