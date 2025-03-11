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
public class TicketInformation {
	
	private String fromLoc;
	private String toLoc;
	private String passengerName;
	private LocalDate journeyDate;
	private Long trainNum;
	private String pnr;
	private String email;
	private String trainName;
	private String ticketStatus;
	private Double ticketPrice;

}
