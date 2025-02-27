package az.dev.service.impl;

import az.dev.entity.Room;
import az.dev.entity.RoomType;
import az.dev.enums.EnumAvailableStatus;
import az.dev.enums.EnumRoomStatus;
import az.dev.repository.RoomDao;
import az.dev.request.ReqRoom;
import az.dev.response.RespRoomType;
import az.dev.response.RespStatus;
import az.dev.util.Utility;
import az.dev.exception.ExceptionConstants;
import az.dev.repository.RoomTypeDao;
import az.dev.response.RespRoom;
import az.dev.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    public RespRoom getRoomById(Long roomId) {

        RespRoom response = new RespRoom();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called getRoomById, roomId = " + roomId);

            if (roomId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Room room = roomDao.findByIdAndActive(roomId, EnumAvailableStatus.ACTIVE.getValue());

            if (room == null) {
                response.setStatus(new RespStatus(ExceptionConstants.ROOM_NOT_FOUND, "Room not found"));
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not found");
                return response;
            }

            RespRoomType respRoomType = new RespRoomType();
            respRoomType.setRoomTypeId(room.getRoomType().getId());
            respRoomType.setName(room.getRoomType().getName());
            respRoomType.setDescription(room.getRoomType().getDescription());

            response.setRoomId(roomId);
            response.setRoomNo(room.getRoomNo());
            response.setRespRoomType(respRoomType);
            response.setRoomStatus(room.getRoomStatus());
            response.setRoomFloor(room.getRoomFloor());
            response.setRoomPrice(room.getRoomPrice());
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus createRoom(ReqRoom reqRoom) {
        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called createRoom, reqRoom = " + reqRoom);

            Long roomTypeId = reqRoom.getRoomTypeId();
            String roomNo = reqRoom.getRoomNo();
            Integer roomFloor = reqRoom.getRoomFloor();
            Float roomPrice = reqRoom.getRoomPrice();

            if (roomTypeId == null || reqRoom.getRoomFloor() == null || (roomNo == null || roomNo.isEmpty())
                    || roomFloor == null || roomPrice == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            RoomType roomType = roomTypeDao.findByIdAndActive(roomTypeId, EnumAvailableStatus.ACTIVE.getValue());
            if (roomType == null) {
                response.setStatusCode(ExceptionConstants.ROOM_TYPE_NOT_FOUND);
                response.setStatusMessage("Room type not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room type not found");
                return response;
            }

            Room room = new Room();
            room.setRoomNo(roomNo);
            room.setRoomType(roomType);
            room.setRoomFloor(roomFloor);
            room.setRoomPrice(roomPrice);
            roomDao.save(room);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus deleteRoom(Long roomId) {
        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called deleteRoom, roomId = " + roomId);

            if (roomId == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Room room = roomDao.findByIdAndActive(roomId, EnumAvailableStatus.ACTIVE.getValue());
            if (room == null) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_FOUND);
                response.setStatusMessage("Room not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not found");
                return response;
            }

            if (!room.getRoomStatus().equals(EnumRoomStatus.EMPTY.getValue())) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_EMPTY);
                response.setStatusMessage("Room not empty");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not empty");
                return response;
            }

            room.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            roomDao.save(room);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }
}
