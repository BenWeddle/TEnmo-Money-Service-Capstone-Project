package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {
    BigDecimal getBalance (int UserId);

    BigDecimal addBalance (BigDecimal amount, int UserId);

    BigDecimal subtractBalance (BigDecimal amount, int UserId);


}
