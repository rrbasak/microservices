package com.rajdeep.restaurentlisting.repo;

import com.rajdeep.restaurentlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
}
