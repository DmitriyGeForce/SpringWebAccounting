/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.dao;

import java.util.List;
import ua.dao.models.AccountingTable;
import ua.dao.models.Users;

/**
 *
 * @author User
 */
public interface AccountingDao {
    List<AccountingTable> getAllUsersTable();
    List<AccountingTable> getUserTable(String login);
    boolean add(AccountingTable action, String login);
    boolean remove(int accountingTableId, String login);
    AccountingTable getAction(int accountingTableId);    // get the transaction to insert it's values in the form for editing
    void update(AccountingTable account, String login);
    double getLastTotalAmount(String login);
    public Users getUserByUsername(String login);
    void reCount(String login);
}
