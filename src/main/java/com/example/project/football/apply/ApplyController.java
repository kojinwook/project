package com.example.project.football.apply;




@Controller
@RequiredArgsConstructor

public class ApplyController {
    private final ApplyService applyService;

    @GetMapping("/application")
    public String showApplyForm(Model model){
        model.addAttribute("apply", new Apply());
        return "apply";
    }


    @PostMapping("/application")
    public String submitApply(@Valid Apply apply,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "apply";
        }
        applyService.save(apply);
        return "apply_success";
    }












}
