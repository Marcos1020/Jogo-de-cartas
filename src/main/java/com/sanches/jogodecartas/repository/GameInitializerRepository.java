package com.sanches.jogodecartas.repository;

import com.sanches.jogodecartas.entity.EntityInitializerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameInitializerRepository extends JpaRepository<EntityInitializerGame, Long> {
}
