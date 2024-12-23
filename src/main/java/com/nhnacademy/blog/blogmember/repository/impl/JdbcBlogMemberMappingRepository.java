package com.nhnacademy.blog.blogmember.repository.impl;

import com.nhnacademy.blog.blogmember.domain.BlogMemberMapping;
import com.nhnacademy.blog.blogmember.repository.BlogMemberMappingRepository;
import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;

import java.sql.*;
import java.util.Optional;

//TODO#4-3 @Repository 애너테이션을 추가하여 빈으로 등록합니다.

public class JdbcBlogMemberMappingRepository implements BlogMemberMappingRepository {
    public static final String BEAN_NAME = "changeMe";

    @Override
    public void save(BlogMemberMapping blogMemberMapping) {
        // TODO#4-4 블로그 회원 정보를 데이터베이스에 저장하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. INSERT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하고 생성된 키를 받아옵니다.
        //  5. 블로그 회원 ID를 blogMemberMapping 객체에 설정합니다.
    }

    @Override
    public void deleteByBlogMemberMappingId(Long blogMemberId) {
        // TODO#4-5 블로그 회원 정보를 데이터베이스에서 삭제하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. DELETE SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 블로그 회원 정보를 삭제합니다.
    }

    @Override
    public Optional<BlogMemberMapping> findByBlogMemberId(Long blogMemberId) {
        // TODO#4-6 블로그 회원 ID로 블로그 회원 정보를 조회하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. SELECT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 결과를 ResultSet으로 받습니다.
        //  5. ResultSet에서 데이터를 추출하여 BlogMemberMapping 객체를 생성합니다.
        //  6. 조회된 블로그 회원 정보를 Optional로 반환합니다.
        return Optional.empty();
    }

    @Override
    public Optional<BlogMemberMapping> findByMbNoAndBlogId(Long mbNo, Long blogId) {
        // TODO#4-7 회원 번호와 블로그 ID로 블로그 회원 정보를 조회하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. SELECT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 결과를 ResultSet으로 받습니다.
        //  5. ResultSet에서 데이터를 추출하여 BlogMemberMapping 객체를 생성합니다.
        //  6. 조회된 블로그 회원 정보를 Optional로 반환합니다.
        return Optional.empty();
    }
}
