package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class TransferDaoJdbc implements TransfersDao {

    private JdbcTemplate jdbcTemplate;

    public TransferDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String sendTransfer(int userFrom, int userTo, BigDecimal amount) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, );
    }
}
