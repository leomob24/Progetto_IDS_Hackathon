package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatiHackathon {
    private String nome;
    private String regolamento;
    private String luogo;
    private BigDecimal premio;
    private int maxTeamPartecipanti;
    private Date scadenzaIscrizioni;
    private Date dataInizio;
    private Date dataFine;

}
