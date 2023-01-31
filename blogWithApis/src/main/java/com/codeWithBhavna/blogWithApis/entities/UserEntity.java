package com.codeWithBhavna.blogWithApis.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="Users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    generatedvalue is for the auto increment of id.
    @Column(name="user_id")
    private int id;

    @Column(name="user_name", nullable = false, length= 100)
    private String name;
    @Column(name = "email_id")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "about")
    private String about;

}
