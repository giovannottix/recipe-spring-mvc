package com.giovannottix.recipe.services;

import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Get recipes method in service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipesById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
