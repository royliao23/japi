package com.app.controller;

import com.app.model.Category;
import com.app.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/high/categ/")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // POST /high/categ
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        Category saved = categoryRepository.save(category);
        Map<String, Object> response = new HashMap<>();
        response.put("code", saved.getCode());
        response.put("name", saved.getName());
        return ResponseEntity.status(201).body(response);
    }

    // GET /high/categ
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // GET /high/categ/{id}
    @GetMapping("{id}/")
    public ResponseEntity<?> getCategory(@PathVariable Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("detail", "Category not found"));
        }
        return ResponseEntity.ok(Map.of("category", category.get()));
    }

    // PUT /high/categ/{id}
    @PutMapping("{id}/")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(category.getName());
                    categoryRepository.save(existing);
                    return ResponseEntity.ok(Map.of("message", "Category updated successfully"));
                })
                .orElse(ResponseEntity.status(404).body(Map.of("detail", "Category not found")));
    }

    // DELETE /high/categ/{id}
    @DeleteMapping("{id}/")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok(Map.of("message", "Category deleted successfully"));
                })
                .orElse(ResponseEntity.status(404).body(Map.of("detail", "Category not found")));
    }
}

