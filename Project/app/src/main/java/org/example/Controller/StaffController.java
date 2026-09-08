package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.HackathonResponse;
import org.example.Controller.Responses.RuoloStaffResponse;
import org.example.Controller.Responses.StaffResponse;
import org.example.Gestori.GestoreStaff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: assegnazione ruoli staff (Organizzatore/Giudice/Mentore) su un hackathon.
 */
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
class StaffController {
    private final GestoreStaff gestoreStaff;

    @GetMapping("/hackathon/{hackathonId}/mentori-non-assegnati")
    List<StaffResponse> getMentoriNonAssegnati(@PathVariable long hackathonId) {
        return gestoreStaff.getMentoriNonAssegnati(hackathonId).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/{staffId}/hackathon-assegnati")
    List<HackathonResponse> getHackathonAssegnati(@PathVariable long staffId) {
        return gestoreStaff.getHackathonAssegnati(staffId).stream().map(ResponseMapper::toResponse).toList();
    }

    @PostMapping("/{staffId}/giudice/{hackathonId}")
    ResponseEntity<RuoloStaffResponse> assegnaGiudice(@PathVariable long staffId, @PathVariable long hackathonId) {
        var giudice = gestoreStaff.assegnaGiudice(staffId, hackathonId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(giudice));
    }

    @PostMapping("/{staffId}/organizzatore/{hackathonId}")
    ResponseEntity<RuoloStaffResponse> assegnaOrganizzatore(@PathVariable long staffId, @PathVariable long hackathonId) {
        var organizzatore = gestoreStaff.assegnaOrganizzatore(staffId, hackathonId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(organizzatore));
    }

    @PostMapping("/hackathon/{hackathonId}/mentori")
    ResponseEntity<List<RuoloStaffResponse>> assegnaMentore(@PathVariable long hackathonId,
                                                              @RequestParam List<Long> staffIds) {
        var mentori = gestoreStaff.assegnaMentore(staffIds, hackathonId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mentori.stream().map(ResponseMapper::toResponse).toList());
    }
}
