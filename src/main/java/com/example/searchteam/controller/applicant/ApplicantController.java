package com.example.searchteam.controller.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantFiltrationRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.request.team.TeamFiltrationRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.applicant.ShortApplicantResponse;
import com.example.searchteam.dto.response.team.ShortTeamResponse;
import com.example.searchteam.service.applicant.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class ApplicantController {

    public static final String APPLICANT_GET_BY_ID = "/api/v1/applicant/get-by-id";
    public static final String APPLICANT_ADD = "/api/v1/applicant/add";
    public static final String APPLICANT_EDIT = "/api/v1/applicant/edit";
    public static final String APPLICANT_DELETE = "/api/v1/applicant/delete";
    public static final String APPLICANT_GET_ALL = "/api/v1/applicant/get-all";
    public static final String APPLICANT_SEARCH="/api/v1/applicant/search";

    private final ApplicantService service;

    @PostMapping(
            value = APPLICANT_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ApplicantResponse getApplicantById(@RequestBody ApplicantRequest request) {
        return service.getApplicantById(request);
    }


    @PostMapping(
            value = APPLICANT_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ApplicantResponse addApplicant(@RequestBody ApplicantAddRequest request) {
        return service.addApplicant(request);
    }

    @PostMapping(
            value = APPLICANT_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ApplicantResponse editApplicant(@RequestBody ApplicantAddRequest request) {
        return service.editApplicant(request);
    }

    @PostMapping(
            value = APPLICANT_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<ApplicantResponse> getAllApplicants(){ return service.getAllApplicants(); }

    @PostMapping(
            value = APPLICANT_DELETE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void deleteApplicant(@RequestBody ApplicantRequest request) {
        service.deleteApplicant(request);
    }

    @PostMapping(
            value = APPLICANT_SEARCH,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<ShortApplicantResponse> getSearchApplicants(@RequestBody ApplicantFiltrationRequest request){
        return service.getSearchApplicants(request);
    }

}