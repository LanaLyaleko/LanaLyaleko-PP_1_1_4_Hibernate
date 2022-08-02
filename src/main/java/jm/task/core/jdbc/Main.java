package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Q", "W", (byte) 25);
        userDaoHibernate.saveUser("A", "B", (byte) 26);
        userDaoHibernate.saveUser("B", "C", (byte) 27);
        userDaoHibernate.saveUser("M", "N", (byte) 28);

        System.out.println(userDaoHibernate.getAllUsers());

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}