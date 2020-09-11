package com.smartosc.training.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface BaseService<T extends BaseEntity, DTO extends IdDto, ID extends Serializable> {

    @Autowired
    BaseRepository<T, ID> getDao();

    T createDto(DTO dto);

    void convertDtoToEntity(DTO dto, T entity);

    default T save(DTO dto) {
        T entity = createDto(dto);
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(new Date());
            entity.setDeleted(false);
        }
        return getDao().save(entity);
    }

    default T update(DTO dto) {
        if (dto.getId() == null) {
            throw new EntityNotFoundException();
        }
        T entity = getDao().getOne((ID) dto.getId());
        convertDtoToEntity(dto, entity);
        entity.setCreatedAt(new Date());
        entity.setDeleted(true);
        return getDao().saveAndFlush(entity);
    }

    default void delete(ID id) {
        if (id == null) {
            throw new EntityNotFoundException();
        }
        T entity = getDao().getOne(id);
        if (entity != null) {
            entity.setCreatedAt(new Date());
            entity.setDeleted(true);
            getDao().saveAndFlush(entity);
        }
    }

    default List<T> findAll() {
        return getDao().findAll();
    }

    default T findById(ID id) {
        return getDao().findById(id).orElse(null);
    }

    default Page<T> findAll(Pageable pageable) {
        return getDao().findAll(pageable);
    }

    default Page<T> findAll(Specification<T> specification, Pageable pageable) {
        return getDao().findAll(specification, pageable);
    }

}
