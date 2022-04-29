package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    BigDecimal getBalance (int UserId);

    BigDecimal addBalance (BigDecimal amount, int UserId);

    BigDecimal subtractBalance (BigDecimal amount, int UserId);


<<<<<<< HEAD

=======
>>>>>>> f52de27e0fa660ba2afab6e6924fa100067a641d
}
