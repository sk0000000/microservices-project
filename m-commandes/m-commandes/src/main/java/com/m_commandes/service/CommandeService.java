package com.m_commandes.service;

import com.m_commandes.models.Commande;
import com.m_commandes.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getById(int id) {
        return commandeRepository.getById(id);
    }

    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }


    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }

}
