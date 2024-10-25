package epicode.gestionePrenotazione.runners;


import com.github.javafaker.Faker;
import epicode.gestionePrenotazione.entities.Edificio;
import epicode.gestionePrenotazione.entities.Postazione;
import epicode.gestionePrenotazione.entities.Tipo;
import epicode.gestionePrenotazione.services.EdificioService;
import epicode.gestionePrenotazione.services.PostazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class PostazioniRunner implements CommandLineRunner {

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private EdificioService edificioService;
    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.ITALY);

        List<Edificio> edifici = new ArrayList<>();

        for(int i=0; i <5; i++) {
            Edificio newEdificio = new Edificio(faker.company().name(),faker.address().fullAddress(),faker.address().city());
              edificioService.saveEdificio(newEdificio); // Riga commentata per evitare ulteriori aggiunte nel db
            edifici.add(newEdificio);
        }

        for (Edificio edificio: edifici) {
            List<Postazione> postazioni = new ArrayList<>();
            for (int ind = 0; ind < 4; ind++) {
                Postazione newPostazione = new Postazione(faker.lorem().sentence(5),faker.number().numberBetween(5,25), Tipo.values()[faker.number().numberBetween(0, Tipo.values().length)],
                        edificio);
                postazioni.add(newPostazione);
            }
             postazioneService.saveManyPostazioni(postazioni); // Riga commentata per evitare ulteriori aggiunte nel db
        }

    }
}
