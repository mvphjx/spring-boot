package com.han.jpa.repository;

import com.han.jpa.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>
{
    /**
     * 通过uuid查询用户
     */
    Team findByUuid(String uuid);
}
