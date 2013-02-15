package webMailApp.dao.entities;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 16.02.13
 * Time: 0:26
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class SessionEntity {

    @Id
    private UUID sessionID;


    @Column(name = "sessionUser")
    @OneToOne
    private UserEntity sessionUser;

    public SessionEntity(UUID sessionID, UserEntity sessionUser) {
        this.sessionID = sessionID;
        this.sessionUser = sessionUser;
    }

    public SessionEntity() {
    }
}
