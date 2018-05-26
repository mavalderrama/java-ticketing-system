package manglar.soporte.config;

import manglar.soporte.repositories.TicketDAO;
import manglar.soporte.repositories.TicketMemoryDAO;
import manglar.soporte.services.TicketsDAOService;
import manglar.soporte.services.TicketsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TicketsService ticketsService(){
        return new TicketsDAOService(ticketsDAO());
    }

    //This is optional because it receives the dependency as a parameter
    /*@Bean
    public TicketsService ticketsService(TicketDAO ticket){
        return new TicketsDAOService(ticket);
    }*/

    @Bean
    public TicketDAO ticketsDAO(){
        return new TicketMemoryDAO();
    }
}
