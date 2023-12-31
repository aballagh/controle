/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aballagh.services;


import com.aballagh.entity.Admin;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aball
 */

// AdminService.java (dans le package approprié)
@Stateless
public class AdminFacade {
    @PersistenceContext(unitName = "aballaghPU")
    private EntityManager entityManager;
     
    
    public Admin findAdminByUsername(String userName) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.userName = :userName", Admin.class);
        query.setParameter("userName", userName);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public boolean validateLogin(String userName, String password) {
        Admin admin = findAdminByUsername(userName);
        return admin != null && admin.getPassword().equals(password);
    }

     
}
