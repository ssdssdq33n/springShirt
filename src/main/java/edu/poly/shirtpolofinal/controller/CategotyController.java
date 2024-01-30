package edu.poly.shirtpolofinal.controller;

import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.model.CategoryDTO;
import edu.poly.shirtpolofinal.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("categories")
public class CategotyController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoryDTOS=categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int categoryId){
        CategoryDTO categoryDTO=categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO category=categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") int CategoryId, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryUpdate=categoryService.updateCategory(CategoryId,categoryDTO);
        return ResponseEntity.ok(categoryUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("thanh cong");
    }
}
