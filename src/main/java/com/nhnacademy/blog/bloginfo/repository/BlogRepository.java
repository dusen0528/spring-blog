package com.nhnacademy.blog.bloginfo.repository;

import com.nhnacademy.blog.bloginfo.domain.Blog;
import com.nhnacademy.blog.bloginfo.dto.BlogResponse;
import com.nhnacademy.blog.bloginfo.dto.BlogUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface BlogRepository {

    /**
     * 블로그를 저장합니다.
     *
     * @param blog 저장할 블로그 객체
     */
    void save(Blog blog);

    /**
     * 블로그를 수정합니다.
     *
     * @param blogUpdateRequest 블로그 수정 요청 객체
     */
    void update(BlogUpdateRequest blogUpdateRequest);

    /**
     * 주어진 블로그 ID에 해당하는 블로그를 삭제합니다.
     *
     * @param blogId 삭제할 블로그의 ID
     */
    void deleteByBlogId(long blogId);

    /**
     * 블로그 ID를 기준으로 블로그를 조회합니다.
     *
     * @param blogId 조회할 블로그의 ID
     * @return 조회된 블로그 객체, 존재하지 않으면 빈 Optional
     */
    Optional<Blog> findByBlogId(long blogId);

    /**
     * 블로그 ID가 존재하는지 여부를 확인합니다.
     *
     * @param blogId 확인할 블로그의 ID
     * @return 존재하면 true, 존재하지 않으면 false
     */
    boolean existByBlogId(long blogId);

    /**
     * 블로그의 FID가 존재하는지 여부를 확인합니다.
     *
     * @param blogFid 확인할 블로그 FID
     * @return 존재하면 true, 존재하지 않으면 false
     */
    boolean existByBlogFid(String blogFid);

    /**
     * 주어진 회원 번호에 해당하는 주 블로그가 존재하는지 여부를 확인합니다.
     *
     * @param mbNo 회원 번호
     * @return 주 블로그가 존재하면 true, 존재하지 않으면 false
     */
    boolean existMainBlogByMbNo(long mbNo);

    /**
     * 블로그의 공개 여부를 설정합니다.
     *
     * @param blogId 공개 여부를 설정할 블로그의 ID
     * @param blogIsPublic 공개 여부 (true: 공개, false: 비공개)
     */
    void updateByBlogIsPublic(long blogId, boolean blogIsPublic);

    /**
     * 블로그의 주 블로그 설정 값을 변경합니다.
     *
     * @param blogId 주 블로그로 설정할 블로그의 ID
     * @param isBlogMain 주 블로그 설정 여부 (true: 주 블로그, false: 아니오)
     */
    void updateBlogMain(long blogId, boolean isBlogMain);

    /**
     * 회원이 소속된 또는 소유한 블로그의 수를 조회합니다.
     *
     * @param mbNo 회원 번호
     * @param roleId 회원의 역할 (ROLE_OWNER: 소유자, ROLE_MEMBER: 팀원)
     * @return 소속된 또는 소유한 블로그의 수
     */
    long countByMbNo(long mbNo, String roleId);

    /**
     * 회원이 소유하거나 소속된 모든 블로그를 조회합니다.
     *
     * @param mbNo 회원 번호
     * @param roleId 회원의 역할 (ROLE_OWNER: 소유한 블로그, ROLE_MEMBER: 소속된 블로그)
     * @return 소유한 또는 소속된 블로그들의 리스트
     */
    List<BlogResponse> findAllBlogs(long mbNo, String roleId);
}
