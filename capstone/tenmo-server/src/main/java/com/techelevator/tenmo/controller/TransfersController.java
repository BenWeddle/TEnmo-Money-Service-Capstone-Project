package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransfersController {

    @Autowired
    private TransferDao transfersDao;

<<<<<<< HEAD
    @PostMapping("account/transfer")
=======
    @PostMapping("/transfer")
>>>>>>> f52de27e0fa660ba2afab6e6924fa100067a641d
    public boolean makeTransfer(@RequestBody Transfer transfer) {

        transfersDao.sendTransfer(transfer);

        return true;
    }

}
