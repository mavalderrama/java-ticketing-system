package manglar.soporte.services;

import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import manglar.soporte.repositories.TicketDAO;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TicketsDAOService implements TicketsService{

    private TicketDAO memory;

    public TicketsDAOService(TicketDAO memory){
        this.memory = memory;
        int i = 1;
    }

    @Override
    public Ticket report(Ticket ticket) {
        return memory.create(ticket);
    }

    @Override
    public Ticket get(Long ticketId)
    {
        return memory.getOne(ticketId);
    }

    @Override
    public Optional<Ticket> find(Long ticketId) {
       return memory.findOne(ticketId);
    }

    @Override
    public List<Ticket> all() {
        return memory.list();
    }

    @Override
    public Ticket modify(Ticket ticket) {
        return memory.update(ticket);
    }

    @Override
    public Ticket close(Long ticketId, Resolution resolution) {
        TicketValue.TicketValueBuilder builder = TicketValue.from(get(ticketId)).toBuilder();
        Ticket ticket = builder
                .status(TicketStatus.CLOSED)
                .closedAs(resolution)
                .closingDate(LocalDateTime.now())
                .build();
        return modify(ticket);
    }

    @Override
    public Ticket close(Long ticketId, Resolution resolution, String solutionText) {
        TicketValue.TicketValueBuilder builder = TicketValue.from(get(ticketId)).toBuilder();
        Ticket ticket = builder
                .solution(solutionText)
                .status(TicketStatus.CLOSED)
                .closedAs(resolution)
                .closingDate(LocalDateTime.now())
                .build();
        return modify(ticket);
    }
}