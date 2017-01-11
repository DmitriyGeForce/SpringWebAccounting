/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.service;

import java.util.List;
import ua.dao.models.AccountingTable;
import ua.dao.models.Users;

/**
 *
 * @author User
 */
public interface IService {
    public List<AccountingTable> getAll();
    public boolean enter(String login, String password);
    public boolean register(String username, String password);
    public void remove(int accountingTableId, String username);
    public AccountingTable getAction(int accountingTableId);
    public double getLastTotalAmount(String username);
    public List<AccountingTable> getUserTable(String username);
    public void update(AccountingTable accountingTable, String username);
    public void add(AccountingTable accountingTable, String username);
    public List<AccountingTable> getAllUsersTable();

    public Users getUserByUsername(String username);
}
