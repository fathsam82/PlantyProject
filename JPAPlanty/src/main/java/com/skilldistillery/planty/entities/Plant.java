package com.skilldistillery.planty.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private Integer price;
	
	@Column(name = "stock_quantity")
	private Integer stockQuantity;
	
	@Column(name = "plant_image_url")
	private String plantImageUrl;
	
	private String size;
	
	

	public Plant() {
		
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getPlantImageUrl() {
		return plantImageUrl;
	}

	public void setPlantImageUrl(String plantImageUrl) {
		this.plantImageUrl = plantImageUrl;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", stockQuantity=" + stockQuantity + ", plantImageUrl=" + plantImageUrl + ", size=" + size + "]";
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
		Plant other = (Plant) obj;
		return id == other.id;
	}
	
	

}
