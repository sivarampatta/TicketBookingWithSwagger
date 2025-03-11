package com.siva.service;

import org.springframework.stereotype.Service;

import com.siva.binding.Passenger;
import com.siva.binding.TicketInformation;

@Service

public interface TicketService {
	
	public TicketInformation bookTicket(Passenger passenger);
	public TicketInformation getTrainInformation(String pnr);
    public TicketInformation updateTicket(String pnr, Passenger passenger);
	public boolean cancelTicket(String pnr);

}
