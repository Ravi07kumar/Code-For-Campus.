package com.example.jfis.savethetime;

public class Room {

    private String roomId ;
    private String roomType;
    private String department;

    public Room(){}

    public Room(String roomId, String roomType, String department) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.department = department;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
