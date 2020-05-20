package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.IngredientCommand;
import com.giovannottix.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null) {
            return null;
        }

        return Ingredient.builder().id(source.getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .unitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()))
                .build();
    }
}
