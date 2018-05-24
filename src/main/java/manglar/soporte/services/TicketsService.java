package manglar.soporte.services;

import java.util.List;
import java.util.Optional;
import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;

public interface TicketsService {

  Ticket report(Ticket ticket);

  Ticket get(Long ticketId);

  Optional<Ticket> find(Long ticketId);

  List<Ticket> all();

  Ticket modify(Ticket ticket);

  Ticket close(Long ticketId, Resolution resolution);

  Ticket close(Long ticketId, Resolution resolution, String solutionText);

}
