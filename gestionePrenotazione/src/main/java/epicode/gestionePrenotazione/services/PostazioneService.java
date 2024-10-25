package epicode.gestionePrenotazione.services;

import epicode.gestionePrenotazione.entities.Edificio;
import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Tipo;
import epicode.gestionePrenotazione.entities.Utente;
import epicode.gestionePrenotazione.exceptions.NotFoundException;
import epicode.gestionePrenotazione.exceptions.ValidationException;
import epicode.gestionePrenotazione.repositories.PostazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public void savePostazione(Postazione postazione) {
        List<Postazione> postazione1 = postazioneRepository.findByTipoPostazioneAndEdificioCitta(postazione.getTipoPostazione(),postazione.getEdificio().getCitta()  );
        if (!postazione1.isEmpty()) {
            throw new ValidationException("Postazione di tipo " +postazione.getTipoPostazione() + " già esistente" + " nell'edificio " + postazione.getEdificio().getNome());
        }
        postazioneRepository.save(postazione);

    }

    public void saveManyPostazioni(List<Postazione> newPostazioni) {
        for (Postazione postazione: newPostazioni) {
            try {
                this.savePostazione(postazione);
            } catch (ValidationException ex) {

                log.error("Errore nel salvataggio della postazione " + postazione.getId());
            }
        }
    }

    public List<Postazione> findPostazioni(Tipo tipoPostazione, String citta) {
        List<Postazione> postazioni = postazioneRepository.findByTipoPostazioneAndEdificioCitta(tipoPostazione, citta);
        if (postazioni.isEmpty()){
            System.out.println("Errore nella ricerca");
            throw new NotFoundException("Non sono state trovate postazioni disponibili per il tipo " + tipoPostazione + " nella città " + citta);
        }
        return postazioni;
    }
}
