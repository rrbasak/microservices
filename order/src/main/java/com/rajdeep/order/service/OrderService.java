package com.rajdeep.order.service;


import com.rajdeep.order.dto.OrderDTO;
import com.rajdeep.order.dto.OrderDTOFromFE;
import com.rajdeep.order.dto.UserDTO;
import com.rajdeep.order.entity.Order;
import com.rajdeep.order.mapper.OrderMapper;
import com.rajdeep.order.repo.OrderRepo;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;


    @Autowired
    private org.springframework.core.env.Environment env;


    @PostConstruct
    public void checkDb() {
        System.out.println("=== NEW BUILD TEST ===");
        System.out.println("Mongo DB = " + mongoTemplate.getDb().getName());
        System.out.println("Mongo Property = " +
                env.getProperty("spring.data.mongodb.database"));
        System.out.println("Mongo URI = " + env.getProperty("spring.data.mongodb.uri"));
    }




    public OrderDTO saveOrderInDB(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID,orderDetails.getFoodItemsDTOList(),orderDetails.getRestaurantDTO(),userDTO);
        orderRepo.save(orderToBeSaved);
        System.out.println("Saved Order = " + orderToBeSaved);

        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUser/"+userId, UserDTO.class);
    }
}
