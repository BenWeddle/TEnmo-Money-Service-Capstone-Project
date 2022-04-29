package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    public void sendTransfer(Transfer transfers);

    List<Transfer> getAllTransfers(int id);
}
