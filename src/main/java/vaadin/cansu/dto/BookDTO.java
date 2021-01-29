package vaadin.cansu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vaadin.cansu.model.enums.LanguagesEnum;
import vaadin.cansu.model.enums.StatusEnum;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends BaseDTO {

    @NotEmpty(message = "Kitap ismi boş bırakılamaz.")
    private String bookName;

    private Long pageNumber;

    @NotEmpty(message = "Yayınevi bilgisi boş bırakılamaz.")
    private String publisherName;

    private Integer editionNumber;

    private Long isbn;

    private LanguagesEnum languagesEnum;

    @NotEmpty(message = "Kitap türü boş bırakılamaz.")
    private String category;

    private StatusEnum status;

    private List<String> authors = new ArrayList<>();


}
