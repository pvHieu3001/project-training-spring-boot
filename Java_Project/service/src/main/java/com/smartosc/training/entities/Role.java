package com.smartosc.training.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * testJWT
 *
 * @author Namtt
 * @created_at 01/06/2020 - 4:18 PM
 * @created_by Namtt
 * @since 01/06/2020
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> accounts;


}
