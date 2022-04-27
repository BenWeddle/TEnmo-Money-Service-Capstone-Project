package com.techelevator.tenmo.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountDaoJdbc implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public AccountDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalance(int UserId) {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        SqlRowSet results = null;
        BigDecimal balance = null;
        try {
            results = jdbcTemplate.queryForRowSet(sql, UserId);
            if (results.next()) {
                balance = results.getBigDecimal("balance");
            }
        }catch (DataAccessException e) {
            System.out.println("Error accessing data");

            }
        return balance;

        }
    }
