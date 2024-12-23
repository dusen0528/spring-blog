package com.nhnacademy.blog.blogmember.domain;

/** TODO#4-1 BlogMemberMapping class 구현
 * 블로그 회원과 관련된 정보를 나타내는 클래스입니다.
 *
 * 이 클래스는 블로그에 대한 회원 정보와 역할(Role)을 관리하며, 블로그의 특정 회원을 식별하고,
 * 해당 회원의 역할을 지정하는데 사용됩니다.
 */
public class BlogMemberMapping {

    private final Long blogMemberId;  // 블로그 회원 ID
    private final Long mbNo;          // 회원 번호 (회원 식별 ID)
    private final Long blogId;        // 블로그 ID
    private final String roleId;      // 회원의 역할 ID

    /**
     * 생성자: BlogMemberMapping 객체를 생성합니다.
     *
     * 생성자는 blogMemberId 없이 신규 블로그 회원 정보를 생성하는 데 사용됩니다.
     *
     * @param mbNo 회원 번호
     * @param blogId 블로그 ID
     * @param roleId 회원 역할 ID
     */
    private BlogMemberMapping(Long blogMemberId, Long mbNo, Long blogId, String roleId) {
        this.blogMemberId = null;
        this.mbNo = null;
        this.blogId = null;
        this.roleId = null;
    }

    /**
     * 신규 블로그 회원 정보를 생성하는 정적 팩토리 메서드입니다.
     *
     * 블로그 회원 ID는 null로 설정되며, 새로운 블로그 회원을 위한 정보를 반환합니다.
     *
     * @param mbNo 회원 번호
     * @param blogId 블로그 ID
     * @param roleId 회원 역할 ID
     * @return 신규 BlogMemberMapping 객체
     */
    public static BlogMemberMapping ofNewBlogMemberMapping(Long mbNo, Long blogId, String roleId) {
        return null;
    }

    /**
     * 기존 블로그 회원 정보를 생성하는 정적 팩토리 메서드입니다.
     *
     * 블로그 회원 ID를 포함한 기존의 블로그 회원 정보를 반환합니다.
     *
     * @param blogMemberId 블로그 회원 ID
     * @param mbNo 회원 번호
     * @param blogId 블로그 ID
     * @param roleId 회원 역할 ID
     * @return 기존 BlogMemberMapping 객체
     */
    public static BlogMemberMapping ofExistingBlogMemberMapping(Long blogMemberId, Long mbNo, Long blogId, String roleId) {
        return null;
    }

}
