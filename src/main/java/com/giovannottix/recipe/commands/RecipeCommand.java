package com.giovannottix.recipe.commands;

import com.giovannottix.recipe.domain.Difficulty;
import com.giovannottix.recipe.domain.Note;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private NoteCommand note;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();

}
