package pl.coderslab.cultureBuddies.buddyBook;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import pl.coderslab.cultureBuddies.books.Book;
import pl.coderslab.cultureBuddies.buddies.Buddy;
import pl.coderslab.cultureBuddies.buddyBook.BuddyBookId;

import javax.persistence.*;

@Entity
@Table(name = "buddies_books")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@Builder
@ToString
public class BuddyBook {
    @EmbeddedId
    private BuddyBookId id = new BuddyBookId();

    @ManyToOne
    @MapsId("buddyId")
    private Buddy buddy;
    @Transient
    private Long buddyId;

    @ManyToOne
    @MapsId("bookId")
    private Book book;
    @Transient
    private Long bookId;

    private String comment;
    @Range(min = 0, max = 10)
    private Integer rate;

    public BuddyBook(Buddy buddy, Book book) {
        this.buddy = buddy;
        this.book = book;
    }
}
