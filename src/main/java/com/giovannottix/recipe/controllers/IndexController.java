package com.giovannottix.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/07/20, Thu
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        System.out.println("Make a change 123");
        return "index";
    }

}
