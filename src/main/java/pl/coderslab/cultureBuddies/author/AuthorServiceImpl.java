package pl.coderslab.cultureBuddies.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.cultureBuddies.buddies.Buddy;
import pl.coderslab.cultureBuddies.buddies.BuddyService;
import pl.coderslab.cultureBuddies.exceptions.NotExistingRecordException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BuddyService buddyService;

    @Override
    public boolean checkIfAuthorExists(Long authorId) throws NotExistingRecordException {
        final Optional<Author> author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new NotExistingRecordException("Author with id " + authorId + " does not exist");
        }
        return true;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAllByOrderByLastName();
    }

    @Override
    public List<Author> getOrderedAuthorsListOfBuddy(Long buddyId) {
        return authorRepository.findByBuddyIdOrderByLastName(buddyId);
    }

    @Override
    public Author saveIfNotExistYet(String firstName, String lastName) {
        final Optional<Author> authorFromDB = authorRepository.findFirstByFirstNameAndLastName(firstName, lastName);
        return authorFromDB.orElseGet(() -> authorRepository.save(Author.builder()
                .firstName(firstName).lastName(lastName).build()));
    }


    @Override
    public List<Author> getOrderedAuthorsListOfPrincipalUser() throws NotExistingRecordException {
        final Buddy principal = buddyService.getPrincipal();
        return authorRepository.findByBuddyIdOrderByLastName(principal.getId());
    }

    @Override
    public Author findById(Long authorId) throws NotExistingRecordException {
        return authorRepository.findById(authorId).orElseThrow(new NotExistingRecordException("Author with id " + authorId +
                " does not exist in database!"));
    }
}
