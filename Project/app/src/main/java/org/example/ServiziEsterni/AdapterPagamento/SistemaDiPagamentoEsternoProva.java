package org.example.ServiziEsterni.AdapterPagamento;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class SistemaDiPagamentoEsternoProva implements SistemaDiPagamentoEsterno {

    @Override
    public String sendPayment(String iban, BigDecimal amount, String causale) {
        // invio del pagamento verso un sistema esterno: restituisce un id di transazione fittizio
        return "TXN-" + UUID.randomUUID();
    }
}
