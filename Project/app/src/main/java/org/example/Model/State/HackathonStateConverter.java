package org.example.Model.State;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HackathonStateConverter implements AttributeConverter<HackathonState, String> {

    @Override
    public String convertToDatabaseColumn(HackathonState stato) {
        return stato != null ? stato.getNome() : null;
    }

    @Override
    public HackathonState convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "IN_ISCRIZIONE"  -> new StatoInIscrizione();
            case "IN_CORSO"       -> new StatoInCorso();
            case "IN_VALUTAZIONE" -> new StatoInValutazione();
            case "CONCLUSO"       -> new StatoConcluso();
            default -> throw new IllegalArgumentException("Stato sconosciuto: " + dbData);
        };
    }
}
