package com.example.searchteam.service.domain.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.applicant.ApplicantMapper;
import com.example.searchteam.mapper.applicant.ApplicantMerger;
import com.example.searchteam.mapper.applicant.ApplicantResponseMapper;
import com.example.searchteam.repository.applicant.ApplicantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class ApplicantDomainServiceTest {

        private static final Long ID = 0L;
        private static final String NAME = "NAME";
        private static final String DESCRIPTION = "DESCRIPTION";
        private static final UserResponse USER=null;
        private static final TypeTeamResponse TYPE_TEAM=null;
        private static final TeamMemberTypeResponse TEAM_MEMBER_TYPE=null;

        private static final LocalDateTime CREATED = LocalDateTime.now();
        private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


        @Mock
        private ApplicantRepository repository;
        @Mock
        private ApplicantResponseMapper responseMapper;
        @Mock
        private ApplicantMapper mapper;
        @Mock
        private ApplicantMerger merger;

        @InjectMocks
        private ApplicantDomainService domainService;

        @Test
        void getApplicantByIdTest() {
            when(repository.findById(any())).thenReturn(Optional.ofNullable(getApplicant()));
            when(responseMapper.from((Applicant)any())).thenReturn(getApplicantResponse());

            var result = domainService.getApplicantById(any());

            assertEquals(ID, result.getApplicantId());
            assertEquals(NAME, result.getName());

            assertEquals(DESCRIPTION, result.getDescription());
            assertEquals(CREATED, result.getCreated());
            assertEquals(MODIFIED, result.getModified());

            verify(repository).findById(any());
            verify(responseMapper).from((Applicant)any());
            verifyNoMoreInteractions(repository);
            verifyNoMoreInteractions(responseMapper);
        }


        @Test
        void getAllApplicantsTest() {
            when(repository.findAll()).thenReturn(List.of(getApplicant(),getApplicant(),getApplicant()));
            var result = domainService.getAllApplicants();
            for(ApplicantResponse e:result) {
                assertEquals(ID, e.getApplicantId());
                assertEquals(NAME, e.getName());

                assertEquals(DESCRIPTION, e.getDescription());
                assertEquals(CREATED, e.getCreated());
                assertEquals(MODIFIED, e.getModified());
            }
            verify(repository).findAll();
            verifyNoMoreInteractions(repository);
        }

        @Test
        void addApplicantTest() {

            when(repository.save(any())).thenReturn(getApplicant());
            //when(repository.addApplicant(any())).thenReturn(ID);

            var result = domainService.addApplicant(getApplicantAddRequest());

            assertEquals(ID, result);

            //verify(domainService).getApplicantById(any());
            verify(repository).save(any());
            verifyNoMoreInteractions(repository);

        }


        @Test
        void editApplicantTest() {
            when(repository.save(any())).thenReturn(getApplicant());
            when(repository.getReferenceById(any())).thenReturn(getApplicant());

            var result = domainService.editApplicant(getApplicantAddRequest());

            assertEquals(ID, result);

            verify(repository).save(any());
            verify(repository).getReferenceById(any());
            verifyNoMoreInteractions(repository);
        }

        @Test
        void deleteApplicantTest() {
            domainService.deleteApplicantById(ID);
            verify(repository).deleteById(any());
            verifyNoMoreInteractions(repository);
        }


        private ApplicantRequest getApplicantRequest() {
            return new ApplicantRequest()
                    .setApplicantId(ID);
        }

        private Applicant getApplicant() {
            return new Applicant()
                    .setId(ID)
                    .setName(NAME)
                    .setDescription(DESCRIPTION)
                    .setCreatedDateTime(CREATED)
                    .setModifiedDateTime(MODIFIED);

        }

        private ApplicantResponse getApplicantResponse() {
            return new ApplicantResponse()
                    .setApplicantId(ID)
                    .setName(NAME)
                    .setTypeTeam(TYPE_TEAM)
                    .setUser(USER)
                    .setTeamMemberType(TEAM_MEMBER_TYPE)
                    .setDescription(DESCRIPTION)
                    .setCreated(CREATED)
                    .setModified(MODIFIED);

        }

        private ApplicantAddRequest getApplicantAddRequest() {
            return new ApplicantAddRequest()
                    .setId(ID)
                    .setName(NAME)
                    .setDescription(DESCRIPTION)
                    .setId(ID);
        }



    }
