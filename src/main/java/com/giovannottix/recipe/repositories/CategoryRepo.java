package com.giovannottix.recipe.repositories;

import com.giovannottix.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
public interface CategoryRepo extends CrudRepository<Category, Long> {
}
