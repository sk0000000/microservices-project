package com.m_produits.health;

import com.m_produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ProduitHealthIndicator implements HealthIndicator {

    private final ProduitService produitService;

    @Autowired
    public ProduitHealthIndicator(ProduitService produitService) {
        this.produitService = produitService;
    }


    @Override
    public Health health() {
        int size = produitService.findAll().size();
        if(size > 0) return Health.up().build();
        return Health.down().withDetail("count", size).build();
    }
}
