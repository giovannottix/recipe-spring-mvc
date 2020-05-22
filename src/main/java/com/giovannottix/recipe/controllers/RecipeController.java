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

    @GetMapping("recipe/{id}/show")
    public String getRecipeDetail(@PathVariable String id, Model model) {
        RecipeCommand recipe = recipeService.getRecipesById(Long.parseLong(id));

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {

        RecipeCommand savedRecipe =
                recipeService.saveRecipeCommand(recipeCommand);

        return String.format("redirect:/recipe/%s/show", savedRecipe.getId());
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe",
                recipeService.getRecipesById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipeById(Long.valueOf(id));

        return "redirect:/";
    }
}
