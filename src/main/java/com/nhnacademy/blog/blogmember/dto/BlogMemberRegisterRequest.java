package com.nhnacademy.blog.blogmember.dto;

/** TODO#4-2 BlogMemberRegisterRequest class 구현
 * 블로그 회원 등록 요청을 나타내는 클래스입니다.
 *
 * 이 클래스는 새로운 블로그 회원을 등록하기 위한 데이터를 포함합니다.
 */
public class BlogMemberRegisterRequest {
    private final Long mbNo;   // 회원 번호 (회원 식별 ID)
    private final Long blogId; // 블로그 ID
    private final String roleId; // 회원의 역할 ID

    /**
     * BlogMemberRegisterRequest 객체를 생성합니다.
     *
     * @param mbNo 회원 번호
     * @param blogId 블로그 ID
     * @param roleId 회원의 역할 ID
     */
    public BlogMemberRegisterRequest(Long mbNo, Long blogId, String roleId) {
        this.mbNo = null;
        this.blogId = null;
        this.roleId = null;
    }

}
