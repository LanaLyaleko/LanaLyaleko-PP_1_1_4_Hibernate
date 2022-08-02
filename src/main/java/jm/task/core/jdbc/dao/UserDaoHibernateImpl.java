package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    List<User> users;

    public UserDaoHibernateImpl() {
    }


    public void createUsersTable() {
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS `users`.`users`" +
                "(  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `lastName` VARCHAR(45) NOT NULL," +
                "  `age` tinyint(4) NOT NULL," +
                "PRIMARY KEY (`id`))";
        session.createSQLQuery(createUserTableQuery).executeUpdate();
        transaction.commit();
        util.hibernateUtilConnection().close();
        session.close();
    }


    public void dropUsersTable() {
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        String dropUsersTableQuery = "DROP TABLE IF EXISTS `users`.`users`";
        session.createSQLQuery(dropUsersTableQuery).executeUpdate();
        transaction.commit();
        util.hibernateUtilConnection().close();
        session.close();
    }


    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        util.hibernateUtilConnection().close();
        session.close();
    }


    public void removeUserById(long id) {
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        User removedUser = (User) session.get(User.class, id);
        session.delete(removedUser);
        transaction.commit();
        util.hibernateUtilConnection().close();
        session.close();
    }


    public List<User> getAllUsers() {
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        users = (List<User>) session.createCriteria(User.class).list();
        transaction.commit();
        session.close();
        util.hibernateUtilConnection().close();
        return users;
    }


    public void cleanUsersTable() {
        Util util = new Util();
        session = util.hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        List<User> cleanedUserList = (List<User>) session.createCriteria(User.class).list();
        for (User u : cleanedUserList) {
            session.delete(u);
        }
        transaction.commit();
        session.close();
        util.hibernateUtilConnection().close();
    }
}