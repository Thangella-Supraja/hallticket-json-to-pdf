package com.example.hallticket.model;


import lombok.Data;

@Data
public class HallTicketRequest {
    private String studentId;
    private String studentName;
    private String branch;
    private String examCenter;
}
