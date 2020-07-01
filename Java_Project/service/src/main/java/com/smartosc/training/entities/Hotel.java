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
 * @created_at 01/07/2020 - 2:03 PM
 * @created_by Namtt
 * @since 01/07/2020
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String imgUrl;
  private BigDecimal totalRate;
  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
  private List<TypeRoom> typeRooms;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
  private List<Comment> comments;
}
