package vaadin.cansu.model.entities;

import lombok.Data;
import vaadin.cansu.model.enums.LanguagesEnum;
import vaadin.cansu.model.enums.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String bookName;

    @Column(length = 5)
    private Long pageNumber;

    @Column(length = 100)
    private String publisherName;

    @Column(length = 50)
    private Integer editionNumber;

    @Column(unique = true, length = 50)
    private Long isbn;

    // TODO : dilleri Enum sınıfı olarak ekledim.
    @Column(length = 15)
    private LanguagesEnum languagesEnum = LanguagesEnum.TURKISH;

    @Column(length = 50)
    private String category;

    @Column(length = 7)
    private StatusEnum status = StatusEnum.ACTIVE;


}
