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
public class Edificio {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String indirizzo;
    @Column(nullable = false)
    private String citta;

    public Edificio(Long id, String nome, String indirizzo, String citta) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
    }
}
