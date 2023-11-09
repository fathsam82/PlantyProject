package com.skilldistillery.planty.entities;


import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private int price;
	
	@Column(name = "stock_quantity")
	private int stockQuantity;
	
	@Column(name = "plant_image_url")
	private String plantImageUrl;
	
	private String size;
	
	@Column(name = "is_discounted")
	private Boolean isDiscounted;
	
//	@JsonBackReference
	@JoinColumn(name = "plant_category_id")
	@ManyToOne
	private PlantCategory plantCat;
	

	@JsonManagedReference
	@OneToMany(mappedBy = "plant")
	private List<PlantOrigin> plantOrigins;
	
	private Boolean enabled;
	
//	@OneToMany(mappedBy = "plant")
//	private List<OrderDetail> orderDetails;
//	No need for plant to have a bi-directional relationship with orderDetails, will only have to access plant info through order details, not other way around 
	
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



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getStockQuantity() {
		return stockQuantity;
	}



	public void setStockQuantity(int stockQuantity) {
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



	public Boolean getIsDiscounted() {
		return isDiscounted;
	}



	public void setIsDiscounted(Boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}


	public PlantCategory getPlantCat() {
		return plantCat;
	}

	public void setPlantCat(PlantCategory plantCat) {
		this.plantCat = plantCat;
	}

	public List<PlantOrigin> getPlantOrigins() {
		return plantOrigins;
	}

	public void setPlantOrigins(List<PlantOrigin> plantOrigins) {
		this.plantOrigins = plantOrigins;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", stockQuantity=" + stockQuantity + ", plantImageUrl=" + plantImageUrl + ", size=" + size
				+ ", isDiscounted=" + isDiscounted + ", plantCat=" + plantCat + ", enabled=" + enabled + "]";
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
