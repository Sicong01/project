package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto getRoleByName(String name);

    List<RoleDto> getAllRoles();
}
