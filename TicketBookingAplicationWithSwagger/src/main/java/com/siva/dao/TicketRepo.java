package com.siva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<TicketEntity, Integer>{
	
	public TicketEntity findByPnr(String pnr);

}
