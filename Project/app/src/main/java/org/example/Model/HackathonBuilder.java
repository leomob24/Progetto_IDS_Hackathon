package org.example.Model;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import org.example.Model.State.HackathonState;

public interface HackathonBuilder {
    void reset();
    void setNome(String nome);
    void setLuogo(String luogo);
    void setPremio(BigDecimal premio);
    void setRegolamento(String regolamento);
    void setMaxTeamPartecipanti(int maxTeamPartecipazioni);
    void setScadenzaIscrizioni(Date data);
    void setDataInizio(Date data);
    void setDataFine(Date data);
    void setOrganizzatore(Organizzatore organizzatore);
    void setGiudice(Giudice giudice);
    void setMentori(List<Mentore> mentori);
    void setTeamIscritti(List<Iscrizione> teamIscritti);
    void setSottomissioni(List<Sottomissione> sottomissioni);
    void setSegnalazioni(List<Segnalazione> segnalazioni);
    void setStato(HackathonState stato);
    Hackathon build();

}
