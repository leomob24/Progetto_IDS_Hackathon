package org.example.Repository;

import org.example.Model.Sottomissione;
import org.example.Model.Valutazione;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepositoryValutazione {
    public Valutazione aggiungiValutazione(Valutazione valutazione) {
        Transaction transazione = null;
        try (Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            sessione.persist(valutazione);
            transazione.commit();
            return valutazione;
        } catch (Exception e) {
            if (transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Valutazione recuperaValutazione(Sottomissione sottomissione) {
        try (Session sessione = HibernateUtil.openSession()) {
            return sessione.createQuery(
                    "from Valutazione v where v.sottomissione = :sottomissione", Valutazione.class)
                    .setParameter("sottomissione", sottomissione)
                    .uniqueResult();
        }
    }

}
