package com.smartosc.training.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 01/07/2020 - 1:57 PM
 * @created_by Namtt
 * @since 01/07/2020
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class DetailTypeRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private Integer maxPerson;
  private Integer defaultPerson;
  private BigDecimal price;
  private BigDecimal additionalPrice;
  private Integer roomAvailable;

  @ManyToOne
  @JoinColumn(name = "type_room")
  private TypeRoom typeRoom;

  @OneToOne(fetch = FetchType.LAZY,mappedBy = "detailTypeRoom")
  private StatusOT statusOT;
}
