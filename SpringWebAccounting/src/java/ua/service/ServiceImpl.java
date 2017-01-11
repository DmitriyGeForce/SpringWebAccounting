/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.AccountingDao;
import ua.dao.UsersDao;
import ua.dao.models.AccountingTable;
import ua.dao.models.Users;

/**
 *
 * @author User
 */
@Service
@Transactional
public class ServiceImpl implements IService{
    
    @Autowired
    private AccountingDao accountingDao;
    
    @Autowired
    private UsersDao usersDao;
    
    @Override
    public List<AccountingTable> getAll() {
        return accountingDao.getAllUsersTable();
    }

    @Override
    public boolean enter(String login, String password) {
        return usersDao.enter(login, password);
    }

    @Override
    public boolean register(String username, String password) {
        return usersDao.register(username, password);
    }

    @Override
    public void remove(int accountingTableId, String username) {
        accountingDao.remove(accountingTableId, username);
    }

    @Override
    public AccountingTable getAction(int accountingTableId) {
        return accountingDao.getAction(accountingTableId);
    }

    @Override
    public double getLastTotalAmount(String username) {
        return accountingDao.getLastTotalAmount(username);
    }

    @Override
    public List<AccountingTable> getUserTable(String username) {
        return accountingDao.getUserTable(username);
    }

    @Override
    public void update(AccountingTable accountingTable, String username) {
        accountingDao.update(accountingTable, username);
    }

    @Override
    public void add(AccountingTable accountingTable, String username) {
        accountingDao.add(accountingTable, username);
    }

    @Override
    public List<AccountingTable> getAllUsersTable() {
        return accountingDao.getAllUsersTable();
    }

    @Override
    public Users getUserByUsername(String username) {
        return accountingDao.getUserByUsername(username);
    }
}
