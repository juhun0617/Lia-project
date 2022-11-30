package com.attend.liaproject.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class AdminRepository {

    private static final Map<Long, Admin> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Admin save(Admin admin) {
        admin.setId(++sequence);
        log.info("save : admin={}", admin);
        store.put(admin.getId(), admin);
        return admin;
    }

    public Admin findById(Long id) {
        return store.get(id);
    }

    public Optional<Admin> findByLoginId(String loginid) {
        return this.findAll()
                .stream()
                .filter(m -> m.getLoginId()
                        .equals(loginid))
                .findFirst();
    }

    public List<Admin> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    @PostConstruct
    public void init() {
        Admin admin = new Admin();
        admin.setLoginId("admin");
        admin.setPassword("admin");
        admin.setName("박주훈");
        save(admin);
    }
}
