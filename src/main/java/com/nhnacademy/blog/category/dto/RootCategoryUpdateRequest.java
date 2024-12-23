package com.nhnacademy.blog.category.dto;

/** TODO#6-2 RootCategoryUpdateRequest class 구현
 * RootCategoryUpdateRequest 클래스는 루트 카테고리 업데이트 요청을 나타냅니다.
 * 이 클래스는 카테고리 ID, 블로그 ID, 주제 ID, 카테고리 이름, 카테고리 순서 등의 정보를 포함합니다.
 */
public class RootCategoryUpdateRequest {

    private final Long categoryId;      // 카테고리 ID
    private final Long blogId;          // 블로그 ID
    private final Integer topicId;      // 주제 ID
    private final String categoryName;  // 카테고리 이름
    private final int categorySec;      // 카테고리 순서

    /**
     * RootCategoryUpdateRequest 객체의 생성자입니다.
     * @param categoryId 카테고리 ID
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     */
    public RootCategoryUpdateRequest(Long categoryId, Long blogId, Integer topicId, String categoryName, int categorySec) {
        this.categoryId = null;
        this.blogId = null;
        this.topicId = null;
        this.categoryName = null;
        this.categorySec = 0;
    }

}
