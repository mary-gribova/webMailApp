package webMailApp.dao.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 16.02.13
 * Time: 0:26
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "Session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long sessionID;

    @OneToOne
    private UserEntity sessionUser;

    @Column(name = "sessionDate")
    private Date sessionDate;

    public SessionEntity(long sessionID, UserEntity sessionUser) {
        this.sessionID = sessionID;
        this.sessionUser = sessionUser;
    }

    public SessionEntity() {
    }
}
