package epicode.gestionePrenotazione.services;


import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Tipo;
import epicode.gestionePrenotazione.exceptions.NotFoundException;
import epicode.gestionePrenotazione.exceptions.ValidationException;
import epicode.gestionePrenotazione.repositories.PostazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public void savePostazione(Postazione postazione) {
        if (postazione == null){
            log.error("Errore nel salvataggio della postazione");
            throw new ValidationException("La postazione non può essere null");
        }

        if (postazione.getEdificio() == null || postazione.getTipoPostazione() == null) {
            log.error("Postazione non valida: {}", postazione);
            throw new ValidationException("I valori non possono essere null");
        }

        List<Postazione> postazioneEsistente = postazioneRepository.findByTipoPostazioneAndEdificioCitta(postazione.getTipoPostazione(), postazione.getEdificio().getCitta());
        if (!postazioneEsistente.isEmpty()) {
            log.error("Postazione di tipo {} già esistente nell'edificio {}", postazione.getTipoPostazione(), postazione.getEdificio().getNome());
            throw new ValidationException("Postazione di tipo " + postazione.getTipoPostazione() + " già esistente nell'edificio " + postazione.getEdificio().getNome());
        }

        try {
            postazioneRepository.save(postazione);
            log.info("Postazione salvata con successo: {}", postazione);
        } catch (Exception e) {
            log.error("Errore nel salvataggio della postazione: {}", e.getMessage());
            throw new ValidationException("Errore durante il salvataggio della postazione");
        }
    }


    public void saveManyPostazioni(List<Postazione> newPostazioni) {
        if (newPostazioni == null || newPostazioni.isEmpty()) {
            log.warn("Lista di postazioni da salvare è null o vuota");
            return;
        }
        for (Postazione postazione: newPostazioni) {
            if (postazione == null){
                log.error("Postazione null trovata");
                continue;
            }
            try {
                this.savePostazione(postazione);
            } catch (ValidationException ex) {

                log.error("Errore nel salvataggio della postazione " + postazione.getId());
            }
        }
    }

    public List<Postazione> findAll() {
        return postazioneRepository.findAll();
    }
}
