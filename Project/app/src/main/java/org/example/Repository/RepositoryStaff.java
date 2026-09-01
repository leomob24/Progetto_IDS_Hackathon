package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.Organizzatore;
import org.example.Model.Staff;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepositoryStaff {
    public Staff aggiungiStaff(Staff staff) {
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            if(esisteUsername(staff.getUsername())){
                throw new IllegalArgumentException("Membro Staff già esistente");
            }
            sessione.persist(staff);
            transazione.commit();
            return staff;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public Staff getStaffUsername(String username){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                            "from Staff f where f.username = :username", Staff.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }
    public boolean esisteUsername(String Username){
        return getStaffUsername(Username) != null;
    }
}
