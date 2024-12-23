package com.nhnacademy.blog.member.repository.impl;

import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.member.domain.Member;
import com.nhnacademy.blog.member.dto.MemberUpdateRequest;
import com.nhnacademy.blog.member.repository.MemberRepository;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Repository("jdbcMemberRepository") // @Repository annotation을 추가
public class JdbcMemberRepository implements MemberRepository {

    public static final String BEAN_NAME = "jdbcMemberRepository";

    @Override
    public void save(Member member) {
        // TODO#1-3: 회원을 데이터베이스에 저장하는 기능을 구현하세요.
        // DbConnectionThreadLocal.getConnection()을 통해 DB 연결을 가져옵니다.
        Connection connection = DbConnectionThreadLocal.getConnection();

        // SQL 쿼리 문자열 정의: 회원 정보 (mb_email, mb_name, mb_password, mb_mobile, created_at)를 'members' 테이블에 삽입합니다.
        String sql = """
                insert into members (
                    mb_email,
                    mb_name,
                    mb_password,
                    mb_mobile,
                    created_at
                ) 
                values(?,?,?,?,?);
            """;

        // PreparedStatement를 사용하여 SQL 쿼리 실행 준비: Statement.RETURN_GENERATED_KEYS를 사용하여 자동 생성된 키 (mb_no)를 반환받습니다.
        try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // PreparedStatement에 회원 정보 설정
            int index = 1;
            psmt.setString(index++, member.getMbEmail()); // 이메일 설정
            psmt.setString(index++, member.getMbName()); // 이름 설정
            psmt.setString(index++, member.getMbPassword()); // 비밀번호 설정
            psmt.setString(index++, member.getMbMobile()); // 모바일 설정
            psmt.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now())); // 현재 시간 설정 (회원 가입 시간)

            // SQL 쿼리 실행 및 변경된 행 수 반환
            int rows = psmt.executeUpdate();

            // 행이 하나 이상 변경되었을 경우 (즉, 회원이 정상적으로 등록된 경우)
            if (rows > 0) {
                // 자동 생성된 키 (회원 번호, mb_no)를 반환받아 member 객체에 설정
                try (ResultSet rs = psmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long mbNo = rs.getLong(1); // 첫 번째 컬럼에서 자동 생성된 mb_no 값을 가져옵니다.
                        log.debug("inserted member with mbNo={}", mbNo);
                        // ReflectionUtils를 사용하여 불변 객체인 member의 mbNo 필드에 값을 강제로 설정
                        ReflectionUtils.setField(member, "mbNo", mbNo);
                    }
                }
            }
        } catch (SQLException e) {
            // SQLException 발생 시 DatabaseException을 던져서 데이터베이스 관련 오류 처리
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(MemberUpdateRequest memberUpdateRequest) {
        // TODO#1-4: 회원 정보를 업데이트하는 기능을 구현하세요.
    }

    @Override
    public void deleteByMbNo(long mbNo) {
        // TODO#1-5: mbNo를 통해 회원을 삭제하는 기능을 구현하세요.
    }

    @Override
    public void updatePassword(long mbNo, String mbPassword) {
        // TODO#1-6: 회원의 비밀번호를 업데이트하는 기능을 구현하세요.
    }

    @Override
    public Optional<Member> findByMbNo(long mbNo) {
        // TODO#1-7: mbNo로 회원을 조회하는 기능을 구현하세요.
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByMbEmail(String mbEmail) {
        // TODO#1-8: mbEmail로 회원을 조회하는 기능을 구현하세요.
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByMbMobile(String mBMobile) {
        // TODO#1-9: mbMobile로 회원을 조회하는 기능을 구현하세요.
        return Optional.empty();
    }

    @Override
    public boolean existsByMbNo(long mbNo) {
        // TODO#1-10: mbNo로 회원 존재 여부를 확인하는 기능을 구현하세요.
        return false;
    }

    @Override
    public boolean existsByMbEmail(String mbEmail) {
        // TODO#1-11: mbEmail로 회원 존재 여부를 확인하는 기능을 구현하세요.
        return false;
    }

    @Override
    public boolean existsByMbMobile(String mbMobile) {
        // TODO#1-12: mbMobile로 회원 존재 여부를 확인하는 기능을 구현하세요.
        return false;
    }

    @Override
    public boolean isMemberWithdrawn(Long mbNo) {
        // TODO#1-13: 회원이 탈퇴했는지 여부를 확인하는 기능을 구현하세요.
        return false;
    }

    @Override
    public void updateWithdrawalAt(long mbNo, LocalDateTime updateWithdrawalAt) {
        // TODO#1-14: 회원 탈퇴 시간을 업데이트하는 기능을 구현하세요.
    }
}
