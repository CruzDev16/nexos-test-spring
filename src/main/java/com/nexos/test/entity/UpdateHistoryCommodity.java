package com.nexos.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "update_history_commodity")
@Getter
@Setter
@ToString
public class UpdateHistoryCommodity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Commodity commodity;
	
	@NotBlank
	@Size(max = 30)
	private String name;

	@NotBlank
	@Size(max = 30)
	private String product;

	@NotNull
	private Integer quantity;

	@NotNull
	private Date dateOfAdmission;
	
	@NotNull
	private Date creationDate;

	@ManyToOne
	@NotNull
	private User creatorUser;
	
}
