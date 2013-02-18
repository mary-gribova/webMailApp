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
@NamedQueries({
@NamedQuery(name = "Session.findByUserAddress",
            query = "SELECT s FROM SessionEntity s WHERE s.userAddress = :userAddress")
})
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long sessionID;

    @OneToOne
    private UserEntity sessionUser;

    @Column(name = "sessionDate")
    private Date sessionDate;

    @Column(name = "userAddress")
    private String userAddress;

    @Column(name = "sessionNum", length = 36)
    private String sessionNum;


    public SessionEntity(long sessionID, UserEntity sessionUser) {
        this.sessionID = sessionID;
        this.sessionUser = sessionUser;
    }

    public SessionEntity() {
    }

    public long getSessionID() {
        return sessionID;
    }

    public UserEntity getSessionUser() {
        return sessionUser;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public void setSessionUser(UserEntity sessionUser) {
        this.sessionUser = sessionUser;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(String sessionNum) {
        this.sessionNum = sessionNum;
    }
}
