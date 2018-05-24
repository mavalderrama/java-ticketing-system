package manglar.soporte;

import java.time.LocalDateTime;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import manglar.soporte.services.TicketsMapService;
import manglar.soporte.services.TicketsService;

public class App {

  public static void main(String[] args) {
    TicketsService service = new TicketsMapService();

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

    service.all().forEach(System.out::println);
  }
}
