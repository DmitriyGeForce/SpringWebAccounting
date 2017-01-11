/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.dao.AccountingDao;
import ua.dao.AccountingDaoImpl;
import ua.dao.models.AccountingTable;
import ua.dao.models.Users;
import ua.service.IService;
/**
 *
 * @author User
 */
@Controller
public class ViewController {
    
    @Autowired
    private IService service;    
    DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm dd.MM.yyyy");
    AccountingUtility utility = new AccountingUtility();
    AccountingDao dao = new AccountingDaoImpl();
    DateTimeFormatter customFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
    
    @RequestMapping(value = "/entrance", method = RequestMethod.GET)
    public String entranceGet(Model model) {
        model.addAttribute("user", new Users());
        return "entrance";
    }
    
    @RequestMapping(value = "/entrance", method = RequestMethod.POST)
    public String entrancePost(@ModelAttribute("user") Users user,
                                Model model, 
                                HttpServletRequest request, 
                                HttpServletResponse response) throws IOException {
        model.addAttribute("user", user);
        String username = user.getUsername();
        String password = user.getPassword();
        String message = null;
        if (AccountingUtility.paramExist(username) && AccountingUtility.paramExist(password)) {
            if (service.enter(username, password)) {
                if (username.equals("admin")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", "admin");
                    response.sendRedirect("admin");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("user-page");
                }
            } else {
                message = "Incorrect login or password";
            }
        }
        model.addAttribute("message", message);
        return "entrance";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("user") Users user, Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("user", user);
        String message = null;
        if (AccountingUtility.paramExist(user.getUsername()) && AccountingUtility.paramExist(user.getPassword())) {
            if (service.register(user.getUsername(), user.getPassword())) {
                response.sendRedirect("entrance");
            } else {
                message = "Username is already taken. Create another one";
            }
        }
        model.addAttribute("message", message);
        return "register";
    }
    
    @RequestMapping(value = "/user-page", method = RequestMethod.GET)
    public String userPageGet(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("row", new AccountingTable());
        HttpSession session = request.getSession(false);
        String username = session.getAttribute("username").toString();
        String removeParam = request.getParameter("remove");
        String editParam = request.getParameter("update");
        if (AccountingUtility.paramExist(removeParam)) {
            service.remove(Integer.parseInt(removeParam), username);      // username for recount total amount only for one usertable
            response.sendRedirect("user-page");
        } else if (AccountingUtility.paramExist(editParam)) {
            AccountingTable account = service.getAction(Integer.parseInt(editParam));
            model.addAttribute("editSample", account);
        }
        request.setAttribute("Accounts", service.getUserTable(username));
        return "user-page";
    }
    
    @RequestMapping(value = "/user-page", method = RequestMethod.POST)
    public String userPagePost(@ModelAttribute("row") AccountingTable row,Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("row", row);
        HttpSession session = request.getSession(false);
        String username = session.getAttribute("username").toString();
        String error = "";
        String amount = String.valueOf(row.getAmount());
        if (!AccountingUtility.paramExist(amount)) {
            error = "Input amount of money";
            model.addAttribute("error", error);
        } else if (Double.parseDouble(amount) < 1) {
            error = "You can't input negative number or 0";
            model.addAttribute("error", error);
        } else {
            String typeOfAction = request.getParameter("typeOfAction");
            if ("Income".equals(typeOfAction) || "Expense".equals(typeOfAction)) {
                if (AccountingUtility.paramExist(request.getParameter("edit"))) {
                    service.update(new AccountingTable(Double.parseDouble(amount), Integer.parseInt(request.getParameter("editSampleId")), typeOfAction), username);
                } else {
                    service.add(new AccountingTable(Double.parseDouble(amount), new DateTime().toString(format), typeOfAction), username);
                }
            }
            response.sendRedirect("user-page");
        }
        String selectedFilter = request.getParameter("filter");
        utility.checkFilter(selectedFilter, request, username, service);
        request.setAttribute("selectedFilter", selectedFilter);
        return "user-page";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("entrance");
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminGet(HttpServletRequest request){
        request.setAttribute("Accounts", service.getAllUsersTable());
        return "admin";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("searchInput");
        String selectedFilter = request.getParameter("filter");
        if (!"".equals(username) && username != null) {
            Users user = service.getUserByUsername(username);
            if (user != null) {
                request.setAttribute("Accounts", user.getAccountingTableList());
                request.setAttribute("username", user.getUsername());
                utility.checkFilter(selectedFilter, request, username, service);
            } else {
                request.setAttribute("error", "Can't find any results");
            }
        } else utility.checkFilter(selectedFilter, request, "admin", service);
        request.setAttribute("selectedFilter", selectedFilter);
        return "admin";
    }
}
