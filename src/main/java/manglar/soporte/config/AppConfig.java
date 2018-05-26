package manglar.soporte.config;

import manglar.soporte.repositories.TicketDAO;
import manglar.soporte.repositories.TicketMemoryDAO;
import manglar.soporte.services.TicketsDAOService;
import manglar.soporte.services.TicketsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //This configuration uses the ticketsDAO Bean as a dependency for the service
    @Bean
    public TicketsService ticketsService(){
        return new TicketsDAOService(ticketsDAO());
    }

    //This is optional because it receives the dependency as a parameter
    /*@Bean
    public TicketsService ticketsService(TicketDAO ticket){
        return new TicketsDAOService(ticket);
    }*/

    //This method configures a Bean to be used by ticketService as a dependency
    @Bean
    public TicketDAO ticketsDAO(){
        return new TicketMemoryDAO();
    }
}