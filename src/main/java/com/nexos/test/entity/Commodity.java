package com.nexos.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "commodity", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@Getter
@Setter
@ToString
public class Commodity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

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

	@ManyToOne
	@NotNull
	private User creatorUser;

}
