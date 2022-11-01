package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;

}
