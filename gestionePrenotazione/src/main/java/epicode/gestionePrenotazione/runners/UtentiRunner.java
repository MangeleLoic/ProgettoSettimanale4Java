package epicode.gestionePrenotazione.runners;

import com.github.javafaker.Faker;
import epicode.gestionePrenotazione.entities.Utente;
import epicode.gestionePrenotazione.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class UtentiRunner implements CommandLineRunner {

    @Autowired
    private UtenteService utenteService;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        List<Utente> utenti = new ArrayList<>();

        for(int i=0; i < 20; i++) {
            Utente newUtente = new Utente(faker.harryPotter().character(),faker.name().fullName(),faker.internet().emailAddress());
            utenti.add(newUtente);
        }

        utenteService.saveMany(utenti);
        log.info("utente salvato con successo");

    }
}
