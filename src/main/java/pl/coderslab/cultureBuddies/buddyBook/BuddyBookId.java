package pl.coderslab.cultureBuddies.buddyBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@Data
public class BuddyBookId implements Serializable {
    @Column(name = "buddy_id")
    private Long buddyId;
    @Column(name = "book_id")
    private Long bookId;
}

