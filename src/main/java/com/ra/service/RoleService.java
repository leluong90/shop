package com.ra.service;

import com.ra.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

public interface RoleService {

    Role findByRoleName(String roleName);
}
