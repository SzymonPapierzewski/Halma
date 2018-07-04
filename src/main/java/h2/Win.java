package h2;

import javax.persistence.*;

@Entity
@Table(name = "win")
public class Win {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    String winPlayerName;

    @Column
    String loserName1;

    @Column
    String loserName2;

    @Column
    String loserName3;
}