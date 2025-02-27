package az.dev.controller;


import az.dev.request.ReqBooking;
import az.dev.response.RespStatus;
import az.dev.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/createBooking")
    RespStatus createBooking(@RequestBody ReqBooking reqBooking) {
        return bookingService.createBooking(reqBooking);
    }

    @PostMapping(value = "/customerExitHotel/{bookingId}")
    public RespStatus customerExitHotel(@PathVariable("bookingId") Long bookingId) {
        return bookingService.customerExitHotel(bookingId);
    }

}
