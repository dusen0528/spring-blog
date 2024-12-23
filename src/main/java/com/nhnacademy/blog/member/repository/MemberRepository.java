package com.nhnacademy.blog.member.repository;

import com.nhnacademy.blog.member.domain.Member;
import com.nhnacademy.blog.member.dto.MemberUpdateRequest;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * TODO#1 MemberRepository를 구현 합니다.
 * - MemberRepository 기반으로 JdbcMemberRepository를 구현 합니다.
 */
public interface MemberRepository {

    /**
     * 회원을 저장합니다.
     *
     * @param member 저장할 회원 객체
     */
    void save(Member member);

    /**
     * 회원 정보를 수정합니다.
     *
     * @param memberUpdateRequest 수정할 회원 정보 요청 객체
     */
    void update(MemberUpdateRequest memberUpdateRequest);

    /**
     * 회원을 삭제합니다.
     *
     * @param mbNo 삭제할 회원의 회원 번호
     */
    void deleteByMbNo(long mbNo);

    /**
     * 회원의 비밀번호를 변경합니다.
     *
     * @param mbNo 회원 번호
     * @param mbPassword 새로운 비밀번호
     */
    void updatePassword(long mbNo, String mbPassword);

    /**
     * 회원 번호를 사용하여 회원을 조회합니다.
     *
     * @param mbNo 조회할 회원의 회원 번호
     * @return 조회된 회원 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Member> findByMbNo(long mbNo);

    /**
     * 이메일을 사용하여 회원을 조회합니다.
     *
     * @param mbEmail 조회할 회원의 이메일
     * @return 조회된 회원 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Member> findByMbEmail(String mbEmail);

    /**
     * 모바일 연락처를 사용하여 회원을 조회합니다.
     *
     * @param mbMobile 조회할 회원의 모바일 연락처
     * @return 조회된 회원 정보, 존재하지 않으면 빈 Optional
     */
    Optional<Member> findByMbMobile(String mbMobile);

    /**
     * 회원 번호로 회원이 존재하는지 여부를 확인합니다.
     *
     * @param mbNo 확인할 회원의 회원 번호
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    boolean existsByMbNo(long mbNo);

    /**
     * 이메일로 회원이 존재하는지 여부를 확인합니다.
     *
     * @param mbEmail 확인할 회원의 이메일
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    boolean existsByMbEmail(String mbEmail);

    /**
     * 모바일 연락처로 회원이 존재하는지 여부를 확인합니다.
     *
     * @param mbMobile 확인할 회원의 모바일 연락처
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    boolean existsByMbMobile(String mbMobile);

    /**
     * 회원 탈퇴 여부를 확인합니다.
     *
     * @param mbNo 확인할 회원의 회원 번호
     * @return 탈퇴한 회원이면 true, 그렇지 않으면 false
     */
    boolean isMemberWithdrawn(Long mbNo);

    /**
     * 회원 탈퇴 시 탈퇴 일자를 변경합니다.
     *
     * @param mbNo 회원 번호
     * @param updateWithdrawalAt 탈퇴 일자
     */
    void updateWithdrawalAt(long mbNo, LocalDateTime updateWithdrawalAt);
}
