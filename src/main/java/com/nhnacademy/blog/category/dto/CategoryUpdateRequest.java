package com.nhnacademy.blog.category.dto;

/** TODO#6-2 CategoryUpdateRequest class 구현
 * CategoryUpdateRequest 클래스는 카테고리 업데이트 요청을 나타냅니다.
 * 이 클래스는 카테고리 ID, 상위 카테고리 ID, 블로그 ID, 주제 ID, 카테고리 이름, 카테고리 순서 등의 정보를 포함합니다.
 */
public class CategoryUpdateRequest {
    private final Long categoryId;   // 카테고리 ID
    private final Long categoryPid;  // 상위 카테고리 ID
    private final Long blogId;       // 블로그 ID
    private final Integer topicId;   // 주제 ID
    private final String categoryName; // 카테고리 이름
    private final Integer categorySec; // 카테고리 순서

    /**
     * CategoryUpdateRequest 객체의 생성자입니다.
     * @param categoryId 카테고리 ID
     * @param categoryPid 상위 카테고리 ID
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     */
    public CategoryUpdateRequest(Long categoryId, Long categoryPid, Long blogId, Integer topicId, String categoryName, Integer categorySec) {
        this.categoryId = null;
        this.categoryPid = null;
        this.blogId = null;
        this.topicId = null;
        this.categoryName = null;
        this.categorySec = null;
    }

}
