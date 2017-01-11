/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ua.dao.AccountingDao;
import ua.dao.AccountingDaoImpl;
import ua.dao.models.AccountingTable;
import ua.service.IService;

/**
 *
 * @author User
 */
@Component
public class AccountingUtility {
    
    AccountingDao dao = new AccountingDaoImpl();
    DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm dd.MM.yyyy");
    DateTimeFormatter customFormat = DateTimeFormat.forPattern("dd.MM.yyyy");

    static boolean paramExist(String param) {
        return (param != null && !param.trim().isEmpty());
    }
    
    void checkFilter(String selectedFilter, HttpServletRequest request, String username, IService service) {
        List<AccountingTable> userTable;
        if ("admin".equals(username)) {
            userTable = service.getAllUsersTable();
        } else {
            userTable = service.getUserTable(username);
        }
        List<AccountingTable> filteredList = new ArrayList<>();
        if (paramExist(selectedFilter)) {
            if (selectedFilter.equals("Only income")) {
                for (AccountingTable account : userTable) {
                    if (account.getTypeOfAction().equals("Income")) {
                        if (checkDateFilter(request, account.getDateOfAction())) {
                            filteredList.add(account);
                        }
                    }
                }
                request.setAttribute("Accounts", filteredList);
            } else if (selectedFilter.equals("Only expense")) {
                for (AccountingTable account : userTable) {
                    if (account.getTypeOfAction().equals("Expense")) {
                        if (checkDateFilter(request, account.getDateOfAction())) {
                            filteredList.add(account);
                        }
                    }
                }
                request.setAttribute("Accounts", filteredList);
            } else if (selectedFilter.equals("Both")) {
                List<AccountingTable> incomeList = new ArrayList<>();
                List<AccountingTable> expensesList = new ArrayList<>();
                for (AccountingTable account : userTable) {
                    if (account.getTypeOfAction().equals("Expense")) {
                        if (checkDateFilter(request, account.getDateOfAction())) {
                            expensesList.add(account);
                        }
                    } else if (checkDateFilter(request, account.getDateOfAction())) {
                        incomeList.add(account);
                    }
                }
                request.setAttribute("incomeList", incomeList);
                request.setAttribute("expenseList", expensesList);
            }
        } else {
            request.setAttribute("Accounts", userTable);
        }
    }

    private boolean checkDateFilter(HttpServletRequest request, String dateOfAction) {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String oneDate = request.getParameter("oneDate");
        String dateFilter = request.getParameter("dateFilter");
        request.setAttribute("selectedDateFilter", dateFilter);
        DateTime aDate = format.parseDateTime(dateOfAction);
        if (paramExist(fromDate) && paramExist(toDate)) {
            DateTime fDate = customFormat.parseDateTime(fromDate);
            DateTime tDate = customFormat.parseDateTime(toDate);
            System.out.println(aDate.isBefore(tDate) && aDate.isAfter(fDate));
            return aDate.isBefore(tDate) && aDate.isAfter(fDate);
        } else if (paramExist(oneDate)) {
            DateTime oDate = customFormat.parseDateTime(oneDate);
            DateTime temp = oDate.plusDays(1);
            return aDate.isAfter(oDate) && aDate.isBefore(temp);
        } else if (paramExist(dateFilter)) {
            DateTime now = new DateTime();
            DateTime temp;
            if (dateFilter.equals("This 24 hours")) {
                temp= now.minusDays(1);
            } else if (dateFilter.equals("This week")) {
                temp = now.minusDays(7);
            } else if (dateFilter.equals("This month")) {
                temp = now.minusDays(30);
            } else {
                return true;
            }
            return aDate.isAfter(temp) && aDate.isBefore(now);
        }
        return false;
    }
}
