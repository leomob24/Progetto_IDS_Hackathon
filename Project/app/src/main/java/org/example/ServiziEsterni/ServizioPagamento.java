package org.example.ServiziEsterni;

import org.example.Model.Team;

import java.math.BigDecimal;

public interface ServizioPagamento {
    String effetuaPagamento(Team destinatario, BigDecimal importo);
}
