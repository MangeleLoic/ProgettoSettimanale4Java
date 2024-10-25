package epicode.gestionePrenotazione.services;

import epicode.gestionePrenotazione.entities.Edificio;
import epicode.gestionePrenotazione.exceptions.ValidationException;
import epicode.gestionePrenotazione.repositories.EdificioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio saveEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public void saveManyEdifici(List<Edificio> newEdificio) {
        for (Edificio edificio: newEdificio) {
            try {
                this.saveEdificio(edificio);
            } catch (ValidationException ex) {

                log.error("Errore nel salvataggio dell'edificio " + edificio.getNome(),ex);
            }
        }
    }
}
