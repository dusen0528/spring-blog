package com.nhnacademy.blog.role.repository.impl;

import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.role.doamin.Role;
import com.nhnacademy.blog.role.dto.RoleUpdateRequestDto;
import com.nhnacademy.blog.role.repository.RoleRepository;

import java.sql.*;
import java.util.Optional;

//TODO#2- @Repository 작성하세요.
@Repository(JdbcRoleRepository.BEAN_NAME)
public class JdbcRoleRepository implements RoleRepository {
    public static final String BEAN_NAME = "changeMe";

    @Override
    public void save(Role role) {
        // TODO#2-1: 역할 정보를 데이터베이스에 저장하기 위한 SQL 쿼리 작성
    }

    @Override
    public void update(RoleUpdateRequestDto roleUpdateRequestDto) {
        // TODO#2-2: roleUpdateRequestDto 객체를 사용하여 업데이트할 SQL 쿼리와 PreparedStatement 작성
        // 필요한 경우, roleUpdateRequestDto의 데이터를 PreparedStatement에 설정하세요.
    }

    @Override
    public void deleteByRoleId(String roleId) {
        // TODO#2-3: roleId를 사용하여 역할을 삭제하는 SQL 쿼리 작성
        // 필요한 경우, roleId를 PreparedStatement에 설정하세요.
    }

    @Override
    public Optional<Role> findByRoleId(String roleId) {
        // TODO#2-4: roleId를 사용하여 역할을 조회하는 SQL 쿼리 작성
        // SQL 결과를 Role 객체로 변환하고 Optional로 반환하세요.
        return Optional.empty(); // 빈 Optional을 반환하는 기본 구현
    }

    @Override
    public boolean existsByRoleId(String roleId) {
        // TODO#2-5: roleId로 역할이 존재하는지 확인하는 SQL 쿼리 작성
        // SQL 결과에 따라 true 또는 false를 반환하세요.
        return false; // 기본 구현으로 false 반환
    }
}