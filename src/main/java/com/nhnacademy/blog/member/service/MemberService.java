package com.nhnacademy.blog.member.service;

import com.nhnacademy.blog.member.dto.*;

public interface MemberService {

    //회원(등록)
    MemberResponse registerMember(MemberRegisterRequest memberRegisterRequest);
    //회원정보(수정)
    MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest);
    //회원(탈퇴)
    void withdrawalMember(long mbNo);
    //회원조회
    MemberResponse getMember(long mbNo);
    //비밀번호 변경
    void changePassword(MemberPasswordUpdateRequest memberPasswordUpdateRequest);

    LoginMember doLogin(LoginRequest loginRequest);
}
