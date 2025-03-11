package com.siva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.binding.Passenger;
import com.siva.binding.TicketInformation;
import com.siva.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<TicketInformation> bookTicketFromController(@RequestBody Passenger passenger) {
		TicketInformation ticketInfo = ticketService.bookTicket(passenger);
		return new ResponseEntity<TicketInformation>(ticketInfo, HttpStatus.OK);
	}
	
	@GetMapping("/getTicket/{pnr}")
	public ResponseEntity<TicketInformation> getTicketDataFromRestController(@PathVariable String pnr){
		TicketInformation trainInformation = ticketService.getTrainInformation(pnr);
		return new ResponseEntity<TicketInformation>(trainInformation, HttpStatus.OK);
		
	}

	@PutMapping("/updateTicket/{pnr}")
	public ResponseEntity<TicketInformation> updateTicket(@PathVariable String pnr, @RequestBody Passenger passenger) {
		TicketInformation updatedTicket = ticketService.updateTicket(pnr, passenger);
		return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
	}

	@DeleteMapping("/cancelTicket/{pnr}")
	public ResponseEntity<String> cancelTicket(@PathVariable String pnr) {
		boolean isDeleted = ticketService.cancelTicket(pnr);
		if (isDeleted) {
			return new ResponseEntity<>("Ticket with PNR " + pnr + " has been cancelled.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ticket not found with PNR " + pnr, HttpStatus.NOT_FOUND);
		}
	}
}
	

