package com.giovannottix.recipe.controllers;

import com.giovannottix.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/07/20, Thu
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
