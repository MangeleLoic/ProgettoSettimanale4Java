package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {


}
