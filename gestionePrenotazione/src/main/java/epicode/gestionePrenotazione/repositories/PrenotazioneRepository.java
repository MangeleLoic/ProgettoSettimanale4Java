package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteId(Long utenteId);
    List<Prenotazione> findByData(LocalDate data);
    List<Prenotazione> findByPostazioneIdAndData(Long postazioneId,LocalDate data);
}
