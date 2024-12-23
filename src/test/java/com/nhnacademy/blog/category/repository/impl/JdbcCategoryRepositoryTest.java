package com.nhnacademy.blog.category.repository.impl;

import com.nhnacademy.blog.bloginfo.repository.BlogRepository;
import com.nhnacademy.blog.bloginfo.repository.impl.JdbcBlogRepository;
import com.nhnacademy.blog.category.repository.CategoryRepository;
import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

//TODO#6-TEST
@Slf4j
class JdbcCategoryRepositoryTest {
    static CategoryRepository categoryRepository;
    static BlogRepository blogRepository;

    @BeforeAll
    static void beforeAll() {
        Context context = ContextHolder.getApplicationContext();
        blogRepository = (BlogRepository) context.getBean(JdbcBlogRepository.BEAN_NAME);
        categoryRepository = (CategoryRepository) context.getBean(JdbcCategoryRepository.BEAN_NAME);
    }

    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
    }

    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("카테고리 등록")
    void save() {
        // TODO: 카테고리가 정상적으로 등록되는지 확인
        // 1. 새로운 카테고리가 저장되고, 그 카테고리의 ID가 존재하는지 확인
        // 2. 등록된 카테고리의 이름, 순서, 생성일 등이 제대로 저장되었는지 확인
    }

    @Test
    @DisplayName("서브카테고리 등록")
    void save_subCategory() {
        // TODO: 서브 카테고리가 정상적으로 등록되는지 확인
        // 1. 서브 카테고리가 정상적으로 저장되고, 부모 카테고리와 연결되는지 확인
        // 2. 서브 카테고리의 이름, 순서, 생성일 등이 제대로 저장되었는지 확인
    }

    @Test
    @DisplayName("category-업데이트")
    void update() {
        // TODO: 카테고리가 정상적으로 업데이트되는지 확인
        // 1. 카테고리 이름 및 순서 등의 업데이트가 반영되는지 확인
        // 2. 업데이트 후 카테고리의 수정 시간이 올바르게 갱신되는지 확인
    }

    @Test
    @DisplayName("카테고리-삭제")
    void delete() {
        // TODO: 카테고리가 삭제되었을 때, 블로그가 존재하지 않는지 확인
        // 1. 삭제된 카테고리가 블로그와 연결된 상태에서 올바르게 삭제되는지 확인
        // 2. 삭제 후 블로그의 존재 여부를 체크하여, 블로그가 정상적으로 삭제되었는지 확인
    }

    @Test
    @DisplayName("category 조회")
    void findByCategoryId() {
        // TODO: 특정 카테고리를 조회했을 때, 해당 카테고리가 정상적으로 반환되는지 확인
        // 1. 카테고리 ID로 조회하여, 카테고리의 이름, 순서, 생성일 등이 정확히 일치하는지 확인
    }

    @Test
    @DisplayName("category 전체조회")
    void findAll() {
        // TODO: 블로그에 속한 모든 카테고리를 조회했을 때, 모든 카테고리가 정상적으로 반환되는지 확인
        // 1. 블로그에 속한 모든 카테고리가 반환되며, 반환된 카테고리의 순서 및 ID가 정확히 일치하는지 확인
    }

    @Test
    @DisplayName("category 서브 카테고리 - 조회")
    void findAll_subCategory() {
        // TODO: 특정 카테고리의 서브 카테고리를 조회했을 때, 서브 카테고리들이 정상적으로 반환되는지 확인
        // 1. 서브 카테고리가 정상적으로 반환되며, 반환된 서브 카테고리의 ID와 이름이 정확히 일치하는지 확인
    }

    @Test
    @DisplayName("categoryId 존재여부 체크")
    void existsByCategoryId() {
        // TODO: 카테고리 ID가 존재하는지 확인
        // 1. 카테고리 ID로 조회했을 때, 해당 카테고리가 존재하는지 체크
    }

    @Test
    @DisplayName("서브카테고리 존재유무 : true")
    void existsSubCategoryByCategoryId() {
        // TODO: 특정 카테고리에 서브 카테고리가 존재하는지 확인
        // 1. 특정 카테고리에 서브 카테고리가 존재하는지 체크
    }

    @Test
    @DisplayName("서브카테고리 존재유무 : false")
    void notExistsSubCategoryByCategoryId() {
        // TODO: 특정 카테고리에 서브 카테고리가 존재하지 않는지 확인
        // 1. 특정 카테고리에 서브 카테고리가 존재하지 않는지 체크
    }
}
