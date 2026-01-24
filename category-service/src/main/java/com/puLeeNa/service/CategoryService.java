package com.puLeeNa.service;

import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.modal.Category;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id, Long salonId) throws Exception;
}
