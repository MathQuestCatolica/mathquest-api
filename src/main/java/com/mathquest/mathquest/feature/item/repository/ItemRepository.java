package com.mathquest.mathquest.feature.item.repository;

import com.mathquest.mathquest.feature.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
