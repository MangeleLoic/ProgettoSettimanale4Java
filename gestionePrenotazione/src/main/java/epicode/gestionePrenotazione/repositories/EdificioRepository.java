package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}
