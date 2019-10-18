package server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "connections")
public class Connections {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 10, nullable = false)
    private long id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "invitation_id")
    private long invitation_id;

    @Column(name = "key")
    private String key;

    @Column(name = "permission")
    private int permission;

    public Connections() {
    }

    public Connections(long user_id, long invitation_id, String key, int permission) {
        this.user_id = user_id;
        this.invitation_id = invitation_id;
        this.key = key;
        this.permission = permission;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(long invitation_id) {
        this.invitation_id = invitation_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
