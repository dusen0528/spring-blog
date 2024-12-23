package com.nhnacademy.blog.member.repository.impl;

import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.context.ContextHolder;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.member.domain.Member;
import com.nhnacademy.blog.member.dto.MemberUpdateRequest;
import com.nhnacademy.blog.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO#1-TEST 아래 테스트 코드를 작성하세요.
 */
@Slf4j
class JdbcMemberRepositoryTest {
    static MemberRepository memberRepository;

    @BeforeAll
    static void beforeAll() {
        Context context = ContextHolder.getApplicationContext();
        memberRepository = (MemberRepository) context.getBean(JdbcMemberRepository.BEAN_NAME);
    }

    @BeforeEach
    void setUp(){
        DbConnectionThreadLocal.initialize();
    }

    @AfterEach
    void tearDown(){
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("회원등록")
    void save() {

        // 새 회원 저장 시 데이터베이스에 회원 정보가 정상적으로 저장되는지 검증
        // 저장 후, 데이터베이스에서 회원 정보를 조회하여 저장된 데이터와 일치하는지 확인

        Member member1 = Member.ofNewMember("marco@nhnacademy.com","마르코","12345","01012345678");
        Member member2 = Member.ofNewMember("test@nhnacademy.com","테스트","12345","01011112222");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Optional<Member> actualOptionalMember1 = memberRepository.findByMbNo(member1.getMbNo());
        Optional<Member> actualOptionalMember2 = memberRepository.findByMbNo(member1.getMbNo());

        log.debug("member1: {}", actualOptionalMember1.get());
        log.debug("member2: {}", actualOptionalMember2.get());

        Assertions.assertAll(
                ()->Assertions.assertNotNull(actualOptionalMember1.get()),
                ()->Assertions.assertEquals(member1.getMbEmail(), actualOptionalMember1.get().getMbEmail()),
                ()->Assertions.assertEquals(member1.getMbName(), actualOptionalMember1.get().getMbName()),
                ()->Assertions.assertEquals(member1.getMbPassword(), actualOptionalMember1.get().getMbPassword()),
                ()->Assertions.assertEquals(member1.getMbMobile(), actualOptionalMember1.get().getMbMobile()),
                ()->Assertions.assertNotNull(actualOptionalMember1.get().getCreatedAt()),

                ()->Assertions.assertNotNull(actualOptionalMember2.get()),
                ()->Assertions.assertEquals(member1.getMbEmail(), actualOptionalMember2.get().getMbEmail()),
                ()->Assertions.assertEquals(member1.getMbName(), actualOptionalMember2.get().getMbName()),
                ()->Assertions.assertEquals(member1.getMbPassword(), actualOptionalMember2.get().getMbPassword()),
                ()->Assertions.assertEquals(member1.getMbMobile(), actualOptionalMember2.get().getMbMobile()),
                ()->Assertions.assertNotNull(actualOptionalMember2.get().getCreatedAt())
        );
    }

    @Test
    @DisplayName("회원정보 수정{이메일,이름,연락처}")
    void update() {
        // 회원 수정 시 수정된 정보(이메일, 이름, 연락처)가 데이터베이스에 반영되는지 확인

    }

    @Test
    @DisplayName("회원삭제")
    void delete() {
        // 회원 삭제 후 해당 회원 정보가 데이터베이스에서 삭제되었는지 검증
        // 삭제된 회원의 정보를 조회하여 데이터베이스에서 없는지 확인

    }

    @Test
    @DisplayName("비밀번호 변경")
    void updatePassword(){
        // 비밀번호 변경 후, 변경된 비밀번호가 데이터베이스에 반영되는지 확인

    }

    @Test
    @DisplayName("회원조회-by-회원번호")
    void findByMbNo() {
        // 회원번호로 조회한 회원의 정보가 데이터베이스에서 정확히 반환되는지 확인
    }

    @Test
    @DisplayName("회원조회-by-email")
    void findByMbEmail() {
        // 이메일로 조회한 회원의 정보가 데이터베이스에서 정확히 반환되는지 확인

    }

    @Test
    @DisplayName("회원조회-by-mobile")
    void findByMbMobile() {
        // 모바일 번호로 조회한 회원의 정보가 데이터베이스에서 정확히 반환되는지 확인

    }

    @Test
    @DisplayName("회원번호로 회원존재여부 : true")
    void existsByMbNo() {
        // 회원번호로 조회 시 해당 회원이 존재하는지 확인

    }

    @Test
    @DisplayName("회원번호로 회원존재여부 : false")
    void notExistsByMbNo() {
        // 존재하지 않는 회원번호로 조회 시 회원이 존재하지 않음을 확인

    }

    @Test
    @DisplayName("회원존재여부-by-email : true")
    void existsByMbEmail() {
        // 이메일로 조회 시 해당 회원이 존재하는지 확인

    }

    @Test
    @DisplayName("회원존재여부-by-email : false")
    void notExistsByMbEmail() {
        // 존재하지 않는 이메일로 조회 시 회원이 존재하지 않음을 검증

    }

    @Test
    @DisplayName("회원존재여부-by-mobile : true")
    void existsByMbMobile() {
        // 모바일 번호로 조회 시 해당 회원이 존재하는지 확인

    }

    @Test
    @DisplayName("회원존재여부-by-mobile : false")
    void notExistsByMbMobile() {
        // 존재하지 않는 모바일 번호로 조회 시 회원이 존재하지 않음을 검증

    }

    @Test
    @DisplayName("회원 탈퇴여부: true")
    void isMemberWithdrawn_true(){
        // 회원이 탈퇴 후, 탈퇴 상태가 정상적으로 반영되었는지 확인

    }

    @Test
    @DisplayName("회원 탈퇴여부: false")
    void isMemberWithdrawn_false(){
        // 탈퇴되지 않은 회원 상태가 정상적으로 반영되었는지 확인

    }

    @Test
    @DisplayName("회원탈퇴:탈퇴일자 수정")
    void updateWithdrawalAt() {
        // 탈퇴일자가 변경된 후, 변경된 일자가 데이터베이스에 정확히 반영되는지 확인

    }
}