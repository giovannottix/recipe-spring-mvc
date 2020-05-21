package com.giovannottix.recipe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.services.RecipeService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/18/20, Mon
 */
public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);

    }

    @Test
    public void getRecipeDetailTest() throws Exception {
        RecipeCommand recipe = RecipeCommand.builder().id(1L).build();

        when(recipeService.getRecipesById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attribute("recipe", equalTo(recipe)));
    }

    @Test
    public void newRecipeTest() throws Exception {
        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe",
                        any(RecipeCommand.class)));
    }

    @Test
    public void saveOrUpdateRecipeTest() throws Exception {
        RecipeCommand recipeSent =
                RecipeCommand.builder().description("Description").build();
        RecipeCommand recipeSaved =
                RecipeCommand.builder().id(1L).description("Description").build();

        when(recipeService.saveRecipeCommand(ArgumentMatchers.any())).thenReturn(recipeSaved);

        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "Description")
                .param("id", ""))
                .andExpect(redirectedUrl("/recipe/1/show"));
    }

    @Test
    public void updateRecipeTest() throws Exception {
        RecipeCommand recipe = RecipeCommand.builder().id(1L).build();

        when(recipeService.getRecipesById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe", equalTo(recipe)));
    }
}
