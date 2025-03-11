package com.siva.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passenger {
	
	private String passengerName;
	private String fromLoc;
	private String toLoc;
	private LocalDate journeyDate;
	private String email;
	private Long trainNum;
	private String trainName;
	
	

}