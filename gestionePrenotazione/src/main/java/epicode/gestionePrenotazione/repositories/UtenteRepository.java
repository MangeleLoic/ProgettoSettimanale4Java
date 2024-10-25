package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
