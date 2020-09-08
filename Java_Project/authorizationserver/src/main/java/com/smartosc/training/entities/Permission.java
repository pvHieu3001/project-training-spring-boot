package com.smartosc.training.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
}
