package com.security.app.repository;

import com.security.app.entities.RoleEntity;
import com.security.app.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findByRoleEnumIn(List<RoleEnum> roleNames);

}
