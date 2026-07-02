package com.rajdeep.order.repo;

import com.rajdeep.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface OrderRepo extends MongoRepository<Order,Integer> {

}
