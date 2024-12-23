package com.nhnacademy.blog.role.doamin;

/**
 * TODO#2-1 Role class 구현
 * 역할을 나타내는 Role 클래스입니다.
 * 역할 ID, 역할 이름 및 역할 설명을 포함합니다.
 */
public class Role {
    // 역할 ID
    private final String roleId;
    // 역할 이름
    private final String roleName;
    // 역할 설명
    private final String roleDescription;

    // Role 클래스의 생성자
    public Role(String roleId, String roleName, String roleDescription) {
        // 역할 ID를 초기화합니다.
        this.roleId = null;
        // 역할 이름을 초기화합니다.
        this.roleName = null;
        // 역할 설명을 초기화합니다.
        this.roleDescription = null;
    }
}
