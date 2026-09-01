package org.example.Repository;

import org.example.Model.Invito;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepositoryInviti {

    public void aggiungi(Invito invito) {
        Transaction transazione= null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            sessione.persist(invito);
            transazione.commit();
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public boolean invitoEsistente(Team team, Utente utente) {
        try(Session session = HibernateUtil.openSession()){
            return !session.createQuery(
                    "from Invito i where i.team = :team and i.utente = :utente", Invito.class)
                    .setParameter("team", team)
                    .setParameter("utente", utente).getResultList()
                    .isEmpty();
        }
    }

}
