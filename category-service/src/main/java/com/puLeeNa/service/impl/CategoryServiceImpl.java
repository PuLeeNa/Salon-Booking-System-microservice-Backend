package com.puLeeNa.service.impl;

import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.modal.Category;
import com.puLeeNa.repository.CategoryRepository;
import com.puLeeNa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDTO.getId());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new Exception("category not found");
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id, Long salonId) throws Exception {
        Category category = getCategoryById(id);
        if(!category.getSalonId().equals(salonId)){
            throw new Exception("you are not authorized to delete this category");
        }
        categoryRepository.deleteById(id);
    }
}
