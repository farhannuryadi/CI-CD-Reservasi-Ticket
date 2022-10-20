package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.request.OrderRequest;
import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.response.SeatAvailabelResponse;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.SeatEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.OrderDetailService;
import com.farhan.bioskopapi.service.OrderService;
import com.farhan.bioskopapi.service.ScheduleService;
import com.farhan.bioskopapi.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bioskop/api/orders")
@Tag(name = "Order", description = "Everything about order")
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

    @Operation(summary = "Get all seats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SeatEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/seat")
    public ResponseEntity<ResponseData<List<SeatEntity>>> seat(){
        ResponseData<List<SeatEntity>> responseData = new ResponseData<>();

        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add("sukses");
            responseData.setData(seatService.findAll());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            responseData.setData(seatService.findAll());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @Operation(summary = "Get all seat is available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SeatAvailabelResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/seat/available/{scheduleId}")
    public ResponseEntity<ResponseData<SeatAvailabelResponse>> seatAvailable(@PathVariable("scheduleId") Long scheduleId){
        ResponseData<SeatAvailabelResponse> responseData= new ResponseData<>();
        try {
            SeatAvailabelResponse seatAvailabelResponse = new SeatAvailabelResponse();
            List<String> list =  seatService.findSeatAvailable(scheduleId);
            seatAvailabelResponse.setSeatName(list);
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.setMessages(List.of("sukses"));
            responseData.setData(seatAvailabelResponse);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Add a new order by username and scheduleId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping("create/{username}/{scheduleId}")
    public ResponseEntity<ResponseData> createOrder(@PathVariable("username") String username,
                                                    @PathVariable("scheduleId") Long scheduleId,
                                                    @RequestBody OrderRequest orderRequest){

        ResponseData responseData = new ResponseData();
        List<String> seats = new ArrayList<>(orderRequest.getSeatName());

        try {
            orderService.createOrder(scheduleId, username, seats);
            orderDetailService.createOrderDetail(seats, scheduleId, username);

            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add("sukses");
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
