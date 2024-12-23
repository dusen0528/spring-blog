package com.nhnacademy.blog.category.repository.impl;

import com.nhnacademy.blog.category.domain.Category;
import com.nhnacademy.blog.category.dto.CategoryResponse;
import com.nhnacademy.blog.category.dto.CategoryUpdateRequest;
import com.nhnacademy.blog.category.repository.CategoryRepository;
import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link CategoryRepository}의 구현체로, 카테고리 정보를 데이터베이스와 상호작용하여 처리합니다.
 * <p>
 * 이 클래스는 카테고리의 저장, 수정, 삭제, 조회 기능을 제공하며,
 * 모든 DB 연산은 JDBC를 통해 수행됩니다.
 */
@SuppressWarnings("java:S1192")
// TODO#6-4 @Repository 어노테이션을 추가하여 Bean으로 인식되도록 설정
@Repository
public class JdbcCategoryRepository implements CategoryRepository {
    public static final String BEAN_NAME = "jdbcCategoryRepository";

    /**
     * 카테고리를 데이터베이스에 저장합니다.
     *
     * @param category 저장할 카테고리 객체
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public void save(Category category) {
        // TODO:#6-5  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL INSERT 쿼리문을 작성하여 카테고리를 삽입합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 파라미터 설정합니다.
        // 4. executeUpdate() 메서드를 사용하여 카테고리 데이터를 데이터베이스에 삽입합니다.
        // 5. 자동 생성된 키를 받아와서 category 객체에 categoryId를 설정합니다.
        // 6. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
    }

    /**
     * 카테고리 정보를 수정합니다.
     *
     * @param categoryUpdateRequest 수정할 카테고리 정보
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public void update(CategoryUpdateRequest categoryUpdateRequest) {
        // TODO#6-6  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL UPDATE 쿼리문을 작성하여 카테고리 정보를 수정합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 파라미터 설정합니다.
        // 4. executeUpdate() 메서드를 사용하여 카테고리 정보를 수정합니다.
        // 5. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
    }

    /**
     * 카테고리 ID로 카테고리를 삭제합니다.
     *
     * @param categoryId 삭제할 카테고리 ID
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public void deleteByCategoryId(Long categoryId) {
        // TODO#6-7  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL DELETE 쿼리문을 작성하여 카테고리를 삭제합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 카테고리 ID 파라미터를 설정합니다.
        // 4. executeUpdate() 메서드를 사용하여 카테고리를 삭제합니다.
        // 5. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
    }

    /**
     * 카테고리 ID로 카테고리를 조회합니다.
     *
     * @param categoryId 조회할 카테고리 ID
     * @return 카테고리가 존재하면 Optional로 감싸 반환, 없으면 빈 Optional 반환
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public Optional<Category> findByCategoryId(Long categoryId) {
        // TODO#6-8  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL SELECT 쿼리문을 작성하여 카테고리 정보를 조회합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 카테고리 ID 파라미터를 설정합니다.
        // 4. executeQuery() 메서드를 사용하여 쿼리 실행 후 결과를 ResultSet에서 가져옵니다.
        // 5. 결과를 Category 객체로 변환하여 반환합니다.
        // 6. 조회된 값이 없으면 Optional.empty()를 반환합니다.
        // 7. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
        return Optional.empty();
    }

    /**
     * 블로그 ID와 카테고리 PID로 카테고리 목록을 조회합니다.
     *
     * @param blogId 블로그 ID
     * @param categoryPid 상위 카테고리 ID (없으면 null)
     * @return 카테고리 목록
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public List<Category> findAll(Long blogId, Long categoryPid) {
        // TODO#6-9  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. 기본 SQL SELECT 쿼리문을 작성하여 카테고리 목록을 조회합니다.
        // 3. categoryPid가 null이 아니면 WHERE 조건을 추가하여 상위 카테고리를 기준으로 조회합니다.
        // 4. PreparedStatement를 생성하고 SQL 쿼리문에 파라미터를 설정합니다.
        // 5. executeQuery() 메서드를 사용하여 쿼리 실행 후 결과를 ResultSet에서 가져옵니다.
        // 6. 결과를 Category 객체로 변환하여 List에 추가합니다.
        // 7. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
        return new ArrayList<>();
    }

    /**
     * 블로그 ID로 카테고리 목록을 조회하여 {@link CategoryResponse} 객체로 반환합니다.
     *
     * @param blogId 블로그 ID
     * @return {@link CategoryResponse} 객체 리스트
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public List<CategoryResponse> findAllByBlogId(Long blogId) {
        // TODO#6-10 1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL SELECT 쿼리문을 작성하여 블로그 ID에 해당하는 카테고리 목록을 조회합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 블로그 ID 파라미터를 설정합니다.
        // 4. executeQuery() 메서드를 사용하여 쿼리 실행 후 결과를 ResultSet에서 가져옵니다.
        // 5. 결과를 CategoryResponse 객체로 변환하여 List에 추가합니다.
        // 6. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
        return new ArrayList<>();
    }

    /**
     * 카테고리 ID로 카테고리가 존재하는지 확인합니다.
     *
     * @param categoryId 확인할 카테고리 ID
     * @return 카테고리가 존재하면 true, 그렇지 않으면 false
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public boolean existsByCategoryId(Long categoryId) {
        // TODO#6-11  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL SELECT 쿼리문을 작성하여 카테고리 ID가 존재하는지 확인합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 카테고리 ID 파라미터를 설정합니다.
        // 4. executeQuery() 메서드를 사용하여 결과를 확인하고, 카테고리가 존재하면 true를 반환합니다.
        // 5. 결과가 없으면 false를 반환합니다.
        // 6. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
        return false;
    }

    /**
     * 카테고리 ID로 하위 카테고리가 존재하는지 확인합니다.
     *
     * @param categoryId 확인할 카테고리 ID
     * @return 하위 카테고리가 존재하면 true, 그렇지 않으면 false
     * @throws DatabaseException 데이터베이스 연산 중 발생한 예외
     */
    @Override
    public boolean existsSubCategoryByCategoryId(Long categoryId) {
        // TODO#6-12  1. DbConnectionThreadLocal을 통해 현재 연결된 DB 커넥션을 가져옵니다.
        // 2. SQL SELECT 쿼리문을 작성하여 하위 카테고리가 존재하는지 확인합니다.
        // 3. PreparedStatement를 생성하고 SQL 쿼리문에 카테고리 ID 파라미터를 설정합니다.
        // 4. executeQuery() 메서드를 사용하여 결과를 확인하고, 하위 카테고리가 존재하면 true를 반환합니다.
        // 5. 결과가 없으면 false를 반환합니다.
        // 6. 예외 발생 시 DatabaseException을 처리하여 적절한 오류 메시지를 반환합니다.
        return false;
    }
}
