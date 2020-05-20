package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.CategoryCommand;
import com.giovannottix.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
class CategoryCommandToCategoryTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
       assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        //given
        CategoryCommand categoryCommand = CategoryCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}