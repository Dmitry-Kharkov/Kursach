package com.example.searchteam.domain.applicant;

import com.example.searchteam.domain.EntityWithName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity(name = "APPLICANT")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "APPLICANT_ID"))
public class Applicant extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="IS_COMPLETED")
    boolean isCompleted;

    public Applicant(Long applicantId) {
        this.id = applicantId;
    }


    @Override
    public Applicant setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Applicant setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Applicant setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Applicant setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Applicant setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
