package com.nhnacademy.blog.bloginfo.dto;

import java.time.LocalDateTime;

/** TODO#3-4 BlogResponse class 구현
 * 블로그 응답 데이터를 위한 DTO 클래스입니다.
 * 블로그의 ID, FID, 메인 여부, 이름, 회원 닉네임, 설명, 공개 여부, 생성일 및 수정일을 포함합니다.
 */
public class BlogResponse {

    // 블로그 ID (고유 식별자)
    private final Long blogId;
    // 블로그 FID (블로그 식별자) ex)blog.nhnacademy.com/{FID}, blog.nhnacademy.com/marco ...
    private final String blogFid;
    // 블로그 메인 여부 (해당 블로그가 메인 블로그인지 여부)
    private final boolean blogMain;
    // 블로그 이름
    private final String blogName;
    // 블로그 회원 닉네임
    private final String blogMbNickname;
    // 블로그 설명
    private final String blogDescription;
    // 블로그 공개 여부
    private final Boolean blogIsPublic;
    // 생성일 (가입일)
    private final LocalDateTime createdAt;
    // 수정일
    private final LocalDateTime updatedAt;

    // BlogResponse 클래스의 생성자
    public BlogResponse(Long blogId, String blogFid, boolean blogMain, String blogName, String blogMbNickname, String blogDescription, Boolean blogIsPublic, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.blogId = null;
        this.blogFid = null;
        this.blogMain = false;
        this.blogName = null;
        this.blogMbNickname = null;
        this.blogDescription = null;
        this.blogIsPublic = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

}
