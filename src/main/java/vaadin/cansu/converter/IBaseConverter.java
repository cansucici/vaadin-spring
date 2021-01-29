package vaadin.cansu.converter;

public interface IBaseConverter<E, D> {

    D convertToDto(E book);

    E convertToEntity(D dto);
}
