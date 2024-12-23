package com.nhnacademy.blog.topic.dto;

/** TODO#5-2 TopicUpdateRequestDto class 구현
 * TopicUpdateRequestDto 클래스는 주제 업데이트 요청을 나타냅니다.
 * 이 클래스는 주제 ID, 상위 주제 ID, 주제 이름, 주제 순서 등의 정보를 포함합니다.
 */
public class TopicUpdateRequestDto {
    private final Integer topicId;   // 주제 ID
    private final Integer topicPid;  // 상위 주제 ID
    private final String topicName;  // 주제 이름
    private final Integer topicSec;  // 주제 순서

    /**
     * TopicUpdateRequestDto 객체의 생성자입니다.
     * @param topicId 주제 ID
     * @param topicPid 상위 주제 ID
     * @param topicName 주제 이름
     * @param topicSec 주제 순서
     */
    public TopicUpdateRequestDto(Integer topicId, Integer topicPid, String topicName, Integer topicSec) {
        this.topicId = null;
        this.topicPid = null;
        this.topicName = null;
        this.topicSec = null;
    }

}
