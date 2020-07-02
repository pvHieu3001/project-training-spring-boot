package com.smartosc.training.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
 * @created_at 01/07/2020 - 1:48 PM
 * @created_by Namtt
 * @since 01/07/2020
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private Integer status;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.REFRESH})
  @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "account_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.REFRESH})
  private List<Comment> comments;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.REFRESH})
  private StatusOT statusOT;

}
