package com.nhnacademy.blog.bloginfo.dto;

/** TODO#3-3 BlogVisibilityUpdateRequest class 구현
 * 블로그 공개 여부 업데이트 요청을 위한 DTO 클래스입니다.
 * 블로그 ID와 블로그의 공개 여부를 포함합니다.
 */
public class BlogVisibilityUpdateRequest {
    // 블로그 ID (고유 식별자)
    private final Long blogId;
    // 블로그 공개 여부
    private final Boolean blogIsPublic;

    // BlogVisibilityUpdateRequest 클래스의 생성자
    public BlogVisibilityUpdateRequest(Long blogId, Boolean blogIsPublic) {
        this.blogId = null;
        this.blogIsPublic = null;
    }

}
