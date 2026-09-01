package org.example.Repository;
import org.example.Model.Utente;
import org.example.Model.Team;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryUtenti {

    public void assegnaTeam(Utente utente, Team team){
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            if(utente.getTeam() != null){
                throw new IllegalArgumentException("Utente già appartente ad un team");
            }
            if(!esisteUtente(utente.getUsername())){
                throw new IllegalArgumentException("Utente non esistente");
            }
            utente.setTeam(team);
            team.addMembro(utente);
            sessione.merge(utente);
            transazione.commit();
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public List<Utente> getUtentiSenzaTeam(){
        try(Session session = HibernateUtil.openSession()){
            return session.createQuery(
                    "from Utente u where u.team is null", Utente.class)
                    .getResultList();
        }
    }
    public Utente addUtente(Utente utente){
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            sessione.persist(utente);
            transazione.commit();
            return utente;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public Utente getUtenteById (Long id){
        try(Session session = HibernateUtil.openSession()){
            return session.get(Utente.class, id);
        }
    }
    public Utente getUtenteByUsername (String username){
        try(Session session = HibernateUtil.openSession()){
            return session.createQuery(
                    "from Utente u where u.username = :username", Utente.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }
    public boolean esisteUtente(String username){
        return getUtenteByUsername(username)!=null;
    }
}
