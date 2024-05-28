package com.example.project.football.inquiry;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public Inquiry save(Inquiry inquiry){
        return inquiryRepository.save(inquiry);
    }

}
