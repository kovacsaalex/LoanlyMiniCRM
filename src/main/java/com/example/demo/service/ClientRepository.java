package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Loan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientRepository {


//	@Select("SELECT * FROM testdbase.client;")
//	List<Client> findAll();
//
//	@Select("SELECT * FROM testdbase.loan;")
//	List<Loan> findAllLoan();
//
//	@Update("UPDATE testdbase.client SET lastname=#{lastname},firstname=#{firstname},\"maidenName\"=#{maidenName}, \"birthPlace\"=#{birthPlace},\"birthDate\"=#{birthDate},\"motherName\"=#{motherName},\"postalCode\"=#{postalCode},city=#{city},address=#{address},\"movingTime\"=#{movingTime},\"personalID\"=#{personalID},\"cardId\"=#{cardId},\"addressCardID\"=#{addressCardID},\"taxID\"=#{taxID},\"socialSecurityCard\"=#{socialSecurityCard},\"phoneNumber\"=#{phoneNumber},email=#{email} WHERE ID=#{id}")
//	void update(Client client);
//
//	@Insert("INSERT into testdbase.client (lastname,firstname,\"maidenName\", \"birthPlace\",\"birthDate\",\"motherName\",\"postalCode\",city,address,\"movingTime\",\"personalID\",\"cardId\",\"addressCardID\",\"taxID\",\"socialSecurityCard\",\"phoneNumber\",email) "
//			+ "VALUES (#{lastname},#{firstname},#{maidenName},#{birthPlace},#{birthDate},#{motherName},#{postalCode},#{city},#{address},#{movingTime},#{personalID},#{cardId},#{addressCardID},#{taxID},#{socialSecurityCard},#{phoneNumber},#{email})")
//
//	void create(Client client);
//
//	@Delete("DELETE FROM testdbase.client WHERE ID=#{id}")
//	void delete(Client client);

}
