package epicode.gestionePrenotazione.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false,unique = true)
    private String fullName;
    @Column(unique = true)
    private String email;

    public Utente( String username, String fullName, String email) {

        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }


}
