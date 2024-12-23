package com.nhnacademy.blog.bloginfo.repository.impl;

import com.nhnacademy.blog.bloginfo.domain.Blog;
import com.nhnacademy.blog.bloginfo.dto.BlogResponse;
import com.nhnacademy.blog.bloginfo.dto.BlogUpdateRequest;
import com.nhnacademy.blog.bloginfo.repository.BlogRepository;
import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * JDBC 구현체 BlogRepository 인터페이스
 * 해당 클래스는 DB와의 연결을 통해 Blog 데이터를 처리하는 기능을 제공합니다.
 *
 * @see BlogRepository
 */
//TODO#3-5 @Repository 작성하세요.
@Repository(JdbcBlogRepository.BEAN_NAME)
public class JdbcBlogRepository implements BlogRepository {
    public static final String BEAN_NAME = "jdbcBlogRepository";

    @Override
    public void save(Blog blog) {
        // TODO#3-6: 이 메소드에서 DB에 새로운 블로그를 저장하는 코드를 작성하세요.
        // - Connection 객체를 얻고, INSERT SQL 쿼리를 실행하여 새로운 블로그를 DB에 저장합니다.
        // - SQL에 필요한 파라미터들은 Blog 객체에서 가져옵니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
    }

    @Override
    public void update(BlogUpdateRequest blogUpdateRequest) {
        // TODO#3-7: 이 메소드에서 블로그의 정보를 수정하는 SQL 쿼리를 작성하고 실행하세요.
        // - Connection 객체를 얻고, UPDATE SQL 쿼리를 사용하여 블로그의 정보를 업데이트합니다.
        // - BlogUpdateRequest 객체에서 필요한 정보를 가져옵니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
    }

    @Override
    public void deleteByBlogId(long blogId) {
        // TODO#3-8: 이 메소드에서 블로그를 삭제하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, DELETE SQL 쿼리를 사용하여 주어진 blogId에 해당하는 블로그를 삭제합니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
    }

    @Override
    public Optional<Blog> findByBlogId(long blogId) {
        // TODO#3-9: 이 메소드에서 블로그 정보를 DB에서 조회하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT SQL 쿼리를 사용하여 주어진 blogId에 해당하는 블로그 정보를 조회합니다.
        // - 조회된 결과를 Blog 객체로 변환하여 Optional로 반환합니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
        return Optional.empty();
    }

    @Override
    public boolean existByBlogId(long blogId) {
        // TODO#3-10: 이 메소드에서 주어진 blogId에 해당하는 블로그가 DB에 존재하는지 확인하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT SQL 쿼리를 사용하여 주어진 blogId가 존재하는지 확인합니다.
        // - 결과가 존재하면 true를 반환하고, 없으면 false를 반환합니다.
        return false;
    }

    @Override
    public boolean existByBlogFid(String blogFid) {
        // TODO#3-11: 이 메소드에서 주어진 blogFid가 DB에 존재하는지 확인하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT SQL 쿼리를 사용하여 주어진 blogFid가 존재하는지 확인합니다.
        // - 결과가 존재하면 true를 반환하고, 없으면 false를 반환합니다.
        return false;
    }

    @Override
    public boolean existMainBlogByMbNo(long mbNo) {
        // TODO#3-12: 이 메소드에서 주어진 mbNo에 대해 메인 블로그가 존재하는지 확인하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT SQL 쿼리를 사용하여 주어진 mbNo에 대해 메인 블로그가 존재하는지 확인합니다.
        // - 결과가 존재하면 true를 반환하고, 없으면 false를 반환합니다.
        return false;
    }

    @Override
    public void updateByBlogIsPublic(long blogId, boolean blogIsPublic) {
        // TODO#3-13: 이 메소드에서 블로그의 공개 여부를 업데이트하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, UPDATE SQL 쿼리를 사용하여 주어진 blogId에 대해 공개 여부를 업데이트합니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
    }

    @Override
    public void updateBlogMain(long blogId, boolean isBlogMain) {
        // TODO#3-14: 이 메소드에서 블로그의 메인 여부를 업데이트하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, UPDATE SQL 쿼리를 사용하여 주어진 blogId에 대해 메인 블로그 여부를 업데이트합니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.
    }

    @Override
    public long countByMbNo(long mbNo, String roleId) {
        // TODO#3-15: 이 메소드에서 주어진 mbNo와 roleId에 해당하는 블로그의 수를 계산하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT COUNT(*) SQL 쿼리를 사용하여 주어진 mbNo와 roleId에 해당하는 블로그 수를 계산합니다.
        // - PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행하고 결과를 반환합니다.
        return 0;
    }

    @Override
    public List<BlogResponse> findAllBlogs(long mbNo, String roleId) {
        // TODO#3-16: 이 메소드에서 모든 블로그를 조회하는 SQL 쿼리와 실행 코드를 작성하세요.
        // - Connection 객체를 얻고, SELECT SQL 쿼리를 사용하여 주어진 mbNo와 roleId에 해당하는 모든 블로그를 조회합니다.
        // - 조회된 블로그들을 BlogResponse 객체로 변환하여 List로 반환합니다.
        return new ArrayList<>();
    }
}
