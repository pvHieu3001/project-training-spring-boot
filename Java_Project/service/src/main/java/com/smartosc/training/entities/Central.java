package com.smartosc.training.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @created_at 01/07/2020 - 1:54 PM
 * @created_by Namtt
 * @since 01/07/2020
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class Central {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private String imgUrl;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "central", cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.REFRESH})
  private List<City> cities;

}
