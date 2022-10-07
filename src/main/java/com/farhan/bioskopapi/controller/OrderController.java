package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.request.OrderRequest;
import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.response.SeatAvailabelResponse;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.SeatEntity;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.OrderDetailService;
import com.farhan.bioskopapi.service.OrderService;
import com.farhan.bioskopapi.service.ScheduleService;
import com.farhan.bioskopapi.service.SeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bioskop/api/orders")
@Tag(name = "Order")
public class OrderController {

    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private SeatService seatService;
    private ScheduleService scheduleService;


    @Autowired
    public OrderController(OrderService orderService, OrderDetailService orderDetailService, SeatService seatService, ScheduleService scheduleService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.seatService = seatService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/seat")
    public List<SeatEntity> seat(){
        return seatService.findAll();
    }

    @GetMapping("/seat/available/{scheduleId}")
    public ResponseEntity<ResponseData<SeatAvailabelResponse>> seatAvailable(@PathVariable("scheduleId") Long scheduleId){
        ResponseData<SeatAvailabelResponse> responseData= new ResponseData<>();
        SeatAvailabelResponse seatAvailabelResponse = new SeatAvailabelResponse();
        List<String> list =  seatService.findSeatAvailable(scheduleId);
        seatAvailabelResponse.setSeatName(list);
        responseData.setStatusCode(StatusCode.OK);
        responseData.setStatus(true);
        responseData.setMessages(List.of("sukses"));
        responseData.setData(seatAvailabelResponse);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("create/{username}/{scheduleId}")
    public ResponseEntity<ResponseData> createOrder(
            @PathVariable("username") String username,
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody OrderRequest orderRequest){

        ResponseData responseData = new ResponseData();
        List<String> seats = new ArrayList<>(orderRequest.getSeatName());

        orderService.createOrder(scheduleId, username, seats);
        orderDetailService.createOrderDetail(seats, scheduleId, username);

        responseData.setStatusCode(StatusCode.OK);
        responseData.setStatus(true);
        responseData.getMessages().add("sukses");
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }
}
