package com.nhnacademy.blog.member.domain;

import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("java:S107")
public class Member {

    // 회원 번호
    private final Long mbNo;
    // 회원 이메일
    private final String mbEmail;
    // 회원 이름
    private final String mbName;
    // 비밀번호
    private final String mbPassword;
    // 모바일 연락처
    private final String mbMobile;
    // 생성일 (가입일)
    private final LocalDateTime createdAt;
    // 수정일
    private final LocalDateTime updatedAt;
    // 탈퇴일
    private final LocalDateTime withdrawalAt;

    // Member 객체의 private 생성자
    private Member(Long mbNo, String mbEmail, String mbName, String mbPassword, String mbMobile, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime withdrawalAt) {
        this.mbNo = mbNo;
        this.mbEmail = mbEmail;
        this.mbName = mbName;
        this.mbPassword = mbPassword;
        this.mbMobile = mbMobile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.withdrawalAt = withdrawalAt;
    }

    // 새로운 회원을 생성하는 정적 팩토리 메서드
    public static Member ofNewMember(String mbEmail, String mbName, String mbPassword, String mbMobile) {
        return new Member(null, mbEmail, mbName, mbPassword, mbMobile, null, null, null);
    }

    // 기존 회원을 생성하는 정적 팩토리 메서드
    public static Member ofExistingMember(Long mbNo, String mbEmail, String mbName, String mbPassword, String mbMobile, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime withdrawalAt) {
        return new Member(mbNo, mbEmail, mbName, mbPassword, mbMobile, createdAt, updatedAt, withdrawalAt);
    }

    // 회원 번호를 반환하는 getter 메서드
    public Long getMbNo() {
        return mbNo;
    }

    // 회원 이메일을 반환하는 getter 메서드
    public String getMbEmail() {
        return mbEmail;
    }

    // 회원 이름을 반환하는 getter 메서드
    public String getMbName() {
        return mbName;
    }

    // 회원 비밀번호를 반환하는 getter 메서드
    public String getMbPassword() {
        return mbPassword;
    }

    // 모바일 연락처를 반환하는 getter 메서드
    public String getMbMobile() {
        return mbMobile;
    }

    // 생성일 (가입일)을 반환하는 getter 메서드
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 수정일을 반환하는 getter 메서드
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // 탈퇴일을 반환하는 getter 메서드
    public LocalDateTime getWithdrawalAt() {
        return withdrawalAt;
    }

    // equals 메서드 재정의: 회원 객체 비교
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(mbNo, member.mbNo) && Objects.equals(mbEmail, member.mbEmail) && Objects.equals(mbName, member.mbName) && Objects.equals(mbPassword, member.mbPassword) && Objects.equals(mbMobile, member.mbMobile) && Objects.equals(createdAt, member.createdAt) && Objects.equals(updatedAt, member.updatedAt) && Objects.equals(withdrawalAt, member.withdrawalAt);
    }

    // hashCode 메서드 재정의: 해시 코드 생성
    @Override
    public int hashCode() {
        return Objects.hash(mbNo, mbEmail, mbName, mbPassword, mbMobile, createdAt, updatedAt, withdrawalAt);
    }

    // toString 메서드 재정의: 회원 객체를 문자열로 표현
    @Override
    public String toString() {
        return "Member{" +
                "mbNo=" + mbNo +
                ", mbEmail='" + mbEmail + '\'' +
                ", mbName='" + mbName + '\'' +
                ", mbPassword='" + mbPassword + '\'' +
                ", mbMobile='" + mbMobile + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", withdrawalAt=" + withdrawalAt +
                '}';
    }
}
