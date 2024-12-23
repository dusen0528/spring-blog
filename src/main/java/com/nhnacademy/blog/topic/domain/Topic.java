package com.nhnacademy.blog.topic.domain;

import java.time.LocalDateTime;

/** TODO#5-1 Topic class 구현
 * Topic 클래스는 블로그의 주제를 나타내는 도메인 클래스입니다.
 * 이 클래스는 주제의 ID, 상위 주제 ID, 주제 이름, 주제 순서, 생성일시, 수정일시 등의 정보를 포함합니다.
 */
public class Topic {

    private final Integer topicId;       // 주제 ID
    private final Integer topicPid;      // 상위 주제 ID
    private final String topicName;      // 주제 이름
    private final Integer topicSec;      // 주제 순서
    private final LocalDateTime createdAt; // 생성일시
    private final LocalDateTime updatedAt; // 수정일시

    /**
     * Topic 객체의 생성자입니다.
     * @param topicId 주제 ID
     * @param topicPid 상위 주제 ID
     * @param topicName 주제 이름
     * @param topicSec 주제 순서
     * @param createdAt 생성일시
     * @param updatedAt 수정일시
     */
    private Topic(Integer topicId, Integer topicPid, String topicName, Integer topicSec, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.topicId = null;
        this.topicPid = null;
        this.topicName = null;
        this.topicSec = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    /**
     * 새 루트 주제를 생성합니다.
     * @param topicName 주제 이름
     * @param topicSec 주제 순서
     * @return 새 루트 주제
     */
    public static Topic ofNewRootTopic(String topicName, Integer topicSec) {
        return null;
    }

    /**
     * 새 하위 주제를 생성합니다.
     * @param topicPid 상위 주제 ID
     * @param topicName 주제 이름
     * @param topicSec 주제 순서
     * @return 새 하위 주제
     */
    public static Topic ofNewSubTopic(Integer topicPid, String topicName, Integer topicSec) {
        return null;
    }

    /**
     * 기존 주제를 생성합니다.
     * @param topicId 주제 ID
     * @param topicPid 상위 주제 ID
     * @param topicName 주제 이름
     * @param topicSec 주제 순서
     * @param createdAt 생성일시
     * @param updatedAt 수정일시
     * @return 기존 주제
     */
    public static Topic ofExistingTopic(Integer topicId, Integer topicPid, String topicName, Integer topicSec, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return null;
    }

}
