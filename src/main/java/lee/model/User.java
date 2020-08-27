package lee.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private int uid;
    private String username;

    public User() {
    }

    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }
}
