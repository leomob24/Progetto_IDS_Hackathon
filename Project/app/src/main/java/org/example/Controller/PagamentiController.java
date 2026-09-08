package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Gestori.GestorePagamenti;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Caso d'uso: erogazione del premio al team vincitore via ServizioPagamento.
 * Chiamata separata e successiva a POST /hackathon/{id}/vincitore, non parte della macchina a stati.
 */
@RestController
@RequestMapping("/pagamenti")
@RequiredArgsConstructor
class PagamentiController {
    private final GestorePagamenti gestorePagamenti;

    record EsitoPagamento(String transazioneId) {
    }

    @PostMapping("/{hackathonId}/eroga")
    EsitoPagamento erogaPremio(@PathVariable long hackathonId) {
        return new EsitoPagamento(gestorePagamenti.erogaPremio(hackathonId));
    }
}
