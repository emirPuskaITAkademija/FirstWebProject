package com.solution.best.app.dynamic.ejb;

import javax.ejb.Stateless;
/**
 * JPA EntityManager
 * <p>
 * NamedQuery
 * @author Grupa1
 */
@Stateless
public class LoginSessionBean implements LoginSessionBeanLocal{
    


    @Override
    public boolean login(String username, String password) {
        //ovdje ide JPA 
        return "admin".equals(username) && "admin".equals(password);
    }
    
}
