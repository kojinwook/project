package com.example.project.football.inquiry;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;
@Getter
@Setter
public class InquiryForm {

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "문의하시는 분의 전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
            message = "유효한 전화번호 형식(예: 010-1234-5678)을 입력해주세요.")
    private String phone;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String subject;

    @NotEmpty(message = "문의하실 내용을 입력해주세요.")
    @Size(max = 200, message = "내용은 200글자 이내로 작성해주세요.")
    private String content;

}
