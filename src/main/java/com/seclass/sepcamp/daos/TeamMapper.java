package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMapper {

    Team GetOneTeam(int teamId);


}
