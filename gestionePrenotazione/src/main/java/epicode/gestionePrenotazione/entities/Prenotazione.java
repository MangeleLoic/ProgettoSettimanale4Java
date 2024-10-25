package epicode.gestionePrenotazione.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;
}
