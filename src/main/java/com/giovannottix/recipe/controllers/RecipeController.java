package com.giovannottix.recipe.controllers;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/18/20, Mon
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/show/{id}")
    public String getRecipeDetail(@PathVariable String id, Model model) {
        RecipeCommand recipe = recipeService.getRecipesById(Long.parseLong(id));

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {

        RecipeCommand savedRecipe =
                recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedRecipe.getId();
    }
}