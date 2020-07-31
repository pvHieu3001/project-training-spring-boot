package com.smartosc.training.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.REFRESH})
  @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "account_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles;

}
