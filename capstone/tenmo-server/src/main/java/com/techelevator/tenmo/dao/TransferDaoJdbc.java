package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class TransferDaoJdbc implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    AccountDao accountDao;

    public TransferDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void sendTransfer(Transfer transfers) {
        accountDao.subtractBalance(transfers.getAmount(), transfers.getUserFrom());
        accountDao.addBalance(transfers.getAmount(), transfers.getUserTo());
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, 2, 2, transfers.getUserFrom(), transfers.getUserTo(), transfers.getAmount());


    }


        private Transfer mapRowToTransfer(SqlRowSet results) {
            Transfer transfer = new Transfer();
            transfer.setTransferId(results.getInt("transfer_id"));
            transfer.setTransferTypeId(results.getInt("transfer_type_id"));
            transfer.setTransferStatusId(results.getInt("transfer_status_id"));
            transfer.setAccountFrom(results.getInt("account_from"));
            transfer.setAccountTo(results.getInt("account_to"));
            transfer.setAmount(results.getBigDecimal("amount"));
            return transfer;
        }


}
