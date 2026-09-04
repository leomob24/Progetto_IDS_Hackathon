package org.example.ServiziEsterni.AdapterPagamento;

import java.math.BigDecimal;

public interface SistemaDiPagamentoEsterno {
    String sendPayment(String iban, BigDecimal amount, String causale);
}
