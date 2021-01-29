package vaadin.cansu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vaadin.cansu.converter.BookConverter;
import vaadin.cansu.dao.BookRepository;
import vaadin.cansu.dto.BookDTO;
import vaadin.cansu.model.entities.Book;
import vaadin.cansu.service.IBookService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findBooksByDeletedIsFalse();
    }

    @Override
    public Book save(BookDTO bookDTO) {

        boolean isExist = bookRepository.existsBooksByIsbn(bookDTO.getIsbn());
        if (isExist) {
            throw new RuntimeException("Aynı barkod numarasına sahip kitap bulunmaktadır. " +
                    "Barkod numaranızı tekrar kontrol ediniz!");
        }

        Book book = bookConverter.convertToEntity(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, BookDTO dto) {

        Book book = bookRepository.findById(id).orElse(new Book());

        book.setUpdateDate(new Date());
        book.setDeleted(dto.isDeleted());
        book.setPublisherName(dto.getPublisherName());
        book.setCategory(dto.getCategory());
        book.setLanguagesEnum(dto.getLanguagesEnum());
        book.setEditionNumber(dto.getEditionNumber());
        book.setPageNumber(dto.getPageNumber());
        book.setIsbn(dto.getIsbn());
        book.setBookName(dto.getBookName());
        book.setStatus(dto.getStatus());

        return bookRepository.save(book);
    }

    @Override
    public BookDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Aradığınız kitap bulunamadı."));
        return bookConverter.convertToDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Aradığınız kitap bulunamadı."));
        bookRepository.deleteById(book.getId());
    }

    @Override
    public List<Book> getBooksByBookName(String bookName) {

        Collection<Book> books = bookRepository.findByBookNameLikeAndDeletedIsFalse(bookName);

        if (CollectionUtils.isEmpty(books)) {
            throw new RuntimeException("Bu isimde kitap bulunmamaktadır.");
        } else {
            return bookRepository.findByBookNameLikeAndDeletedIsFalse(bookName);
        }
    }
}
