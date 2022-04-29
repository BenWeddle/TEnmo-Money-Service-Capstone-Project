package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransfersController {

    @Autowired
    private TransferDao transfersDao;

    @PostMapping("/transfer")
    public boolean makeTransfer(@RequestBody Transfer transfer) {
        System.out.println("Making transfer");
        transfersDao.sendTransfer(transfer);

        return true;
    }

    @GetMapping("transfer/{id}")
    public List<Transfer> getAllTransfers(@PathVariable int id){
        List<Transfer> results = transfersDao.getAllTransfers(id);

        return results;
    }

}
