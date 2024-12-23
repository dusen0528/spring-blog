package com.nhnacademy.blog.bloginfo.repository.impl;

import com.nhnacademy.blog.bloginfo.domain.Blog;
import com.nhnacademy.blog.bloginfo.dto.BlogUpdateRequest;
import com.nhnacademy.blog.bloginfo.repository.BlogRepository;
import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Optional;

/**
 * TODO#3-TEST 구현
 */
@Slf4j
class JdbcBlogRepositoryTest {

    static BlogRepository blogRepository;

    @BeforeAll
    static void beforeAll() {
        Context context = ContextHolder.getApplicationContext();
        blogRepository = (BlogRepository) context.getBean(JdbcBlogRepository.BEAN_NAME);
    }

    @BeforeEach
    void setUp(){
        DbConnectionThreadLocal.initialize();
    }

    @AfterEach
    void tearDown(){
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    /**
     * 블로그 정보를 저장(생성)하는 테스트.
     * 이 테스트는 새 블로그를 생성하고, 저장한 후에 DB에서 해당 블로그를 조회하여
     * 저장이 성공적으로 되었는지 확인합니다.
     * 검증 항목:
     * - 저장된 블로그가 존재하는지 확인
     * - 저장된 블로그의 필드 값들이 입력한 값과 동일한지 확인
     */
    @Test
    @DisplayName("블로그정보 저장(생성)")
    void save() {

    }

    /**
     * 블로그 정보를 수정하는 테스트.
     * 이 테스트는 기존에 저장된 블로그를 수정하고, 수정된 값을 DB에서 조회하여
     * 수정이 성공적으로 되었는지 확인합니다.
     * 검증 항목:
     * - 수정된 블로그의 필드 값들이 입력한 수정 값과 동일한지 확인
     */
    @Test
    @DisplayName("블로그정보 수정")
    void update() {

    }

    /**
     * 블로그를 삭제하는 테스트.
     * 이 테스트는 저장된 블로그를 삭제하고, 삭제된 후 DB에서 해당 블로그가 존재하지 않는지
     * 확인합니다.
     * 검증 항목:
     * - 블로그 삭제 후 DB에서 해당 블로그가 존재하지 않는지 확인
     */
    @Test
    @DisplayName("blog삭제")
    void delete() {

    }

    /**
     * 블로그 ID로 블로그 정보를 조회하는 테스트.
     * 이 테스트는 블로그 ID로 블로그 정보를 조회하여 존재하는지 확인하고,
     * 해당 블로그의 필드 값들을 검증합니다.
     * 검증 항목:
     * - 조회된 블로그가 존재하는지 확인
     * - 조회된 블로그의 필드 값들이 정확한지 확인
     */
    @Test
    void findByBlogInfoId() {

    }

    /**
     * 주어진 blogId로 블로그가 존재하는지 확인하는 테스트 (존재하는 경우).
     * 이 테스트는 특정 블로그 ID에 대해 블로그가 존재하는지 확인하고,
     * 그 결과가 true인지를 검증합니다.
     * 검증 항목:
     * - 블로그가 존재하는지 여부를 확인
     */
    @Test
    @DisplayName("블로그존재여부-by-blogId:true")
    void existByBlogId() {

    }

    /**
     * 주어진 blogId로 블로그가 존재하지 않는지 확인하는 테스트 (존재하지 않는 경우).
     * 이 테스트는 특정 블로그 ID에 대해 블로그가 존재하지 않는지 확인하고,
     * 그 결과가 false인지를 검증합니다.
     * 검증 항목:
     * - 블로그가 존재하지 않는지 여부를 확인
     */
    @Test
    @DisplayName("블로그존재여부-by-blogId:false")
    void notExistByBlogId() {

    }

    /**
     * 주어진 blogFid로 블로그가 존재하는지 확인하는 테스트 (존재하는 경우).
     * 이 테스트는 특정 블로그 fid에 대해 블로그가 존재하는지 확인하고,
     * 그 결과가 true인지를 검증합니다.
     * 검증 항목:
     * - 블로그 fid가 존재하는지 여부를 확인
     */
    @Test
    @DisplayName("블로그존재여부-by-blogFid:true")
    void existByBlogFid(){

    }

    /**
     * 주어진 blogFid로 블로그가 존재하지 않는지 확인하는 테스트 (존재하지 않는 경우).
     * 이 테스트는 특정 블로그 fid에 대해 블로그가 존재하지 않는지 확인하고,
     * 그 결과가 false인지를 검증합니다.
     * 검증 항목:
     * - 블로그 fid가 존재하지 않는지 여부를 확인
     */
    @Test
    @DisplayName("블로그존재여부-by-blogFid:false")
    void notExistByBlogFid(){

    }

    /**
     * 블로그의 공개 여부를 "공개"로 설정하는 테스트.
     * 이 테스트는 블로그의 공개 여부를 true로 업데이트한 후, 해당 블로그가 공개 상태인지
     * 확인합니다.
     * 검증 항목:
     * - 블로그가 공개 상태인지 확인
     */
    @Test
    @DisplayName("블로그 공개여부 설정 : 공개")
    void updateByBlogIsPublic_true(){

    }

    /**
     * 블로그의 공개 여부를 "비공개"로 설정하는 테스트.
     * 이 테스트는 블로그의 공개 여부를 false로 업데이트한 후, 해당 블로그가 비공개 상태인지
     * 확인합니다.
     * 검증 항목:
     * - 블로그가 비공개 상태인지 확인
     */
    @Test
    @DisplayName("블로그 공개여부 설정 : 비공개")
    void updateByBlogIsPublic_false(){

    }

}
