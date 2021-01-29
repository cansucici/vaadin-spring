package vaadin.cansu.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vaadin.cansu.dto.BookDTO;
import vaadin.cansu.model.entities.Book;

@Component
public class BookConverter implements IBaseConverter<Book, BookDTO> {

    private ModelMapper modelMapper;

    @Override
    public BookDTO convertToDto(Book entity) {
        modelMapper = new ModelMapper();
        return modelMapper.map(entity, BookDTO.class);
    }

    @Override
    public Book convertToEntity(BookDTO dto) {
        modelMapper = new ModelMapper();
        Book book = modelMapper.map(dto, Book.class);
        return book;
    }

//    public Book convert(BookDTO bookDTO, List<Author> authors) {
//        Book book = new Book();
//
//        book.setDeleted(bookDTO.isDeleted());
//        book.setPublisherName(bookDTO.getPublisherName());
//        book.setCategory(bookDTO.getCategory());
//        book.setLanguagesEnum(bookDTO.getLanguagesEnum());
//        book.setEditionNumber(bookDTO.getEditionNumber());
//        book.setPageNumber(bookDTO.getPageNumber());
//        book.setIsbn(bookDTO.getIsbn());
//        book.setBookName(bookDTO.getBookName());
//        book.setStatus(bookDTO.getStatus());
//        book.setAuthors(authors);
//
//        return book;
//    }
}
