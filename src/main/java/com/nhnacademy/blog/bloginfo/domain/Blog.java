package com.nhnacademy.blog.bloginfo.domain;

import java.time.LocalDateTime;
//TODO#3-1 Blog class 구현
@SuppressWarnings("java:S107")
public class Blog {

    // 블로그 ID
    private final Long blogId;
    // 블로그 FID
    private final String blogFid;
    // 블로그 메인 여부
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

    // Blog 클래스의 private 생성자
    private Blog(Long blogId, String blogFid, boolean blogMain, String blogName, String blogMbNickname, String blogDescription, Boolean blogIsPublic, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.blogId = null;
        this.blogFid = null;
        this.blogMain = true;
        this.blogName = null;
        this.blogMbNickname = null;
        this.blogDescription = null;
        this.blogIsPublic = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    // 새로운 블로그 객체를 생성하는 정적 팩토리 메서드
    public static Blog ofNewBlog(String blogFid, Boolean blogMain, String blogName, String blogMbNickname, String blogDescription) {
        return null;
    }

    // 기존 블로그 정보를 이용해 블로그 객체를 생성하는 정적 팩토리 메서드
    public static Blog ofExistingBlogInfo(Long blogId, String blogFid, Boolean blogMain, String blogName, String blogMbNickname, String blogDescription, Boolean blogIsPublic, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return null;
    }

}
