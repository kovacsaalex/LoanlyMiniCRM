package com.example.demo.repository;

import com.example.demo.pojo.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClientRepository {

    //@Select("SELECT * FROM `tesDBase`.client;")
    @Select("SELECT * FROM testdbase.client;")
    List<Client> findAll();

    @Update("UPDATE testdbase.client SET lastname=#{lastname}, firstname=#{firstname} WHERE id=#{Id}")
    void update(Client client);
    
//    @Insert("INSERT into `tesDBase`.client (`lastname`,`firstname`,maidenName, birthPlace,birthDate,motherName,postalCode,city,address,movingTime,personalID,cardId,addressCardID,taxID,socialSecurityCard,phoneNumber,email) " +
//            "VALUES (#{lastname}, #{firstname},#{maidenName},#{birthPlace},#{birthDate},#{motherName},#{postalCode},#{city},#{address},#{movingTime},#{personalID},#{cardId},#{addressCardID},#{taxID},#{socialSecurityCard},#{phoneNumber},#{email})")
//@Insert("INSERT into testdbase.client (lastname,firstname) VALUES (#{lastname}, #{firstname})")

    @Insert("INSERT into testdbase.client (lastname,firstname,\"maidenName\", \"birthPlace\",\"birthDate\",\"motherName\",\"postalCode\",city,address,\"movingTime\",\"personalID\",\"cardId\",\"addressCardID\",\"taxID\",\"socialSecurityCard\",\"phoneNumber\",email) " +
           "VALUES (#{lastname},#{firstname},#{maidenName},#{birthPlace},#{birthDate},#{motherName},#{postalCode},#{city},#{address},#{movingTime},#{personalID},#{cardId},#{addressCardID},#{taxID},#{socialSecurityCard},#{phoneNumber},#{email})")

    void create(Client client);

}
