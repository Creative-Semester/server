package com.sejong.creativesemester.promise.service;

import com.sejong.creativesemester.common.format.exception.user.NotHaveRoleException;
import com.sejong.creativesemester.promise.entity.Promise;
import com.sejong.creativesemester.promise.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class PromiseService {
    private final PromiseRepository promiseRepository;
    public boolean implementPromise(Authentication authentication, Long promiseId){
        if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_USER"))){
            throw new NotHaveRoleException();
        }
        Promise findPromise = promiseRepository.findById(promiseId).orElseThrow(IllegalAccessError::new);
        return findPromise.implementPromise();
    }
}
