package com.m_commandes.controller;

import com.m_commandes.configurations.ApplicationPropertiesConfiguration;
import com.m_commandes.dto.ProduitDTO;
import com.m_commandes.models.Commande;
import com.m_commandes.proxy.ProduitProxy;
import com.m_commandes.service.CommandeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
    private final ProduitProxy produitProxy;

    @Autowired
    public CommandeController(CommandeService commandeService, ApplicationPropertiesConfiguration applicationPropertiesConfiguration, ProduitProxy produitProxy) {
        this.commandeService = commandeService;
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
        this.produitProxy = produitProxy;
    }

    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackGetCommandes")
    @GetMapping("/get_all")
    public List<Commande> getCommandes() {
        List<Commande> commandes = commandeService.getCommandes();
        return commandes.subList(0, applicationPropertiesConfiguration.getLimit());
    }

    public List<Commande> fallbackGetCommandes(Throwable throwable) {
        System.out.println("Erreur lors de la récupération des commandes : " + throwable.getMessage());
        return Collections.emptyList(); // Retourne une liste vide en cas d'erreur
    }

    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackGetCommandeById")
    @GetMapping("/get_by_id/{id}")
    public Commande getCommandeById(@PathVariable int id) {
        return commandeService.getById(id);
    }

    public Commande fallbackGetCommandeById(int id, Throwable throwable) {
        System.out.println("Erreur lors de la récupération de la commande avec l'ID " + id + " : " + throwable.getMessage());
        Commande defaultCommande = new Commande();
        defaultCommande.setDescription("Commande non disponible (fallback)");
        return defaultCommande;
    }

    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackAddCommande")
    @PostMapping("/add_command")
    public Commande addCommande(@RequestBody Commande commande) {
        ProduitDTO produit = produitProxy.get_product_by_id(commande.getId());
        if (produit == null) {
            throw new RuntimeException("Produit inexistant");
        }
        if (produit.getQuantite() < commande.getQuantite()) {
            throw new RuntimeException("Quantité invalide");
        }

        produit.setQuantite(produit.getQuantite() - commande.getQuantite());
        produitProxy.updateProduit(produit.getId(), produit);

        commande.setMontant(produit.getPrix() * commande.getQuantite());
        return commandeService.addCommande(commande);
    }

    public Commande fallbackAddCommande(Commande commande, Throwable throwable) {
        System.out.println("Erreur lors de l'ajout de la commande : " + throwable.getMessage());
        Commande defaultCommande = new Commande();
        defaultCommande.setDescription("Commande non ajoutée (fallback)");
        return defaultCommande;
    }

    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackDeleteCommande")
    @DeleteMapping("/delete_command/{id}")
    public void deleteCommande(@PathVariable int id) {
        commandeService.deleteCommande(id);
    }

    public void fallbackDeleteCommande(int id, Throwable throwable) {
        System.out.println("Erreur lors de la suppression de la commande avec l'ID " + id + " : " + throwable.getMessage());
    }
}
