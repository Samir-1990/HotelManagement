package az.dev.service;

import az.dev.request.ReqBooking;
import az.dev.response.RespStatus;

public interface BookingService {

    RespStatus createBooking(ReqBooking reqBooking);

    RespStatus customerExitHotel(Long bookingId);
}
