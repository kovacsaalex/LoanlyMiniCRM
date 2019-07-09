package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Loan;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoanRepository {

    @Select("SELECT * FROM testdbase.loan;")
    List<Loan> findAll();



}
