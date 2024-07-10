package org.fullstackgroupproject.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class IdService {
    public String idGenerator(){
        return UUID.randomUUID().toString();
    }
}
