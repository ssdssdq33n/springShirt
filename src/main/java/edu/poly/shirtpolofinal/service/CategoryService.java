package edu.poly.shirtpolofinal.service;

import edu.poly.shirtpolofinal.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(int categoryId);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(int categoryId,CategoryDTO updatedCategory);
    void deleteCategory(int categoryId);
}
