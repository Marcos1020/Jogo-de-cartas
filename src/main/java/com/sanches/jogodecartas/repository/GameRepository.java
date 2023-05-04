package com.sanches.jogodecartas.repository;

import com.sanches.jogodecartas.entity.EntityGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<EntityGame, Long> {
}
