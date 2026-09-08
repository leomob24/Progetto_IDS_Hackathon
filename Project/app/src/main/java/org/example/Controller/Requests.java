package org.example.Controller;

/**
 * Record di richiesta REST per i payload che non corrispondono a un DTO già esistente in dto/Dati*
 * (quei DTO restano quelli usati dai Gestori; questi coprono solo campi aggiuntivi specifici degli endpoint,
 * es. credenziali di login o un singolo campo come l'IBAN).
 */
final class Requests {
    private Requests() {
    }

    record LoginRequest(String username, String password) {
    }

    record CreaTeamRequest(String nome) {
    }

    record IbanRequest(String iban) {
    }

    record CreaSegnalazioneRequest(String motivazione, Long teamId, Long hackathonId, Long mentoreId) {
    }

    record CreaRichiestaSupportoRequest(String oggetto, String descrizione) {
    }
}
