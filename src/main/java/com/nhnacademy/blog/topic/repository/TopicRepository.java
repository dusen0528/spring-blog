package com.nhnacademy.blog.topic.repository;

import com.nhnacademy.blog.topic.domain.Topic;
import com.nhnacademy.blog.topic.dto.TopicUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface TopicRepository {

    /**
     * 새로운 주제를 저장합니다.
     *
     * @param topic 저장할 주제 객체
     */
    void save(Topic topic);

    /**
     * 기존 주제를 수정합니다.
     *
     * @param topicUpdateRequestDto 수정할 주제 정보 요청 객체
     */
    void update(TopicUpdateRequestDto topicUpdateRequestDto);

    /**
     * 주제를 삭제합니다.
     *
     * @param topicId 삭제할 주제의 ID
     */
    void deleteByTopicId(Integer topicId);

    /**
     * 주제 ID를 기준으로 주제를 조회합니다.
     *
     * @param topicId 조회할 주제의 ID
     * @return 조회된 주제 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Topic> findByTopicId(Integer topicId);

    /**
     * topicPid를 기준으로 모든 주제를 조회합니다.
     *
     * @param topicPid 조회할 부모 주제 ID
     * @return 주제 리스트
     */
    List<Topic> findAll(Integer topicPid);

    /**
     * 주제가 존재하는지 여부를 확인합니다.
     *
     * @param topicId 확인할 주제의 ID
     * @return 주제가 존재하면 true, 존재하지 않으면 false
     */
    boolean existByTopicId(Integer topicId);
}
