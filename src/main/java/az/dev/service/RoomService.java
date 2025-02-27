package az.dev.service;

import az.dev.request.ReqRoom;
import az.dev.response.RespStatus;
import az.dev.response.RespRoom;

public interface RoomService {

    RespRoom getRoomById(Long roomId);

    RespStatus createRoom(ReqRoom reqRoom);

    RespStatus deleteRoom(Long roomId);
}
