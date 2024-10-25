package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostazioneRepository extends JpaRepository<Postazione,Long> {
}
