package com.nhnacademy.blog.role.repository;

import com.nhnacademy.blog.role.doamin.Role;
import com.nhnacademy.blog.role.dto.RoleUpdateRequestDto;

import java.util.Optional;

public interface RoleRepository {

    /**
     * 새로운 역할(role)을 등록합니다.
     *
     * @param role 등록할 역할 객체
     */
    void save(Role role);

    /**
     * 기존 역할(role)의 정보를 수정합니다.
     *
     * @param roleUpdateRequestDto 수정할 역할 정보 요청 객체
     */
    void update(RoleUpdateRequestDto roleUpdateRequestDto);

    /**
     * 지정된 역할(role)을 삭제합니다.
     *
     * @param roleId 삭제할 역할의 ID
     */
    void deleteByRoleId(String roleId);

    /**
     * 역할 ID를 이용해 역할 정보를 조회합니다.
     *
     * @param roleId 조회할 역할의 ID
     * @return 조회된 역할 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Role> findByRoleId(String roleId);

    /**
     * 특정 역할(role)이 존재하는지 여부를 확인합니다.
     *
     * @param roleId 확인할 역할의 ID
     * @return 역할이 존재하면 true, 존재하지 않으면 false
     */
    boolean existsByRoleId(String roleId);
}