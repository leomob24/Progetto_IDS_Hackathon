package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.Sottomissione;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositorySottomissioni {
    public Sottomissione aggiungiSottomissione(Sottomissione sottomissione){
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            if(sottomissione.getIscrizione() == null){
                throw new IllegalArgumentException("Iscrizione non valida");
            }
            sessione.persist(sottomissione);
            transazione.commit();
            return sottomissione;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }

    }
    public List<Sottomissione> recuperaSottomissioni(Hackathon hackathon){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "select s.iscrizione i from Sottomissione s where i.hackathon = :hackathon", Sottomissione.class)
                    .setParameter("hackathon", hackathon)
                    .list();
        }
    }
    public Sottomissione aggiornaSottomissione(Sottomissione sottomissione){
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            if(sottomissione == null){
                throw new IllegalArgumentException("Sottomissione non esiste");
            }
            Sottomissione aggiornato  = sessione.merge(sottomissione);
            transazione.commit();
            return aggiornato;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
