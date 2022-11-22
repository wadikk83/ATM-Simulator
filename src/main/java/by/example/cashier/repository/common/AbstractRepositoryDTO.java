package by.example.cashier.repository.common;

import by.example.cashier.model.dto.AbstractDto;
import by.example.cashier.parser.Parser;
import by.example.cashier.parser.ParserFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AbstractRepositoryDTO<E extends AbstractDto> implements CommonRepositoryDTO<E> {

    private Parser<E> parser;

    private String entityFilename;

    public AbstractRepositoryDTO(String entityFilename) {
        this.entityFilename = entityFilename;
        parser = new ParserFactory().getParser();
    }

    @Override
    public Optional<E> save(E entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        }
        List<E> entityList = getAll();

        if (!getById(entity.getId()).isPresent()) entityList.add(entity);
        else {
            entityList.remove(getById(entity.getId()).get());
            entityList.add(entity);
        }
        parser.write(entityFilename, entityList);
        return Optional.of(entity);
    }

    @Override
    public Optional<E> update(E entity) {
        return save(entity);
    }

    @Override
    public Optional<E> getById(Long id) {
        return getAll().stream().filter(s -> s.getId().equals(id)).findAny();
    }

    @Override
    public List<E> getAll() {
        return parser.read(entityFilename);
    }

    @Override
    public Boolean deleteById(Long id) {
        List<E> entityList = getAll();
        Optional<E> toBeDeleted = getById(id);
        if (toBeDeleted != null) {
            entityList.remove(toBeDeleted);
            parser.write(entityFilename, entityList);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAll() {
        List<E> entityList = getAll();
        entityList.clear();
        parser.write(entityFilename, entityList);
        return true;
    }
}
