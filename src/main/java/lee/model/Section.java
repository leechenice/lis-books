package lee.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Section {
    private int sid;
    private String uuid;
    private String name;

    public Section() {

    }

    public Section(int sid, String uuid, String name) {
        this.sid = sid;
        this.uuid = uuid;
        this.name = name;
    }
}
