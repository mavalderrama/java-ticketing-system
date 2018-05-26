package manglar.soporte.repositories;

import manglar.soporte.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface TicketDAO {
    Ticket create(Ticket ticket);

    Ticket getOne(long id);

    Optional<Ticket> findOne(long id);

    //(Predicate) retorna los elementos en los que el predicado es verdadero
    List<Ticket> find(Predicate<Ticket> predicate);

    List<Ticket> list();

    Ticket update(Ticket ticket);

    Ticket delete(long id);
}
