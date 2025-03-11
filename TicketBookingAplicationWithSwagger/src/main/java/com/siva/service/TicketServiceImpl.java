package com.siva.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.siva.binding.Passenger;
import com.siva.binding.TicketInformation;
import com.siva.dao.PassengerEntity;
import com.siva.dao.PassengerRepo;
import com.siva.dao.TicketEntity;
import com.siva.dao.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final PassengerRepo passengerRepo;

    public TicketServiceImpl(TicketRepo ticketRepo, PassengerRepo passengerRepo) {
        this.ticketRepo = ticketRepo;
        this.passengerRepo = passengerRepo;
    }

    @Override
    public TicketInformation bookTicket(Passenger passenger) {
        String pnr = "";
        for (int i = 1; i <= 8; i++) {
            pnr = pnr + (int) (Math.random() * 10);
        }

        TicketInformation ticket = new TicketInformation();
        ticket.setPnr(pnr);
        ticket.setTicketPrice(1200.00);
        ticket.setTicketStatus("confirmed");
        
        ticket.setPassengerName(passenger.getPassengerName());
        ticket.setPassengerName(passenger.getPassengerName());
        ticket.setFromLoc(passenger.getFromLoc());
        ticket.setToLoc(passenger.getToLoc());
        ticket.setJourneyDate(LocalDate.now());
        ticket.setTrainNum(passenger.getTrainNum());
        ticket.setTrainName(passenger.getTrainName());
        ticket.setEmail(passenger.getEmail());

        PassengerEntity passengerEntity = new PassengerEntity();
        BeanUtils.copyProperties(passenger, passengerEntity);
        passengerRepo.save(passengerEntity);

        TicketEntity ticketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticket, ticketEntity);
        ticketRepo.save(ticketEntity);

        return ticket;
    }

    @Override
    public TicketInformation getTrainInformation(String pnr) {
        TicketEntity byPnr = ticketRepo.findByPnr(pnr);
        if (byPnr == null) {
            return null;
        }
        TicketInformation ticketInfo = new TicketInformation();
        BeanUtils.copyProperties(byPnr, ticketInfo);
        return ticketInfo;
    }

    
    @Override
    public TicketInformation updateTicket(String pnr, Passenger passenger) {
        TicketEntity ticketEntity = ticketRepo.findByPnr(pnr);
        if (ticketEntity == null) {
            return null; 
        }

        ticketEntity.setFromLoc(passenger.getFromLoc());
        ticketEntity.setToLoc(passenger.getToLoc());
        ticketEntity.setTrainNum(passenger.getTrainNum());

        ticketRepo.save(ticketEntity);

        TicketInformation updatedTicket = new TicketInformation();
        BeanUtils.copyProperties(ticketEntity, updatedTicket);
        return updatedTicket;
    }

    
    @Override
    public boolean cancelTicket(String pnr) {
        TicketEntity ticketEntity = ticketRepo.findByPnr(pnr);
        if (ticketEntity != null) {
            ticketRepo.delete(ticketEntity);
            return true;
        }
        return false;
    }

}