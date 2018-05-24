package manglar.soporte.services;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketsMapServiceTest {

  private TicketsService service;

  @BeforeEach
  void setup() {
    service = new TicketsMapService();
    service.report(new TicketValue(
        1L,
        "Error en Java",
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    ));
    service.report(new TicketValue(
        2L,
        "Error en Java 2",
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    ));
  }

  @Test
  void report() {
    service.report(new TicketValue(
        3L,
        "Error en Java 3",
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    ));

    assertEquals(3, service.all().size());
    assertEquals("Error en Java 3", service.get(3L).getSubject());
  }

  @Test
  void get() {
    Ticket ticket = service.get(1L);

    assertEquals("Error en Java", ticket.getSubject());
  }

  @Test
  void get_error() {
    assertThrows(NoSuchElementException.class, () -> service.get(1000L));
  }

  @Test
  void find_some() {
    Optional<Ticket> ticket = service.find(1L);

    assertTrue(ticket.isPresent());
    assertEquals("Error en Java", ticket.get().getSubject());
  }

  @Test
  void find_none() {
    Optional<Ticket> ticket = service.find(1000L);

    assertFalse(ticket.isPresent());
    assertThrows(NoSuchElementException.class, ticket::get);
  }

  @Test
  void all() {
    List<Ticket> tickets = service.all();

    assertEquals(2, tickets.size());
    assertEquals(Long.valueOf(1L), tickets.get(0).getId());
  }

  @Test
  void modify() {
    Ticket ticket = service.get(1L);

    Ticket out = service.modify(
        TicketValue.from(ticket)
            .toBuilder()
            .subject("SUBJECT UPDATED")
            .build()
    );

    assertEquals("SUBJECT UPDATED", out.getSubject());
  }

  @Test
  void close_not_solved() {
    Ticket ticket = service.close(1L, Resolution.NOT_SOLVED);

    assertEquals(TicketStatus.CLOSED, ticket.getStatus());
    assertEquals(Resolution.NOT_SOLVED, ticket.getClosedAs());
  }

  @Test
  void close_solved() {
    Ticket ticket = service.close(1L, Resolution.SOLVED, "Solution");

    assertEquals(TicketStatus.CLOSED, ticket.getStatus());
    assertEquals(Resolution.SOLVED, ticket.getClosedAs());
    assertEquals("Solution", ticket.getSolution().get());
  }

  @Test
  void close_solved_error() {
    assertThrows(IllegalStateException.class, () -> service.close(1L, Resolution.SOLVED));
  }
}