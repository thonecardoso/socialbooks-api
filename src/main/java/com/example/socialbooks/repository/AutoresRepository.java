package com.example.socialbooks.repository;

import com.example.socialbooks.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoresRepository extends JpaRepository<Autor, Long> {
}
