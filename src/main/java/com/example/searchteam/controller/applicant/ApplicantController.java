package com.example.searchteam.controller.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.service.applicant.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class ApplicantController {

    public static final String APPLICANT_GET_BY_ID = "/api/v1/applicant/get-by-id";

    private final ApplicantService service;

    @PostMapping(
            value = APPLICANT_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ApplicantResponse getApplicantById(@RequestBody ApplicantRequest request){
        return service.getApplicantById(request);
    }


}