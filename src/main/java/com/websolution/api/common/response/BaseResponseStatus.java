package com.websolution.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {
    // ✅ 성공 응답
    SUCCESS(true, 200, "요청이 성공적으로 처리되었습니다."),

    // ✅ 공통 실패 응답
    BAD_REQUEST(false, 400, "잘못된 요청입니다."), UNAUTHORIZED(false, 400, "인증되지 않은 사용자입니다."),
    FORBIDDEN(false, 400, "접근이 거부되었습니다."), NOT_FOUND(false, 400, "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(false, 500, "서버 내부 오류가 발생하였습니다."),

    // ✅ 로그인 관련 응답
    LOGIN_FAILED(false, 400, "잘못된 아이디 혹은 비밀번호입니다."), LOGIN_PENDING(false, 400, "관리자 승인 대기 중인 사용자입니다."),

    // ✅ 회원가입 관련 응답
    DUPLICATE_LOGIN_ID(false, 400, "이미 존재하는 로그인 ID입니다."), DUPLICATE_EMAIL(false, 400, "이미 사용 중인 이메일입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}