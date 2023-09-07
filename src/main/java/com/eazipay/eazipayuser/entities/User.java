package com.eazipay.eazipayuser.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "userEntity")
public class User {
    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private  String phoneNumber;
}
