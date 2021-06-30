package com.khangse616.serverfashionrs.Utils;

import com.khangse616.serverfashionrs.models.Category;

import java.util.List;
import java.util.Set;

public class CategoryUtil {
    public static void getListIdCategory(Category category, List<Integer> listId) {
        Set<Category> listCAT = category.getCategories();

        if (listCAT.size() > 0) {
            listCAT.forEach(c->{
                getListIdCategory(c, listId);
            });
        } else {
            listId.add(category.getId());
        }
    }

    public static void getListSubCategory(Category category, List<Category> listId) {
        Set<Category> listCAT = category.getCategories();

        if (listCAT.size() > 0) {
            listCAT.forEach(c->{
                getListSubCategory(c, listId);
            });
        } else {
            listId.add(category);
        }
    }
}
