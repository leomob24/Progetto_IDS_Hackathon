package org.example.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.example.Model.State.HackathonState;

public class ConcreteHackathonBuilder implements HackathonBuilder{
    private Hackathon hackathon;
    @Override
    public void reset() {
        this.hackathon = new Hackathon();
    }
    @Override
    public void setNome(String nome) {
        this.hackathon.setNome(nome);
    }
    @Override
    public void setLuogo(String luogo){
        this.hackathon.setLuogo(luogo);
    }
    @Override
    public void setPremio(BigDecimal premio) {
        this.hackathon.setPremio(premio);
    }
    @Override
    public void setRegolamento(String regolamento) {
        this.hackathon.setRegolamento(regolamento);
    }
    @Override
    public void setMaxTeamPartecipanti(int maxTeamPartecipanti) {
        this.hackathon.setMaxTeamPartecipanti(maxTeamPartecipanti);
    }
    @Override
    public void setScadenzaIscrizioni(Date data){
        this.hackathon.setScadenzaIscrizioni(data);
    }
    @Override
    public void setDataInizio(Date data){
        this.hackathon.setDataInizio(data);
    }
    @Override
    public void setDataFine(Date data){
        this.hackathon.setDataFine(data);
    }
    @Override
    public void setOrganizzatore(Organizzatore organizzatore){
        this.hackathon.setOrganizzatore(organizzatore);
    }
    @Override
    public void setGiudice(Giudice giudice){
        this.hackathon.setGiudice(giudice);
    }
    @Override
    public void setMentori(List<Mentore> mentori){
        this.hackathon.setMentori(mentori);
    }
    @Override
    public void setTeamIscritti(List<Iscrizione> teamIscritti){
        this.hackathon.setTeamIscritti(teamIscritti);
    }
    @Override
    public void setStato(HackathonState stato){
        this.hackathon.setStato(stato);
    }
    @Override
    public void setSottomissioni(List<Sottomissione> sottomissioni){
        this.hackathon.setSottomissioni(sottomissioni);
    }
    @Override
    public void setSegnalazioni(List<Segnalazione> segnalazioni){
        this.hackathon.setSegnalazioni(segnalazioni);
    }
    @Override
    public Hackathon build() {
        return this.hackathon;
    }

}
