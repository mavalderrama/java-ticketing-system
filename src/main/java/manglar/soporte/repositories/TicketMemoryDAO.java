package manglar.soporte.repositories;


import manglar.soporte.model.Ticket;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TicketMemoryDAO implements TicketDAO{

    private Map<Long,Ticket> persistence;

    public TicketMemoryDAO() {
        this.persistence = new HashMap<>();
    }

    @Override
    public Ticket create(Ticket ticket) {
        persistence.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Ticket getOne(long id) {
        Ticket ticket = persistence.get(id);
        if (ticket != null) {
            return ticket;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Ticket> findOne(long id) {
        return Optional.ofNullable(persistence.get(id));
    }

    @Override
    public List<Ticket> find(Predicate<Ticket> predicate) {
        return persistence.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> list() {
        return Collections.unmodifiableList(new ArrayList<>(persistence.values()));
    }

    @Override
    public Ticket update(Ticket ticket) {
        return persistence.replace(ticket.getId(), ticket);
    }

    @Override
    public Ticket delete(long id) {
        return persistence.remove(id);
    }

}