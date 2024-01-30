package edu.poly.shirtpolofinal.mapper;

import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.model.CategoryDTO;

import java.util.HashSet;
import java.util.Set;

public class CategoryMapper {
    public static CategoryDTO toMapperCategoryDTO(Category category){
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName()
        );
    }

    public static Category toMapperCategory(CategoryDTO categoryDTO){
        Set<Product> product=new HashSet<>();
        return new Category(
                categoryDTO.getCategoryId(),
                categoryDTO.getName(),
                product
        );
    }
}
