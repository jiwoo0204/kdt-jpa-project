package com.kdt.lecture.domain.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
public abstract class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int price;
    private int stockQuantity;

    // item : order_item = 1 : N
    @OneToMany(mappedBy = "items")
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setItems(this);
    }
}
