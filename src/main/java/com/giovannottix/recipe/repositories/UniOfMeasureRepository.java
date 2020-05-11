package com.giovannottix.recipe.repositories;

import com.giovannottix.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
public interface UniOfMeasureRepository
        extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
