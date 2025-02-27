package az.dev.controller;


import az.dev.request.ReqRoom;
import az.dev.response.RespRoom;
import az.dev.response.RespStatus;
import az.dev.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/getRoomById/{roomId}")
    public RespRoom getRoomById(@PathVariable("roomId") Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @PostMapping(value = "/createRoom")
    public RespStatus createRoom(@RequestBody ReqRoom reqRoom) {
        return roomService.createRoom(reqRoom);
    }

    @DeleteMapping(value = "/deleteRoom/{roomId}")
    public RespStatus deleteRoom(@PathVariable("roomId") Long roomId) {
        return roomService.deleteRoom(roomId);
    }

}
