package com.example.userrailservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainDetails {


    private Long tId;

    private String train_id;
    private String train_name;
    private String departure_station;
    private String arrival_station;
    private String departure_time;
    private String arrival_time;
    private Double general_fare;
    private Double ladies_fare;
    private Number total_seats;
    private Number seats_left;
    private Boolean status;

}
