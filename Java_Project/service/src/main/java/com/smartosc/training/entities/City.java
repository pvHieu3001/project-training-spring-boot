package com.smartosc.training.entities;

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
 * @created_at 01/07/2020 - 2:06 PM
 * @created_by Namtt
 * @since 01/07/2020
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String urlImg;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
  private List<Hotel> hotels;

  @ManyToOne
  @JoinColumn(name = "central_id")
  private Central central;
}
