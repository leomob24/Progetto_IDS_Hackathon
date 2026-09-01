package org.example.Repository;

import org.example.Model.Staff;
import org.example.Model.Team;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepositoryTeam {
    public Team addTeam(Team team) {
        Transaction transazione = null;
        try(Session sessione = HibernateUtil.openSession();) {
            transazione = sessione.beginTransaction();
            if(esisteTeam(team)){
                throw new IllegalArgumentException("Team già esistente");
            }
            sessione.persist(team);
            transazione.commit();
            return team;
        } catch (Exception e) {
            if(transazione != null) {
                transazione.rollback();
            }
            throw new RuntimeException(e);
        }
    }
    public Team getTeamByName(String nome) {
        try(Session sessione = HibernateUtil.openSession()){
            return sessione.createQuery(
                            "from Team t where t.nome = :nome", Team.class)
                    .setParameter("nome", nome)
                    .uniqueResult();
        }
    }
    public boolean esisteTeam(Team team) {
        return getTeamByName(team.getNome()) != null;
    }
}
