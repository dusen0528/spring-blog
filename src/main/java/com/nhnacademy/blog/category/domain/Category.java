package com.nhnacademy.blog.category.domain;

import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("java:S107")
/** TODO#6-1 Category class 구현
 * Category 클래스는 블로그의 카테고리를 나타내는 도메인 클래스입니다.
 * 이 클래스는 카테고리의 ID, 상위 카테고리 ID, 블로그 ID, 주제 ID, 카테고리 이름, 카테고리 순서, 생성일시, 수정일시 등의 정보를 포함합니다.
 */
public class Category {

    private final Long categoryId;       // 카테고리 ID
    private final Long categoryPid;      // 상위 카테고리 ID
    private final Long blogId;           // 블로그 ID
    private final Integer topicId;       // 주제 ID
    private final String categoryName;   // 카테고리 이름
    private final Integer categorySec;   // 카테고리 순서
    private final LocalDateTime createdAt; // 생성일시
    private final LocalDateTime updatedAt; // 수정일시

    /**
     * Category 객체의 생성자입니다.
     * @param categoryId 카테고리 ID
     * @param categoryPid 상위 카테고리 ID
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     * @param createdAt 생성일시
     * @param updatedAt 수정일시
     */
    private Category(Long categoryId, Long categoryPid, Long blogId, Integer topicId, String categoryName, Integer categorySec, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.categoryId = null;
        this.categoryPid = null;
        this.blogId = null;
        this.topicId = null;
        this.categoryName = null;
        this.categorySec = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    /**
     * 새 루트 카테고리를 생성합니다.
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     * @return 새 루트 카테고리
     */
    public static Category ofNewRootCategory(Long blogId, Integer topicId, String categoryName, Integer categorySec) {
        return null;
    }

    /**
     * 새 하위 카테고리를 생성합니다.
     * @param categoryPid 상위 카테고리 ID
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     * @return 새 하위 카테고리
     */
    public static Category ofNewSubCategory(Long categoryPid, Long blogId, Integer topicId, String categoryName, Integer categorySec) {
        return null;
    }

    /**
     * 기존 카테고리를 생성합니다.
     * @param categoryId 카테고리 ID
     * @param categoryPid 상위 카테고리 ID
     * @param blogId 블로그 ID
     * @param topicId 주제 ID
     * @param categoryName 카테고리 이름
     * @param categorySec 카테고리 순서
     * @param createdAt 생성일시
     * @param updatedAt 수정일시
     * @return 기존 카테고리
     */
    public static Category ofExistingCategory(Long categoryId, Long categoryPid, Long blogId, Integer topicId, String categoryName, Integer categorySec, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return null;
    }

}
