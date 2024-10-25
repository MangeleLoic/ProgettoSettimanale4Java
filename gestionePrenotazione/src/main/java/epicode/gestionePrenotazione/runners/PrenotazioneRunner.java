package epicode.gestionePrenotazione.runners;

import com.github.javafaker.Faker;
import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Prenotazione;
import epicode.gestionePrenotazione.entities.Utente;
import epicode.gestionePrenotazione.services.PostazioneService;
import epicode.gestionePrenotazione.services.PrenotazioneService;
import epicode.gestionePrenotazione.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class PrenotazioneRunner implements CommandLineRunner {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PostazioneService postazioneService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        List<Utente> utenti = utenteService.findAll();
        List<Postazione> postazioni = postazioneService.findAll();

        for (int i = 0; i < 10; i++) {
            Utente utente = utenti.get(faker.number().numberBetween(0, utenti.size()));
            Postazione postazione = postazioni.get(faker.number().numberBetween(0, postazioni.size()));
            LocalDate dataPrenotazione = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));

            if (prenotazioneService.isPostazioneDisponibile(postazione, dataPrenotazione)) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setUtente(utente);
                prenotazione.setPostazione(postazione);
                prenotazione.setData(dataPrenotazione);

                try {
                    prenotazioneService.savePrenotazione(prenotazione);
                    log.info("Prenotazione salvata con successo per l'utente {} nella postazione {} per la data {}",
                            utente.getUsername(), postazione.getDescrizione(), dataPrenotazione);
                } catch (Exception ex) {
                    log.error("Errore nel salvataggio della prenotazione: {}", ex.getMessage());
                }
            } else {
                log.warn("La postazione {} non è disponibile per la data {}", postazione.getDescrizione(), dataPrenotazione);
            }
        }
    }
}
