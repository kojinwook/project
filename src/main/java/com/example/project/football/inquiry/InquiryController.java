package com.example.project.football.inquiry;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping("/submit")
    public String showInquiryForm(Model model){
        model.addAttribute("inquiry",new Inquiry());
        return "inquiry";
    }


    @PostMapping("/submit")
    public String submitInquiry(@Valid Inquiry inquiry,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "inquiry";
        }
        inquiryService.save(inquiry);
        return "success";
    }




}
