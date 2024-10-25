package epicode.gestionePrenotazione.services;

import epicode.gestionePrenotazione.entities.Utente;
import epicode.gestionePrenotazione.exceptions.ValidationException;
import epicode.gestionePrenotazione.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.ErrorManager;


@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;


   public void save(Utente utente) {
        Optional<Utente> utente1 = utenteRepository.findByUsername(utente.getUsername());
        if (utente1.isPresent()) {
            throw new ValidationException("utente con username " +utente.getUsername() + " già esistente");
        }
       // utenteRepository.save(utente);
    }

    public void saveMany(List<Utente> newUtenti) {
        for (Utente utente: newUtenti) {
            try {
                this.save(utente);
            } catch (ValidationException ex) {

                log.error(ex.getMessage());
            }
        }
    }


}
