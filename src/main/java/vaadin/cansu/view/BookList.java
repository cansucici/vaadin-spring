package vaadin.cansu.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.cansu.model.entities.Book;
import vaadin.cansu.service.impl.BookService;
import java.util.List;

@Route("bookList")
public class BookList extends VerticalLayout {

    public BookList(@Autowired BookService bookService) {

        Button homeButton = new Button("Home");
        homeButton.addClickListener(e ->
                homeButton.getUI().ifPresent(ui -> ui.navigate(""))
        );

        add(homeButton);

        List<Book> getBooks = bookService.getAllBooks();

        Grid<Book> grid = new Grid<>(Book.class);
        grid.setItems(getBooks);

        grid.addComponentColumn(item -> createRemoveButton(grid, item, bookService)).setHeader("Delete");
        grid.addComponentColumn(item -> createUpdateButton(item)).setHeader("Update");

        add(grid);
    }

    private Button createRemoveButton(Grid<Book> grid, Book item, BookService bookService) {
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Book> dataProvider = (ListDataProvider<Book>) grid.getDataProvider();
            bookService.deleteBook(item.getId());
            dataProvider.getItems().remove(item);
            dataProvider.refreshAll();
        });
        return button;
    }

    private Button createUpdateButton(Book item) {
        Button button = new Button("Update");
        button.addClickListener(e ->
                button.getUI().ifPresent(ui -> ui.navigate("updateBook/"+item.getId()))
        );
        return button;
    }
}
