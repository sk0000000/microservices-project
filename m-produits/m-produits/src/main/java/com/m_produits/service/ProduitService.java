package com.m_produits.service;


import com.m_produits.models.Produit;
import com.m_produits.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Optional<Produit> findById(int id) {
        return produitRepository.findById(id);
    }

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(int id, Produit produit) {
        return produitRepository.findById(id).map(existingProduit -> {
            existingProduit.setName(produit.getName());
            existingProduit.setPrix(produit.getPrix());
            existingProduit.setQuantite(produit.getQuantite());
            return produitRepository.save(existingProduit);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id " + id));
    }

    public void delete(int id) {
        produitRepository.deleteById(id);
    }


}
