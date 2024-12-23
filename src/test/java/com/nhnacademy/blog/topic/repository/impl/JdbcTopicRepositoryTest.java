package com.nhnacademy.blog.topic.repository.impl;

import com.nhnacademy.blog.bloginfo.repository.BlogRepository;
import com.nhnacademy.blog.bloginfo.repository.impl.JdbcBlogRepository;
import com.nhnacademy.blog.category.repository.CategoryRepository;
import com.nhnacademy.blog.category.repository.impl.JdbcCategoryRepository;
import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.topic.domain.Topic;
import com.nhnacademy.blog.topic.dto.TopicUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

//TODO#5-TEST
@Slf4j
class JdbcTopicRepositoryTest {

    static JdbcTopicRepository topicRepository;

    /**
     * 모든 테스트가 실행되기 전에 한 번만 실행됩니다.
     * 테스트를 위한 리포지토리를 초기화합니다.
     */
    @BeforeAll
    static void beforeAll() {
        Context context = ContextHolder.getApplicationContext();
        topicRepository = (JdbcTopicRepository) context.getBean(JdbcTopicRepository.BEAN_NAME);
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
    @DisplayName("topic-저장")
    void save() {
        //TODO: 주제를 저장하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성합니다.
        //  2. 주제를 데이터베이스에 저장합니다.
        //  3. 저장된 주제를 조회하여 검증합니다.
        //  4. 주제 ID, 상위 주제 ID, 주제 이름, 생성일시가 올바르게 저장되었는지 확인합니다.
    }

    @Test
    @DisplayName("sub-topic-저장")
    void save_subTopic() {
        //TODO: 하위 주제를 저장하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성하고 저장합니다.
        //  2. 루트 주제의 하위 주제를 생성하고 저장합니다.
        //  3. 저장된 하위 주제를 조회하여 검증합니다.
        //  4. 하위 주제 ID, 상위 주제 ID, 주제 이름, 생성일시가 올바르게 저장되었는지 확인합니다.
    }

    @Test
    @DisplayName("topic-수정")
    void update() {
        //TODO: 주제를 수정하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성하고 저장합니다.
        //  2. 주제 정보를 업데이트합니다.
        //  3. 업데이트된 주제를 조회하여 검증합니다.
        //  4. 주제 이름과 순서, 수정일시가 올바르게 업데이트되었는지 확인합니다.
    }

    @Test
    @DisplayName("topic-삭제")
    void deleteByTopicId() {
        //TODO: 주제를 삭제하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성하고 저장합니다.
        //  2. 주제를 데이터베이스에서 삭제합니다.
        //  3. 삭제된 주제가 데이터베이스에서 더 이상 존재하지 않는지 확인합니다.
    }

    @Test
    @DisplayName("topic-조회-byTopicId")
    void findByTopicId() {
        //TODO: 주제를 ID로 조회하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성하고 저장합니다.
        //  2. 주제 ID로 주제를 조회합니다.
        //  3. 조회된 주제가 올바르게 반환되는지 검증합니다.
        //  4. 주제 ID, 상위 주제 ID, 주제 이름, 생성일시가 올바르게 조회되었는지 확인합니다.
    }

    @Test
    @DisplayName("topic -조회 (Root)")
    @Disabled
    void findAll() {
        // TODO: 모든 루트 주제를 조회하는 기능을 테스트합니다.
        //  1. 여러 개의 루트 주제를 생성하고 저장합니다.
        //  2. 모든 루트 주제를 조회합니다.
        //  3. 조회된 루트 주제 목록이 올바르게 반환되는지 검증합니다.
        //  4. 각 주제의 ID, 이름, 순서, 생성일시가 올바르게 조회되었는지 확인합니다.
    }

    @Test
    @DisplayName("topic -조회 (sub)")
    void findAll_subTopic() {
        // TODO: 모든 하위 주제를 조회하는 기능을 테스트합니다.
        //  1. 여러 개의 루트 주제를 생성하고 저장합니다.
        //  2. 루트 주제의 하위 주제를 생성하고 저장합니다.
        //  3. 특정 루트 주제에 대한 하위 주제들을 조회합니다.
        //  4. 조회된 하위 주제 목록이 올바르게 반환되는지 검증합니다.
        //  5. 각 하위 주제의 ID, 상위 주제 ID, 이름, 순서, 생성일시가 올바르게 조회되었는지 확인합니다.
    }

    @Test
    @DisplayName("topic-존재여부체크 : true")
    void existByTopicId() {
        // TODO: 주제 ID로 주제가 존재하는지 확인하는 기능을 테스트합니다.
        //  1. 새로운 루트 주제를 생성하고 저장합니다.
        //  2. 해당 주제 ID로 존재 여부를 확인합니다.
        //  3. 주제가 존재하는 경우 true를 반환하는지 검증합니다.
    }

    @Test
    @DisplayName("topic-존재여부체크 : false")
    void notExistByTopicId() {
        // TODO: 존재하지 않는 주제 ID로 주제 존재 여부를 확인하는 기능을 테스트합니다.
        //  1. 존재하지 않는 주제 ID로 존재 여부를 확인합니다.
        //  2. 주제가 존재하지 않는 경우 false를 반환하는지 검증합니다.
    }
}
