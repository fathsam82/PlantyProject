package com.skilldistillery.planty.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "order_cart")
public class OrderCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "total_price")
	private int totalPrice;
	
	@Column(name = "date_placed")
	@CreationTimestamp
	private LocalDateTime datePlaced;
	
	private String notes;
	
	@Column(name = "estimated_delivery_date")
	@CreationTimestamp
	private LocalDateTime estimatedDeliveryDate;
	
	@Column(name = "tracking_number")
	private Integer trackingNumber; 
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
//	@JsonIgnore  // TEMP
	@ManyToOne
	@JoinColumn(name = "payment_data_id")
	private PaymentData paymentData;
	
//	@JsonIgnore  // TEMP
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//  COMMENTED OUT FOR TEMP FIX FOR INFINITE RECURSION, RELATIONSHIP TURNED FROM BI TO UNI
//	@JsonManagedReference
//	//@JsonBackReference
   @JsonIgnore
	@OneToMany(mappedBy = "orderCart", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
	private Boolean enabled;

	public OrderCart() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(LocalDateTime datePlaced) {
		this.datePlaced = datePlaced;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Integer getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(Integer trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentData getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

//	public List<OrderDetail> getOrderDetails() {
//		return orderDetails;
//	}
//
//	public void setOrderDetails(List<OrderDetail> orderDetails) {
//		this.orderDetails = orderDetails;
//	}
	
	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "OrderCart [id=" + id + ", totalPrice=" + totalPrice + ", datePlaced=" + datePlaced + ", notes=" + notes
				+ ", estimatedDeliveryDate=" + estimatedDeliveryDate + ", trackingNumber=" + trackingNumber
				+ ", paymentMethod=" + paymentMethod + ", paymentData=" + paymentData + ", user=" + user + ", enabled="
				+ enabled + "]";
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
		OrderCart other = (OrderCart) obj;
		return id == other.id;
	}


}