package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.DisplayTransfer;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
//        if (transfers.getUserFrom() == transfers.getUserTo()) {
//            System.out.println("You cant send money to yourself.");
//        }
        accountDao.subtractBalance(transfers.getAmount(), transfers.getUserFrom());
        accountDao.addBalance(transfers.getAmount(), transfers.getUserTo());
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?);";
        jdbcTemplate.update(sql, 2, 2, transfers.getUserFrom(), transfers.getUserTo(), transfers.getAmount());


    }

    @Override
    public List<DisplayTransfer> getAllTransfers(int user_id) {
        List<DisplayTransfer> transferList = new ArrayList<>();
        String sql = "SELECT t.transfer_id, t.transfer_type_id, t.transfer_status_id, u.username AS user_from, u1.username AS user_to, t.amount " +
                "FROM transfer t " +
                "JOIN account a ON t.account_from = a.account_id " +
                "JOIN tenmo_user u ON a.user_id = u.user_id " +
                "JOIN account a1 ON t.account_to = a1.account_id " +
                "JOIN tenmo_user u1 ON a1.user_id = u1.user_id " +
                "WHERE u.user_id = ? OR u1.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
            DisplayTransfer displayTransfer = mapRowToDisplayTransfer(results);
            transferList.add(displayTransfer);
        }
        return transferList;
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
    private DisplayTransfer mapRowToDisplayTransfer(SqlRowSet results) {
        DisplayTransfer displayTransfer = new DisplayTransfer();
        displayTransfer.setTransferId(results.getInt("transfer_id"));
        displayTransfer.setTransferTypeId(results.getInt("transfer_type_id"));
        displayTransfer.setTransferStatusId(results.getInt("transfer_status_id"));
        displayTransfer.setAmount(results.getBigDecimal("amount"));
        displayTransfer.setUserFrom(results.getString("user_from"));
        displayTransfer.setUserTo(results.getString("user_to"));
        return displayTransfer;
    }


}
