package com.nhnacademy.blog.blogmember.repository.impl;

import com.nhnacademy.blog.bloginfo.domain.Blog;
import com.nhnacademy.blog.bloginfo.repository.BlogRepository;
import com.nhnacademy.blog.bloginfo.repository.impl.JdbcBlogRepository;
import com.nhnacademy.blog.blogmember.domain.BlogMemberMapping;
import com.nhnacademy.blog.blogmember.repository.BlogMemberMappingRepository;
import com.nhnacademy.blog.category.domain.Category;
import com.nhnacademy.blog.category.repository.CategoryRepository;
import com.nhnacademy.blog.category.repository.impl.JdbcCategoryRepository;
import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.member.domain.Member;
import com.nhnacademy.blog.member.repository.MemberRepository;
import com.nhnacademy.blog.member.repository.impl.JdbcMemberRepository;
import com.nhnacademy.blog.role.doamin.Role;
import com.nhnacademy.blog.role.repository.RoleRepository;
import com.nhnacademy.blog.role.repository.impl.JdbcRoleRepository;
import org.junit.jupiter.api.*;

import java.util.Optional;

/**
 * TODO#4-TEST
 */
class JdbcBlogMemberMappingRepositoryTest {

    static BlogRepository blogRepository;
    static BlogMemberMappingRepository blogMemberMappingRepository;
    static CategoryRepository categoryRepository;
    static MemberRepository memberRepository;
    static RoleRepository roleRepository;

    /**
     * 모든 테스트가 실행되기 전에 한 번만 실행됩니다.
     * 테스트를 위한 리포지토리를 초기화합니다.
     */
    @BeforeAll
    static void beforeAll() {
        Context context = ContextHolder.getApplicationContext();
        blogMemberMappingRepository = (BlogMemberMappingRepository) context.getBean(JdbcBlogMemberMappingRepository.BEAN_NAME);
        blogRepository = (BlogRepository) context.getBean(JdbcBlogRepository.BEAN_NAME);
        categoryRepository = (CategoryRepository) context.getBean(JdbcCategoryRepository.BEAN_NAME);
        memberRepository = (MemberRepository) context.getBean(JdbcMemberRepository.BEAN_NAME);
        roleRepository = (RoleRepository) context.getBean(JdbcRoleRepository.BEAN_NAME);
    }

    /**
     * 각 테스트가 실행되기 전에 실행됩니다.
     * 데이터베이스 커넥션을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
    }

    /**
     * 각 테스트가 종료된 후에 실행됩니다.
     * 데이터베이스 커넥션을 재설정합니다.
     */
    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("블로그+회원 : 연결")
    void save() {
        //TODO:블로그와 회원을 연결하는 기능을 테스트합니다.
        //  1. 새로운 회원과 블로그를 생성합니다.
        //  2. 생성된 회원과 블로그를 연결합니다.
        //  3. 연결된 블로그 회원 정보를 조회하여 검증합니다.
        //  4. 조회된 정보가 생성된 회원, 블로그, 권한 정보와 일치하는지 확인합니다.
    }

    @Test
    @DisplayName("블로그+회원 : 연결삭제")
    void deleteByBlogMemberMappingId() {
        // TODO: 블로그와 회원 간의 연결을 삭제하는 기능을 테스트합니다.
        //  1. 새로운 회원과 블로그를 생성합니다.
        //  2. 생성된 회원과 블로그를 연결합니다.
        //  3. 연결된 블로그 회원 정보를 삭제합니다.
        //  4. 삭제된 정보가 데이터베이스에서 더 이상 존재하지 않는지 확인합니다.
    }

    @Test
    @DisplayName("mb_id + blog_id 이용한 조회")
    void findByMbNoAndBlogId() {
        // TODO: 회원 번호와 블로그 ID를 이용하여 블로그 회원 정보를 조회하는 기능을 테스트합니다.
        //  1. 새로운 회원과 블로그를 생성합니다.
        //  2. 생성된 회원과 블로그를 연결합니다.
        //  3. 회원 번호와 블로그 ID로 블로그 회원 정보를 조회합니다.
        //  4. 조회된 정보가 생성된 회원, 블로그, 권한 정보와 일치하는지 확인합니다.
    }
}
