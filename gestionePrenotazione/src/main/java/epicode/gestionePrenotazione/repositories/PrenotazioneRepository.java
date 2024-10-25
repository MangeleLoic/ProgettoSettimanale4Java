package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
