package by.example.cashier.repository.common;

import by.example.cashier.model.dto.AbstractDto;

import java.util.List;
import java.util.Optional;

public interface CommonRepositoryDTO<E extends AbstractDto> {

    Optional<E> save(E entity);

    Optional<E> update(E entity);

    Optional<E> getById(Long id);

    List<E> getAll();

    Boolean deleteById(Long id);

    Boolean deleteAll();
}
