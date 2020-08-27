package lee.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Book {
    private int bid;
    private User user;
    private String title;
    private List<Section> sections;

    public Book() {
        this.sections = new ArrayList<>();
    }

    public Book(int bid, User user, String title) {
        this.bid = bid;
        this.user = user;
        this.title = title;
        this.sections = new ArrayList<>();
    }
}
