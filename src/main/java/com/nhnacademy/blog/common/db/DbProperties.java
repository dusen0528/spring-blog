package com.nhnacademy.blog.common.db;

@SuppressWarnings("java:S107")
public class DbProperties {
    /**
     * TODO#4-6 DbProperties 객체를 구현 합니다.
     */

    //기본 BEAN_NAME을 설정 합니다.
    public static final String BEAN_NAME="dbProperties";

    private final String url;
    private final String username;
    private final String password;

    private final int initialSize;
    private final int maxTotal;
    private final int maxIdle;
    private final int minIdle;

    private final int maxWait;
    private final String validationQuery;
    private final boolean testOnBorrow;
    private final boolean spy;

    public DbProperties(String url, String username, String password, int initialSize, int maxTotal, int maxIdle, int minIdle, int maxWait, String validationQuery, boolean testOnBorrow, boolean spy) {
        //생성자를 구현하세요.

        this.url = null;
        this.username = null;
        this.password = null;
        this.initialSize = 0;
        this.maxTotal = 0;
        this.maxIdle = 0;
        this.minIdle = 0;
        this.maxWait = 0;
        this.validationQuery = "";
        this.testOnBorrow = false;
        this.spy = false;
    }

    /*Getter method를 작성합니다.Setter 메서드는 작성하지 않습니다.
     - 예시)url에 대한 Getter 메서드 예시 입니다.
    */
    public String getUrl() {
        return url;
    }


    //ToString() method를 작성합니다.

}
