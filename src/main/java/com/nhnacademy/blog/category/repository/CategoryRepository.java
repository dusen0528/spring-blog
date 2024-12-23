package com.nhnacademy.blog.category.repository;

import com.nhnacademy.blog.category.domain.Category;
import com.nhnacademy.blog.category.dto.CategoryResponse;
import com.nhnacademy.blog.category.dto.CategoryUpdateRequest;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    /**
     * 새로운 카테고리를 저장합니다.
     *
     * @param category 저장할 카테고리 객체
     */
    void save(Category category);

    /**
     * 기존 카테고리를 수정합니다.
     *
     * @param categoryUpdateRequest 수정할 카테고리 정보 요청 객체
     */
    void update(CategoryUpdateRequest categoryUpdateRequest);

    /**
     * 카테고리를 삭제합니다.
     *
     * @param categoryId 삭제할 카테고리의 ID
     */
    void deleteByCategoryId(Long categoryId);

    /**
     * 카테고리 ID를 기준으로 카테고리를 조회합니다.
     *
     * @param categoryId 조회할 카테고리의 ID
     * @return 조회된 카테고리 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Category> findByCategoryId(Long categoryId);

    /**
     * 주어진 블로그 ID와 부모 카테고리 ID를 기준으로 카테고리 리스트를 조회합니다.
     *
     * @param blogId 조회할 블로그의 ID
     * @param categoryPid 조회할 부모 카테고리 ID
     * @return 카테고리 리스트
     */
    List<Category> findAll(Long blogId, Long categoryPid);

    /**
     * 해당 블로그의 모든 카테고리 리스트를 조회합니다.
     *
     * @param blogId 조회할 블로그의 ID
     * @return 블로그에 속한 모든 카테고리 리스트
     */
    List<CategoryResponse> findAllByBlogId(Long blogId);

    /**
     * 카테고리가 존재하는지 여부를 확인합니다.
     *
     * @param categoryId 확인할 카테고리의 ID
     * @return 카테고리가 존재하면 true, 존재하지 않으면 false
     */
    boolean existsByCategoryId(Long categoryId);

    /**
     * 해당 카테고리의 서브카테고리가 존재하는지 여부를 확인합니다.
     *
     * @param categoryId 확인할 카테고리의 ID
     * @return 서브카테고리가 존재하면 true, 존재하지 않으면 false
     */
    boolean existsSubCategoryByCategoryId(Long categoryId);
}

