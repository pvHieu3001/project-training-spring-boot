package com.smartosc.training.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class StatusOT {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToOne
  @JoinColumn(name = "status")
  private User user;

  @OneToOne
  @JoinColumn(name = "detail_room")
  private DetailTypeRoom detailTypeRoom;
}
