package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByAuthority(String authority);
}
