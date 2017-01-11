/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ua.dao.models.AccountingTable;
import ua.dao.models.Users;

/**
 *
 * @author User
 */
@Repository
public class AccountingDaoImpl implements AccountingDao {

    List<String> alphaNum = new ArrayList<>();

    public AccountingDaoImpl() {
        for (char c = 'A'; c <= 'z'; c++) {
            String s = new String();
            s += c;
            alphaNum.add(s);
            if (c == 'Z') {
                c = 'a' - 1;
            }
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AccountingTable> getAllUsersTable() {
       return em.createNamedQuery("AccountingTable.findAll", AccountingTable.class).getResultList();
    }

    @Override
    public List<AccountingTable> getUserTable(String login) {
        return em.createNamedQuery("AccountingTable.findByUserId", AccountingTable.class).setParameter("userId", em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login).getSingleResult()).getResultList();
    }

    @Override
    public boolean add(AccountingTable action, String login) {
        double currentAmount = action.getAmount();
        double totalAmount = getLastTotalAmount(login);
        if (action.getTypeOfAction().equals("Income")) {
            action.setTotalAmount(totalAmount + currentAmount);
        } else {
            action.setTotalAmount(totalAmount - currentAmount);
        }
        action.setUserId(em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login).getSingleResult());
        em.persist(action);
        return true;
    }

    @Override
    public boolean remove(int accountingTableId, String login) {
        em.remove(em.find(AccountingTable.class, accountingTableId));
        reCount(login);
        return true;
    }

    @Override
    public AccountingTable getAction(int accountingTableId) {
        Query action = em.createNamedQuery("AccountingTable.findByAccountingTableId", AccountingTable.class).setParameter("accountingTableId", accountingTableId);
        if (action.getResultList().isEmpty()) {
            return null;
        } else {
            return (AccountingTable) action.getSingleResult();
        }
    }
    @Override
    public void update(AccountingTable editAccount, String login) {
        AccountingTable row = em.createNamedQuery("AccountingTable.findByAccountingTableId", AccountingTable.class).setParameter("accountingTableId", editAccount.getAccountingTableId()).getSingleResult();
        row.setAmount(editAccount.getAmount());
        row.setTotalAmount(editAccount.getTotalAmount());
        row.setTypeOfAction(editAccount.getTypeOfAction());
        reCount(login);
    }

    @Override
    public double getLastTotalAmount(String login) {
        int userId = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login).getSingleResult().getUserId();
        Query lastUserAction = em.createNativeQuery("SELECT * FROM accounting.accounting_table WHERE user_id = ? ORDER BY accounting_table_id DESC LIMIT 1", AccountingTable.class).setParameter(1, userId);
        if (lastUserAction.getResultList().isEmpty()) {
            return 0;
        } else {
            AccountingTable lastRow = (AccountingTable) lastUserAction.getSingleResult();
            return lastRow.getTotalAmount();
        }
    }

    Users getEncryptedUser(String login, String password) {
        String salt = getRandomSalt();
        String saltedPassword = salt + password;
        MessageDigest md = getMessageDigest();
        md.update(saltedPassword.getBytes());
        byte byteData[] = md.digest();
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            encryptedPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        Users user = new Users(login, salt, encryptedPassword.toString());
        return user;
    }

    private String getRandomSalt() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            salt.append(alphaNum.get((int) (Math.random() * alphaNum.size())));
        }
        return salt.toString();
    }

    private MessageDigest getMessageDigest() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex1) {
            Logger.getLogger(AccountingDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return md;
    }

    String getEncryptedPassword(String password, String salt) {
        String saltedPassword = salt + password;
        MessageDigest md = getMessageDigest();
        md.update(saltedPassword.getBytes());
        byte byteData[] = md.digest();
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            encryptedPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return encryptedPassword.toString();
    }

    @Override
    public Users getUserByUsername(String login) {
        Users user = null;
        TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", login);
        if (!query.getResultList().isEmpty()) {
            user = query.getSingleResult();
        }
        return user;
    }
    
    public Users getUserByUserId(int userId) {
        return em.createNamedQuery("Users.findByUserId", Users.class).setParameter("userId", userId).getSingleResult();
    }

    @Override
    public void reCount(String login) {
        double totalAmount=0;
        List<AccountingTable> accounts = getUserTable(login);
        for (AccountingTable account : accounts) {
            AccountingTable tempAccount = em.createNamedQuery("AccountingTable.findByAccountingTableId", AccountingTable.class).setParameter("accountingTableId", account.getAccountingTableId()).getSingleResult();
            double currentAmount = tempAccount.getAmount();
            if (tempAccount.getTypeOfAction().equals("Income")) {
                totalAmount = totalAmount + currentAmount;
                tempAccount.setTotalAmount(totalAmount);
            } else {
                totalAmount = totalAmount - currentAmount;
                tempAccount.setTotalAmount(totalAmount);
            }
        }
    }

}
