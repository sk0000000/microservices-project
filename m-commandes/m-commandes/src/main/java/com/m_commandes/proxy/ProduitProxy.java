package com.m_commandes.proxy;

import com.m_commandes.dto.ProduitDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name="m-produits" , url = "localhost:9002/produits")
public interface ProduitProxy {


    @GetMapping("/produits/get_all_products")
    List<ProduitDTO> get_all_products();

    @GetMapping("produits/get_products_by_id/{id}")
    ProduitDTO get_product_by_id(@PathVariable int id);

    @PutMapping("/produits/update_produit/{id}")
    ProduitDTO updateProduit(@PathVariable int id,@RequestBody ProduitDTO produit);



}
