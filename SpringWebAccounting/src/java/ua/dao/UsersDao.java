/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.dao;

/**
 *
 * @author User
 */
public interface UsersDao {
    boolean enter(String login, String password);
    boolean register(String login, String password);
}
