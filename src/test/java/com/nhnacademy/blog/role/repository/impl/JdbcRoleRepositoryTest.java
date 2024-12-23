package com.nhnacademy.blog.role.repository.impl;

import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.role.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO#2-TEST
 */
@Slf4j
class JdbcRoleRepositoryTest {
    static RoleRepository roleRepository;

    @BeforeAll
    static void beforeAll() {
        // Application Context에서 RoleRepository 빈을 가져옵니다.
        Context context = ContextHolder.getApplicationContext();
        roleRepository = (RoleRepository) context.getBean(JdbcRoleRepository.BEAN_NAME);
    }

    @BeforeEach
    void setUp() {
        // 각 테스트 케이스 실행 전에 DB 커넥션을 초기화합니다.
        DbConnectionThreadLocal.initialize();
    }

    @AfterEach
    void tearDown() {
        // 각 테스트 케이스 실행 후에 DB 커넥션을 리셋합니다.
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("권한등록")
    void save() {
        //TODO: 새로운 Role 객체를 생성하고 이를 저장하는 테스트 코드를 작성합니다.
        // 저장된 Role 객체의 roleId가 null이 아닌지 확인합니다.
    }

    @Test
    @DisplayName("권한수정")
    void update() {
        //TODO: 기존의 Role 객체를 생성하고 이를 저장한 후,
        // RoleUpdateRequestDto를 사용하여 Role 객체의 정보를 수정하는 테스트 코드를 작성합니다.
        // 수정된 Role 객체가 기대한 값과 일치하는지 확인합니다.
    }

    @Test
    @DisplayName("권한삭제")
    void delete() {
        //TODO: 기존의 Role 객체를 생성하고 이를 저장한 후,
        // Role 객체를 삭제하는 테스트 코드를 작성합니다.
        // 삭제된 Role 객체가 더 이상 존재하지 않는지 확인합니다.
    }

    @Test
    @DisplayName("권한조회")
    void findByRoleId() {
        //TODO: 기존의 Role 객체를 생성하고 이를 저장한 후,
        // Role 객체를 roleId로 조회하는 테스트 코드를 작성합니다.
        // 조회된 Role 객체가 기대한 값과 일치하는지 확인합니다.
    }

    @Test
    @DisplayName("권한존재여부 : true")
    void existsByRoleId() {
        //TODO: 기존의 Role 객체를 생성하고 이를 저장한 후,
        // Role 객체가 존재하는지 확인하는 테스트 코드를 작성합니다.
    }

    @Test
    @DisplayName("권한존재여부 : false")
    void notExistsByRoleId() {
        //TODO: 존재하지 않는 Role ID를 사용하여 Role 객체가 존재하지 않는지 확인하는 테스트 코드를 작성합니다.
    }
}
