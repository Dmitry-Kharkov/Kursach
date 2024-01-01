package com.example.searchteam.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serial;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class EntityWithName extends BaseEntity{

    @Serial
    private static final long serialVersionUID = 1L;

    @ToString.Include
    @Column(name = "NAME")
    protected String name;

    @ToString.Include
    @Column(name = "DESCRIPTION")
    protected String description;

}
