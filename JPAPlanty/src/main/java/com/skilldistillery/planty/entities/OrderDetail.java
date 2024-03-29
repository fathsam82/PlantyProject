package com.skilldistillery.planty.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantity_ordered")
	private Integer quantityOrdered;
	
	@Column(name = "unit_price")
	private int unitPrice;
	
	@Column(name = "subtotal_price")
	private Integer subtotalPrice;
	
	@Column(name = "gift_wrap")
	private Boolean giftWrap;
	
//	@JsonBackReference
//	@JsonManagedReference
	@JsonIgnore
//	@JsonIgnoreProperties
	@JoinColumn(name = "order_cart_id")
	@ManyToOne
	private OrderCart orderCart;
	
	@JsonIgnoreProperties({"plantCat", "plantOrigins"})
	@JoinColumn(name = "plant_id")
	@ManyToOne
	private Plant plant;
	
	

	public OrderDetail() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Integer getSubtotalPrice() {
		return subtotalPrice;
	}

	public void setSubtotalPrice(Integer subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}

	public Boolean getGiftWrap() {
		return giftWrap;
	}

	public void setGiftWrap(Boolean giftWrap) {
		this.giftWrap = giftWrap;
	}

	public OrderCart getOrderCart() {
		return orderCart;
	}

	public void setOrderCart(OrderCart orderCart) {
		this.orderCart = orderCart;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", quantityOrdered=" + quantityOrdered + ", unitPrice=" + unitPrice
				+ ", subtotalPrice=" + subtotalPrice + ", giftWrap=" + giftWrap + ", orderCart=" + orderCart
				+ ", plant=" + plant + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return id == other.id;
	}
	
	
	
	
	
	

}
