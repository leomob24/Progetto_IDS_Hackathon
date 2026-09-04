package org.example.ServiziEsterni.AdapterPagamento;

import lombok.RequiredArgsConstructor;
import org.example.Model.Team;
import org.example.ServiziEsterni.ServizioPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AdapterSistemaDiPagamento implements ServizioPagamento {
    private final SistemaDiPagamentoEsterno sistemaDiPagamentoEsterno;

    @Override
    public String effetuaPagamento(Team destinatario, BigDecimal importo) {
        if (destinatario.getIban() == null) {
            throw new IllegalArgumentException(
                    "Impossibile completare il pagamento: l'IBAN del destinatario non è stato specificato");
        }
        String iban = destinatario.getIban();
        String causale = "Premio hackathon - team " + destinatario.getNome();

        return sistemaDiPagamentoEsterno.sendPayment(iban, importo, causale);
    }
}
