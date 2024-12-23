package com.nhnacademy.blog.member.dto;

/**
 * 회원정보 UPDATE Dto
 * 회원 정보 업데이트 요청을 위한 DTO 클래스입니다.
 */
public class MemberUpdateRequest {
    // 회원 번호
    private final Long mbNo;

    // 회원 이메일
    private final String mbEmail;

    // 회원 이름
    private final String mbName;

    // 모바일 연락처
    private final String mbMobile;

    // MemberUpdateRequest 생성자
    public MemberUpdateRequest(Long mbNo, String mbEmail, String mbName, String mbMobile) {
        this.mbNo = mbNo;
        this.mbEmail = mbEmail;
        this.mbName = mbName;
        this.mbMobile = mbMobile;
    }

    // 모바일 연락처를 반환하는 getter 메서드
    public String getMbMobile() {
        return mbMobile;
    }

    // 회원 이름을 반환하는 getter 메서드
    public String getMbName() {
        return mbName;
    }

    // 회원 이메일을 반환하는 getter 메서드
    public String getMbEmail() {
        return mbEmail;
    }

    // 회원 번호를 반환하는 getter 메서드
    public Long getMbNo() {
        return mbNo;
    }
}