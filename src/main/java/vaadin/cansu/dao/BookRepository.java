package vaadin.cansu.dao;


import vaadin.cansu.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByBookNameLikeAndDeletedIsFalse(String bookName);

    @Transactional
    @Modifying
    @Query("update Book b set b.deleted=true where b.id=:id")
    void deleteBook(Long id);

    List<Book> findBooksByDeletedIsFalse();

    boolean existsBooksByIsbn(Long isbn);

}
