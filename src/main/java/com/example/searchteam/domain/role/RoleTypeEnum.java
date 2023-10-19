package com.example.searchteam.domain.role;

public enum RoleTypeEnum {
    SYSTEM,
    TEAM;

    public static RoleTypeEnum getByOrdinal(long id) { return RoleTypeEnum.values()[(int)id];}

}
