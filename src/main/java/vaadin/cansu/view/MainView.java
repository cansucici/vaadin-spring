package vaadin.cansu.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends HorizontalLayout {

    public MainView() {
        Button bookList = new Button("Book List");
        bookList.addClickListener(e ->
                bookList.getUI().ifPresent(ui -> ui.navigate("bookList"))
        );
        Button addBook = new Button("Add Book");
        addBook.addClickListener(e ->
                addBook.getUI().ifPresent(ui -> ui.navigate("addBook"))
        );

        add(bookList, addBook);

        Button authorList = new Button("Author List");
        authorList.addClickListener(buttonClickEvent -> Notification.show("This page doesn't exists."));
        add(authorList);

        Button addAuthor = new Button("Add Author");
        addAuthor.addClickListener(buttonClickEvent -> Notification.show("This page doesn't exists."));
        add(addAuthor);

        Button register = new Button("Register");
        register.addClickListener(buttonClickEvent -> Notification.show("This page doesn't exists."));

        Button login = new Button("Login");
        login.addClickListener(buttonClickEvent -> Notification.show("This page doesn't exists."));
        add(register, login);
    }


}
