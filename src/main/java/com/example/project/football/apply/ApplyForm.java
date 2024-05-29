package com.example.project.football.apply;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyForm {

    @NotEmpty(message = "신청자 아이디를 입력해주세요.")
    private String loginId;
    @NotEmpty(message = "신청자의 이름 / 성별 / 주 포지션을 입력해주세요.")
    private String content;
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotEmpty(message = "신청 하시는분의 전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
            message = "유효한 전화번호 형식(예: 010-1234-5678)을 입력해주세요.")
    private String phone;





}
