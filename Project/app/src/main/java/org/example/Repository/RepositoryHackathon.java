package org.example.Repository;
import org.example.Model.Hackathon;
import org.example.Model.Mentore;
import org.example.Model.Team;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryHackathon {
    public Hackathon addHackathon(Hackathon hackathon){
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            if(esisteHackathon(hackathon.getNome())){
                throw new IllegalArgumentException("Hackathon già esistente");
            }
            sessione.persist(hackathon);
            transazione.commit();
            return hackathon;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }

    }
    public boolean esisteHackathon(String nome){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "from Hackathon h where h.nome = :nome", Hackathon.class)
                    .setParameter("nome", nome)
                    .uniqueResult() != null;
        }
    }
    public List<Hackathon> getHackathon(){
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                    "from Hackathon", Hackathon.class)
                    .getResultList();
        }
    }
    public Hackathon aggiorna(Hackathon hackathon){
        Transaction transazione = null;
        try( Session sessione = HibernateUtil.openSession()){
            transazione = sessione.beginTransaction();
            if(sessione.get(Hackathon.class, hackathon.getId()) == null){
                throw new IllegalArgumentException("Hackathon non esistente");
            }
            Hackathon aggiornato  = sessione.merge(hackathon);
            transazione.commit();
            return aggiornato;
        }catch (Exception e){
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public List<Mentore> aggiungiMentori (List<Mentore> mentori, Hackathon hackathon) {
        Transaction transazione = null;
        try (Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            hackathon.addMentori(mentori);
            sessione.merge(hackathon);
            transazione.commit();
            return mentori;
        } catch (Exception e) {
            if (transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Team registraVincitore(Team team, Hackathon hackathon){
        Transaction transazione = null;
        try (Session sessione = HibernateUtil.openSession()) {
            transazione = sessione.beginTransaction();
            if(hackathon.getTeamVincitore() != null){
                throw new IllegalArgumentException("Team Vincitore già presente");
            }
            hackathon.setTeamVincitore(team);
            sessione.merge(hackathon);
            transazione.commit();
            return team;
        } catch (Exception e) {
            if (transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
