package com.example.project.note.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Size(min = 3, max = 10)
    private String loginId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "비밀번호는 영어와 숫자를 합쳐서 8글자 이상이어야 합니다."
    )
    private String password;
    @NotEmpty(message = "비밀번호를 확인해주세요.")
    private String password2;
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}