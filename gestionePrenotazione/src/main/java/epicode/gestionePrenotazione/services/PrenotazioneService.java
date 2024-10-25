package epicode.gestionePrenotazione.services;

import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Prenotazione;
import epicode.gestionePrenotazione.exceptions.ValidationException;
import epicode.gestionePrenotazione.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public boolean isPostazioneDisponibile(Postazione postazione, LocalDate date) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByData(date);
        return prenotazioni.stream().noneMatch(p -> p.getPostazione().equals(postazione));
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        if ((!isPostazioneDisponibile(prenotazione.getPostazione(),prenotazione.getData()))) {
            throw new ValidationException("La postazione " + prenotazione.getPostazione() +
                    " non è disponibile per la data " + prenotazione.getData());
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> findByUtenteId(Long utenteId) {
        return prenotazioneRepository.findByUtenteId(utenteId);
    }

    public List<Prenotazione> findByData(LocalDate data) {
        return prenotazioneRepository.findByData(data);
    }

}
