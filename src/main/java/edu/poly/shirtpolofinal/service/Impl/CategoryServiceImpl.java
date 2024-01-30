package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.exception.ResoureNotFoundException;
import edu.poly.shirtpolofinal.mapper.CategoryMapper;
import edu.poly.shirtpolofinal.model.CategoryDTO;
import edu.poly.shirtpolofinal.repository.CategoryRepository;
import edu.poly.shirtpolofinal.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category= CategoryMapper.toMapperCategory(categoryDTO);
        Category categorySave=categoryRepository.save(category);
        return CategoryMapper.toMapperCategoryDTO(categorySave);
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResoureNotFoundException("category is not exisst with given id"));
        return CategoryMapper.toMapperCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category -> CategoryMapper.toMapperCategoryDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(int categoryId, CategoryDTO updatedCategory) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResoureNotFoundException("category is not exisst with given id"));
        category.setName(updatedCategory.getName());
        Category categoryUpdate=categoryRepository.save(category);
        return CategoryMapper.toMapperCategoryDTO(categoryUpdate);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResoureNotFoundException("category is not exisst with given id"));
        categoryRepository.delete(category);
    }
}
