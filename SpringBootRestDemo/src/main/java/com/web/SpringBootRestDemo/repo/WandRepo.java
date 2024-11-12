package com.web.SpringBootRestDemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.SpringBootRestDemo.entity.Wand;

public interface WandRepo extends JpaRepository<Wand, Integer>
{
}
