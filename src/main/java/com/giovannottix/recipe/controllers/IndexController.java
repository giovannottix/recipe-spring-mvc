package com.giovannottix.recipe.controllers;

import com.giovannottix.recipe.domain.Category;
import com.giovannottix.recipe.domain.UnitOfMeasure;
import com.giovannottix.recipe.repositories.CategoryRepository;
import com.giovannottix.recipe.repositories.UniOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/07/20, Thu
 */
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UniOfMeasureRepository uniOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository,
                           UniOfMeasureRepository uniOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.uniOfMeasureRepository = uniOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        Optional<Category> category =
                this.categoryRepository.findByDescription("American");

        Optional<UnitOfMeasure> unitOfMeasure =
                this.uniOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is " + category.get().getId());
        System.out.println("Unit id is " + unitOfMeasure.get().getId());

        return "index";
    }

}
