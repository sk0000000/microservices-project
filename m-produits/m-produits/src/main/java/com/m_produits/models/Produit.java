package com.m_produits.models;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "produits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private int id;
    @Column(name = "nom")
    private String name;
    @Column(name = "prix")
    private Double prix;
    @Column(name = "quantite")
    private int quantite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
