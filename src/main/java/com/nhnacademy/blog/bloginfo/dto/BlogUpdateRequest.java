package com.nhnacademy.blog.bloginfo.dto;

/** TODO#3-2 BlogUpdateRequest class 구현
 * 블로그 업데이트 요청을 위한 DTO 클래스입니다.
 * 블로그 ID, 메인 여부, 블로그 이름, 회원 닉네임, 블로그 설명을 포함합니다.
 */
public class BlogUpdateRequest {

    // 블로그 ID (고유 식별자)
    private final Long blogId;
    // 블로그 메인 여부 (해당 블로그가 메인 블로그인지 여부)
    private final Boolean blogMain;
    // 블로그 이름
    private final String blogName;
    // 블로그 회원 닉네임
    private final String blogMbNickname;
    // 블로그 설명
    private final String blogDescription;

    // BlogUpdateRequest 클래스의 생성자
    public BlogUpdateRequest(Long blogId, Boolean blogMain, String blogName, String blogMbNickname, String blogDescription) {
        this.blogId = null;
        this.blogMain = null;
        this.blogName = null;
        this.blogMbNickname = null;
        this.blogDescription = null;
    }

}
