package com.giovannottix.recipe.services;

import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/16/20, Sat
 */
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    Set<Recipe> recipeData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);

        Recipe recipe = new Recipe();
        recipeData = new HashSet<>();
        recipeData.add(recipe);
    }

    @Test
    void getRecipesTest() throws Exception {
        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdFoundTest() throws Exception {
        Long id = 1L;
        Recipe recipeFix = Recipe.builder().id(id).build();

        when(recipeRepository.findById(anyLong()))
                .thenReturn(Optional.of(recipeFix));

        Recipe recipe = recipeService.getRecipesById(id);

        assertNotNull(recipe);
        assertEquals(id, recipe.getId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getRecipeByIdNotFoundTest() throws Exception {
        Long id = 1L;

        when(recipeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Recipe recipe = recipeService.getRecipesById(id);

        assertNull(recipe);

        verify(recipeRepository, times(1)).findById(anyLong());
    }
}