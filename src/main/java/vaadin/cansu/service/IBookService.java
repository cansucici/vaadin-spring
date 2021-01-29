package vaadin.cansu.service;


import vaadin.cansu.dto.BookDTO;
import vaadin.cansu.model.entities.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBooks();

    Book save(BookDTO dto);

    Book update(Long id, BookDTO dto);

    BookDTO getBookById(Long id);

    void deleteBook(Long id); // deleted

    List<Book> getBooksByBookName(String bookName);
}
