package vaadin.cansu.view;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.cansu.dto.BookDTO;
import vaadin.cansu.model.enums.LanguagesEnum;
import vaadin.cansu.model.enums.StatusEnum;
import vaadin.cansu.service.impl.BookService;

import java.util.Arrays;
import java.util.List;

@Route("addBook")
public class AddBook extends VerticalLayout {
    public AddBook(@Autowired BookService bookService) {


        Button homeButton = new Button("Home");
        homeButton.addClickListener(e ->
                homeButton.getUI().ifPresent(ui -> ui.navigate(""))
        );

        add(homeButton);

        TextField bookName = new TextField("Book Name");
        bookName.addThemeName("bordered");

        TextField publisher = new TextField("Publisher");
        publisher.addThemeName("bordered");

        TextField pageNumber = new TextField("Page Number");
        pageNumber.addThemeName("bordered");

        TextField editionNumber = new TextField("Edition Number");
        editionNumber.addThemeName("bordered");

        TextField category = new TextField("Category");
        category.addThemeName("bordered");

        ComboBox<LanguagesEnum> language = new ComboBox<>();
        language.setItems(
                LanguagesEnum.TURKISH,
                LanguagesEnum.ITALIAN,
                LanguagesEnum.GERMAN,
                LanguagesEnum.PORTUGUESE
        );
        language.setLabel("Language");

        ComboBox<StatusEnum> status = new ComboBox<>();
        status.setItems(StatusEnum.ACTIVE, StatusEnum.DELETED, StatusEnum.PASSIVE);
        status.setLabel("Status");

        TextField isbn = new TextField("ISBN");
        isbn.addThemeName("bordered");
        add(bookName, publisher, status, pageNumber, editionNumber,category, language, isbn);

        Button addBook = new Button("Add Book");
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

                    bookService.save(bookDTO);
                    Notification.show("Book added");

                   // bookName.clear();

                    addBook.getUI().ifPresent(ui -> ui.navigate("bookList"));
                }
        );
        add(addBook);
    }
}
