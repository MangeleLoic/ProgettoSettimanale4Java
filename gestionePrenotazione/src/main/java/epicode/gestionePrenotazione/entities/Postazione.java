package epicode.gestionePrenotazione.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Postazione {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String descrizione;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipoPostazione;
    @Column(nullable = false)
    private int numeroMaxOccupanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id",nullable = false)
    private Edificio edificio;

    public Postazione(String descrizione, int numeroMaxOccupanti, Tipo tipoPostazione, Edificio edificio) {
        this.id = id;
        this.descrizione = descrizione;
        this.numeroMaxOccupanti = numeroMaxOccupanti;
        this.tipoPostazione = tipoPostazione;
        this.edificio = edificio;
    }
}
