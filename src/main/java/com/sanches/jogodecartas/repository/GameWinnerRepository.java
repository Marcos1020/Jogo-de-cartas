package com.sanches.jogodecartas.repository;

import com.sanches.jogodecartas.entity.EntityWinnerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameWinnerRepository extends JpaRepository<EntityWinnerGame, Long> {
}
