package com.attend.liaproject.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AdminRepository adminRepository;

    public Admin login(String loginId, String password) {
        return adminRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword()
                        .equals(password))
                .orElse(null);
    }
}
