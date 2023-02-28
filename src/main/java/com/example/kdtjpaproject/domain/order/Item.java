package com.example.kdtjpaproject.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int price;
    private int stockQuantity;
}
