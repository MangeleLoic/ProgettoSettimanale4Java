package epicode.gestionePrenotazione.repositories;

import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione,Long> {
    List<Postazione> findByTipoPostazioneAndEdificioCitta(Tipo tipoPostazione, String citta);
}
