package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Employee;
import edu.upc.eetac.dsa.orm.model.User;

public class UserDAOImpl implements UserDAO{

    public User getUser(int id){
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User)session.get(User.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return user;
    }
}
