package com.sb.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product")
public class Product {
	
	@PrimaryKeyColumn(name="id", type = PrimaryKeyType.PARTITIONED)
	private String productId;
	@Column("name")
	private String productName;
	@Column("code")
	private String code;
	@Column("price")
	private BigDecimal price;
	@Column("created_datetime")
	private LocalDateTime createDateTime;
	@Column("updated_datetime")
	private LocalDateTime updatedDateTime;
	
	
	public Product() {
		super();
	}

	
	public Product(String productId, String productName, String code, BigDecimal price, LocalDateTime createDateTime,
			LocalDateTime updatedDateTime) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.code = code;
		this.price = price;
		this.createDateTime = createDateTime;
		this.updatedDateTime = updatedDateTime;
	}


	public Product(String productId, String productName, String code, BigDecimal price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.code = code;
		this.price = price;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}


	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	

}
