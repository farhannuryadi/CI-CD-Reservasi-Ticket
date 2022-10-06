package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.response.SeatAvailabelResponse;
import com.farhan.bioskopapi.entity.SeatEntity;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.OrderDetailService;
import com.farhan.bioskopapi.service.OrderService;
import com.farhan.bioskopapi.service.ScheduleService;
import com.farhan.bioskopapi.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bioskop/api/orders")
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
}
