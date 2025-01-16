package com.m_produits.controller;


import com.m_produits.models.Produit;
import com.m_produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @GetMapping("/get_all_products")
     public List<Produit> getAllProduits() {
        return produitService.findAll();
     }

     @GetMapping("/get_product_by_id/{id}")
     public Optional<Produit> getProduitById(@PathVariable @RequestParam int id) {
        return produitService.findById(id);
     }

     @PostMapping("/add_product")
     public Produit createProduit(Produit produit) {
        return produitService.save(produit);
     }

     @PutMapping("/update_produit/{id}")
     public Produit updateProduit(@PathVariable int id ,@RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
     }

     @DeleteMapping("/delete_product")
     public void deleteProduitById(@RequestParam int id) {
        produitService.delete(id);
     }



}
