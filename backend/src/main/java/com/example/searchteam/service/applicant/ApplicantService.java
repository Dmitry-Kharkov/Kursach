package com.example.searchteam.service.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantFiltrationRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.applicant.ShortApplicantResponse;
import com.example.searchteam.service.domain.applicant.ApplicantDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
/**
 * Сервис заявителя
 * Реализует методы обработки информации о заявителе
 */
public class ApplicantService {

    /**
     * Domain Service заявителя
     * Реализует методы обработки информации о заявителе
     */
    private final ApplicantDomainService service;

    /**
     * получение заявителя по id
     * @param request - id
     * @return заявитель по id
     */
    public ApplicantResponse getApplicantById(ApplicantRequest request) {
        return service.getApplicantById(request.getApplicantId());
    }

    /**
     * получение всех заявок
     * @return список заявителей
     */
    public List<ApplicantResponse> getAllApplicants() {
        return service.getAllApplicants();
    }

    /**
     * Создание новой заявки
     * @param request - ApplicantAddRequest(id,name,description,userId,teamTypeId,teamMemberTypeId)
     * @return заявитель
     */
    public ApplicantResponse addApplicant(ApplicantAddRequest request) {
        Long applicantId = service.addApplicant(request);
        return service.getApplicantById(applicantId);
    }

    /**
     * Изменение заявки
     * @param request - ApplicantAddRequest(id,name,description,userId,teamTypeId,teamMemberTypeId)
     * @return заявитель
     */
    public ApplicantResponse editApplicant(ApplicantAddRequest request) {
        Long applicantId = service.editApplicant(request);
        return service.getApplicantById(applicantId);
    }

    /**
     * Удаление заявки
     * @param request - ApplicantRequest(applicantId)
     */
    public void deleteApplicant(ApplicantRequest request) {
        service.deleteApplicantById(request.getApplicantId());
    }

    /**
     * Поиск заявки
     * @param request - ApplicantFiltrationRequest(teamTypes,isCompleted,name,start,finish,users)
     * @return укороченный список заявителей
     */
    public List<ShortApplicantResponse> getSearchApplicants(ApplicantFiltrationRequest request) {
        return service.getAllApplicants().stream()
                .filter(t -> applyFilter(t, request))
                .map(t -> new ShortApplicantResponse(t.getApplicantId(), t.getName(), t.getUser()))
                .toList();

    }


    /**
     * Фильтр для поиска заявки
     * @param source - ApplicantResponse(applicantId,name,description,user,isComleted,typeTeam,teamMemberType), ApplicantFiltrationRequest(teamTypes,isCompleted,name,start,finish,users)
     * @return булевое значение
     */
    private boolean applyFilter(ApplicantResponse source, ApplicantFiltrationRequest filter) {

        if (filter.getStart() == null && filter.getFinish() == null && filter.getName() == null
                && filter.getIsCompleted() == null && filter.getTeamTypes().isEmpty() && filter.getUsers().isEmpty()
        ) return true;


        if (filter.getStart() != null && (filter.getStart().isAfter(source.getCreated())))
            return false;

        if (filter.getFinish() != null && (filter.getFinish().isBefore(source.getCreated())))
            return false;

        if (filter.getIsCompleted() != null && !filter.getIsCompleted().equals(source.isCompleted()))
            return false;

        if (filter.getName() != null && !source.getName().toLowerCase().contains(filter.getName().toLowerCase())) {
            return false;
        }

        var userFilter = filter
                .getUsers()
                .stream()
                .noneMatch(u -> source.getUser().getFullName().toLowerCase().contains(u.toLowerCase()));


        if (!filter.getUsers().isEmpty() && userFilter) {
            return false;
        }

        var typeFilter = filter.getTeamTypes()
                .stream()
                .noneMatch(t -> source.getTypeTeam().getName().toLowerCase().contains(t.toLowerCase()));


        if (!filter.getTeamTypes().isEmpty() && typeFilter) {
            return false;
        }
        return true;
    }
}
