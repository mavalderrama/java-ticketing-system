package manglar.soporte;

import java.time.LocalDateTime;
import java.util.Scanner;

import manglar.soporte.config.AppConfig;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import manglar.soporte.services.TicketsDAOService;
import manglar.soporte.services.TicketsMapService;
import manglar.soporte.services.TicketsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //Obteniendo el servicio ya configurado
        TicketsService service = applicationContext.getBean(TicketsService.class);
        //Cargar datos

        while(true){
            System.out.print("$ > ");
            String input = scanner.nextLine();
            if(input.equals("exit")){
                System.out.println();
                break;
            }
            System.out.println(input);
        }

        /*service.report(new TicketValue(
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
        service.all().forEach(System.out::println);*/
  }
}
