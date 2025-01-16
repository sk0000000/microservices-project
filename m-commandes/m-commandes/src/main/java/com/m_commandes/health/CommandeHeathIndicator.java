package com.m_commandes.health;


import com.m_commandes.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHeathIndicator implements HealthIndicator {

    private CommandeService commandeService;
    @Autowired
    public CommandeHeathIndicator(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @Override
    public Health health() {
        int count = commandeService.getCommandes().size();
        if(count > 0) return Health.up().build();
        else return Health.down().build();
    }
}
