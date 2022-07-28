package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
