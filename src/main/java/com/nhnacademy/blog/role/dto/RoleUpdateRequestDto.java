package com.nhnacademy.blog.role.dto;

/**
 * TODO#2-2 RoleUpdateRequestDto 구현
 * 역할 정보를 업데이트하기 위한 요청 DTO 클래스입니다.
 * 역할 ID, 역할 이름 및 역할 설명을 포함합니다.
 */
public class RoleUpdateRequestDto {
    // 역할 ID
    private final String roleId;
    // 역할 이름
    private final String roleName;
    // 역할 설명
    private final String roleDescription;

    // RoleUpdateRequestDto 클래스의 생성자
    public RoleUpdateRequestDto(String roleId, String roleName, String roleDescription) {
        // 역할 ID를 초기화합니다.
        this.roleId = null;
        // 역할 이름을 초기화합니다.
        this.roleName = null;
        // 역할 설명을 초기화합니다.
        this.roleDescription = null;
    }
}