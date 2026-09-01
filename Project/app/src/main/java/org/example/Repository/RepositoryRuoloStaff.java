package org.example.Repository;

import org.example.Model.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryRuoloStaff {
    public RuoloStaff assegnaRuoloStaff(RuoloStaff ruoloStaff) {
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            sessione.persist(ruoloStaff);
            transazione.commit();
            return ruoloStaff;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public Organizzatore getOrganizzatore(Long id) {
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.get(Organizzatore.class, id);
        }
    }
    public Organizzatore recuperaOrganizzatoreHackathon(Hackathon hackathon){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "from Organizzatore o where o.hackathon = :hackathon", Organizzatore.class)
                    .setParameter("hackathon", hackathon)
                    .uniqueResult();
        }
    }
    public Giudice getGiudice(Long id){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.get(Giudice.class, id);
        }
    }
    public Giudice recuperaGiudiceHackathon(Hackathon hackathon){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "from Giudice g where g.hackathon = :hackathon", Giudice.class)
                    .setParameter("hackathon", hackathon)
                    .uniqueResult();
        }
    }
    public Mentore getMentore(Long id){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.get(Mentore.class, id);
        }
    }
    public List<Mentore> recuperaMentoriHackathon(Hackathon hackathon){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                            "from Mentore m where m.hackathon = :hackathon", Mentore.class)
                    .setParameter("hackathon", hackathon)
                    .list();
        }
    }
    public List<Hackathon> recuperaHackathonAssegnati(Staff staff){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "select distinct r.hackathon from RuoloStaff r where r.staff = :staff", Hackathon.class)
                    .setParameter("staff", staff)
                    .list();
        }
    }
}
