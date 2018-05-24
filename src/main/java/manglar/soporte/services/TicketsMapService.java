package manglar.soporte.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;

public class TicketsMapService implements TicketsService {

  private Map<Long, Ticket> persistence;

  public TicketsMapService() {
    this.persistence = new HashMap<>();
  }

  @Override
  public Ticket report(Ticket ticket) {
    return persistence.put(ticket.getId(), ticket);
  }

  @Override
  public Ticket get(Long ticketId) {
    Ticket ticket = persistence.get(ticketId);
    if (ticket != null) {
      return ticket;
    }
    throw new NoSuchElementException();
  }

  @Override
  public Optional<Ticket> find(Long ticketId) {
    return Optional.ofNullable(persistence.get(ticketId));
  }

  @Override
  public List<Ticket> all() {
    List<Ticket> tickets = new ArrayList<>();
    persistence.forEach((k, v) -> tickets.add(v));
    return tickets;
  }

  @Override
  public Ticket modify(Ticket ticket) {
    persistence.replace(ticket.getId(), ticket);
    return get(ticket.getId());
  }

  @Override
  public Ticket close(Long ticketId, Resolution resolution) {
    TicketValue.TicketValueBuilder builder = TicketValue.from(get(ticketId)).toBuilder();
    Ticket ticket = builder
        .status(TicketStatus.CLOSED)
        .closedAs(resolution)
        .closingDate(LocalDateTime.now())
        .build();
    if (ticket.getClosedAs().equals(Resolution.SOLVED) && !ticket.getSolution().isPresent()) {
      throw new IllegalStateException("Si el ticket se cierra SOLVED debe tener una solución asignada");
    }
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
