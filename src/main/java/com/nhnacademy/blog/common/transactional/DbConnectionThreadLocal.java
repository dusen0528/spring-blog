package com.nhnacademy.blog.common.transactional;

import com.nhnacademy.blog.common.db.DbUtils;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
public class DbConnectionThreadLocal {
    // 각 스레드별로 데이터베이스 연결을 관리하기 위한 ThreadLocal 변수
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    // 각 스레드에서 SQL 에러 여부를 추적하기 위한 ThreadLocal 변수, 기본값은 false
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(() -> false);

    // DbConnectionThreadLocal 클래스의 인스턴스 생성을 방지하는 private 생성자
    private DbConnectionThreadLocal() {
        throw new IllegalStateException();
    }

    /**
     * 현재 스레드에 대한 데이터베이스 연결을 초기화합니다.
     * - 연결을 connection pool에서 가져오고,
     * - 트랜잭션 격리 수준을 READ_COMMITTED로 설정하며,
     * - auto-commit을 false로 설정하여 수동으로 트랜잭션을 관리합니다.
     */
    public static synchronized void initialize() {
        try {
            // connection pool에서 연결을 가져옵니다.
            Connection connection = DbUtils.getDataSource().getConnection();

            // 트랜잭션 격리 수준을 READ_COMMITTED로 설정합니다.
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // auto-commit을 false로 설정하여 트랜잭션을 수동으로 관리합니다.
            connection.setAutoCommit(false);

            // 해당 연결을 현재 스레드에 할당된 ThreadLocal에 저장합니다.
            connectionThreadLocal.set(connection);
        } catch (SQLException e) {
            // SQLException 발생 시 DatabaseException으로 감싸서 던집니다.
            throw new DatabaseException(e);
        }
    }

    /**
     * 현재 스레드의 데이터베이스 연결을 반환합니다.
     * @return 현재 스레드에 할당된 Connection 객체
     */
    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    /**
     * 현재 스레드에 SQL 오류 여부를 설정합니다.
     * @param sqlError SQL 오류가 발생했으면 true, 그렇지 않으면 false
     */
    public static void setSqlError(boolean sqlError) {
        sqlErrorThreadLocal.set(sqlError);
    }

    /**
     * 현재 스레드의 SQL 오류 여부를 반환합니다.
     * @return 현재 스레드에서 SQL 오류가 발생했으면 true, 아니면 false
     */
    public static boolean getSqlError() {
        return sqlErrorThreadLocal.get();
    }

    /**
     * 현재 트랜잭션을 마무리하고, 커밋 또는 롤백을 수행합니다.
     * - SQL 오류가 있으면 롤백하고, 없으면 커밋을 수행합니다.
     * - 트랜잭션을 종료한 후 연결을 닫고, ThreadLocal을 초기화합니다.
     */
    public static void reset() {
        try {
            // 현재 스레드의 연결을 가져옵니다.
            Connection connection = getConnection();

            // 연결이 null이 아니면 트랜잭션을 마무리합니다.
            if (Objects.nonNull(connection)) {
                // SQL 오류가 발생했으면 롤백을 수행합니다.
                if (getSqlError()) {
                    connection.rollback();
                } else {
                    // SQL 오류가 없으면 커밋을 수행합니다.
                    connection.commit();
                }
            }

            // 연결을 닫고 connection pool로 반환합니다.
            connection.close();
        } catch (SQLException e) {
            // 연결 종료 중 에러 발생 시 로깅 후 DatabaseException 던짐
            log.error("Error occurred during connection closing");
            throw new DatabaseException(e);
        } finally {
            // ThreadLocal에서 현재 스레드의 연결과 오류 상태를 제거합니다.
            connectionThreadLocal.remove();
            sqlErrorThreadLocal.remove();
        }
    }
}
