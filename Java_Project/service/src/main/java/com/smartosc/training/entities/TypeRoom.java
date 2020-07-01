package com.smartosc.training.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 01/07/2020 - 2:01 PM
 * @created_by Namtt
 * @since 01/07/2020
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class TypeRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String imgUrl;
  private BigDecimal totalPrice;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @OneToMany(fetch = FetchType.LAZY,mappedBy = "typeRoom")
  private List<DetailTypeRoom> detailTypeRooms;


}
