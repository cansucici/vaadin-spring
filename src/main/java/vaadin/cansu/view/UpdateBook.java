package vaadin.cansu.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.cansu.dto.BookDTO;
import vaadin.cansu.model.entities.Book;
import vaadin.cansu.model.enums.LanguagesEnum;
import vaadin.cansu.model.enums.StatusEnum;
import vaadin.cansu.service.impl.BookService;

import java.util.List;

@Route("updateBook")
public class UpdateBook extends VerticalLayout implements HasUrlParameter<String> {

    @Autowired
    private BookService bookService;

    public UpdateBook() {
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        BookDTO book = bookService.getBookById(Long.valueOf(s));

        Button homeButton = new Button("Home");
        homeButton.addClickListener(e ->
                homeButton.getUI().ifPresent(ui -> ui.navigate(""))
        );
        add(homeButton);

        TextField bookName = new TextField("Book Name");
        bookName.setValue(book.getBookName());
        bookName.addThemeName("bordered");

        TextField publisher = new TextField("Publisher");
        publisher.setValue(book.getPublisherName());
        publisher.addThemeName("bordered");

        TextField pageNumber = new TextField("Page Number");
        pageNumber.setValue(book.getPageNumber().toString());
        pageNumber.addThemeName("bordered");

        TextField editionNumber = new TextField("Edition Number");
        editionNumber.setValue(book.getEditionNumber().toString());
        editionNumber.addThemeName("bordered");

        TextField category = new TextField("Category");
        category.setValue(book.getCategory());
        category.addThemeName("bordered");

        ComboBox<LanguagesEnum> language = new ComboBox<>();
        language.setItems(
                LanguagesEnum.TURKISH,
                LanguagesEnum.ITALIAN,
                LanguagesEnum.GERMAN,
                LanguagesEnum.PORTUGUESE
        );
        language.setValue(book.getLanguagesEnum());
        language.setLabel("Language");

        ComboBox<StatusEnum> status = new ComboBox<>();
        status.setItems(StatusEnum.ACTIVE, StatusEnum.DELETED, StatusEnum.PASSIVE);
        status.setValue(book.getStatus());
        status.setLabel("Status");

        TextField isbn = new TextField("ISBN");
        isbn.addThemeName("bordered");
        isbn.setValue(book.getIsbn().toString());
        add(bookName, publisher, status, pageNumber, editionNumber,category, language, isbn);

        Button addBook = new Button("Update Book");
        addBook.addClickListener(buttonClickEvent -> {
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setBookName(bookName.getValue());
                    bookDTO.setPublisherName(publisher.getValue());
                    bookDTO.setStatus(status.getValue());
                    bookDTO.setEditionNumber(Integer.valueOf(editionNumber.getValue()));
                    bookDTO.setLanguagesEnum((language.getValue()));
                    bookDTO.setCategory(category.getValue());
                    bookDTO.setPageNumber(Long.valueOf(pageNumber.getValue()));
                    bookDTO.setIsbn(Long.valueOf(isbn.getValue()));

                    bookService.update(book.getId(), bookDTO);
                    Notification.show("Book updated");
            addBook.getUI().ifPresent(ui -> ui.navigate("bookList"));
                }
        );
        add(addBook);

    }
}
