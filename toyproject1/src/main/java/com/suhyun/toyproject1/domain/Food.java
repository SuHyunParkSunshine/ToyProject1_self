package com.suhyun.toyproject1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    
    @Id
    private int id;
	private String name;
    private String addr_do;
	private String addr_lot;
	private String contact;
	private String certificated_name;
	private String rmenu;
	private String parking;
	private String pet;
	private String englishmenu;
	private String foodimg;
	private double latitude;
	private double longitude;
	private String gu;
    
}
