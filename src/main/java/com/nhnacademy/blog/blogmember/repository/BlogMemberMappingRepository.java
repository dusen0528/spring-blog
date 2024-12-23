package com.nhnacademy.blog.blogmember.repository;

import com.nhnacademy.blog.blogmember.domain.BlogMemberMapping;

import java.util.Optional;

public interface BlogMemberMappingRepository {

    /**
     * 블로그 회원 매핑을 저장합니다.
     *
     * @param blogMemberMapping 저장할 블로그 회원 매핑 객체
     */
    void save(BlogMemberMapping blogMemberMapping);

    /**
     * 블로그 회원 매핑을 삭제합니다.
     *
     * @param blogMemberId 삭제할 블로그 회원 매핑의 ID
     */
    void deleteByBlogMemberMappingId(Long blogMemberId);

    /**
     * 블로그 회원 ID를 기준으로 블로그 회원 매핑을 조회합니다.
     *
     * @param blogMemberId 조회할 블로그 회원 매핑의 ID
     * @return 조회된 블로그 회원 매핑, 존재하지 않으면 빈 Optional
     */
    Optional<BlogMemberMapping> findByBlogMemberId(Long blogMemberId);

    /**
     * 블로그 ID와 회원 번호를 기준으로 블로그 회원 매핑을 조회합니다.
     *
     * @param mbNo 회원 번호
     * @param blogId 블로그 ID
     * @return 조회된 블로그 회원 매핑, 존재하지 않으면 빈 Optional
     */
    Optional<BlogMemberMapping> findByMbNoAndBlogId(Long mbNo, Long blogId);
}
