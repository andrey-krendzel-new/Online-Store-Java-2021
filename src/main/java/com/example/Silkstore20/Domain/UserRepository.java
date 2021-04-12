package com.example.Silkstore20.Domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usero, Long> {
    Usero findByUsername(String username);
}
