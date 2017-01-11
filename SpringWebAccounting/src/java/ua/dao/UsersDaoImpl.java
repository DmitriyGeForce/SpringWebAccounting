/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ua.dao.models.Users;

/**
 *
 * @author User
 */
@Repository
public class UsersDaoImpl implements UsersDao{
    
    @PersistenceContext
    private EntityManager em;
    
    AccountingDaoImpl adi = new AccountingDaoImpl();

    @Override
    public boolean enter(String login, String password) {
        TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login);
        if (namedQuery.getResultList().isEmpty()) {
            return false;
        }
        Users user = namedQuery.getSingleResult();
        String inputPassword = adi.getEncryptedPassword(password, user.getSalt());
        String DBpassword = user.getPassword();
        return inputPassword.equals(DBpassword);
    }

    @Override
    public boolean register(String login, String password) {
        TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login);
        if (namedQuery.getResultList().isEmpty()) {
            Users user = adi.getEncryptedUser(login, password);
            em.persist(user);
            return true;
        }
        return false;
    }
    
}
