package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
<<<<<<< HEAD
import org.springframework.http.MediaType;
=======
>>>>>>> f52de27e0fa660ba2afab6e6924fa100067a641d
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Scanner;

public class TransferService {
    private String baseUrl;
    RestTemplate restTemplate = new RestTemplate();
    private String authToken;
    private AuthenticatedUser currentUser;

    public TransferService(String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        baseUrl = url;
    }
<<<<<<< HEAD
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void sendBucks() {
        User[] users = null;
        Transfer transfer = new Transfer();

        try {
            Scanner scanner = new Scanner(System.in);
            users = restTemplate.exchange(baseUrl + "account/listUsers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
=======

    public void sendTransfer() {
        User[] users = null;
        Transfer transfers = new Transfer();

        try {
            Scanner scanner = new Scanner(System.in);
            users = restTemplate.exchange(baseUrl + "listUsers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
>>>>>>> f52de27e0fa660ba2afab6e6924fa100067a641d
            System.out.println("------------------------------\n"
                    + "Users\n" + "ID\tName\n" +
                    "------------------------------");
            for (User i : users) {
                if (i.getId() != currentUser.getUser().getId()) {
                    System.out.println(i.getId() + "\t\t" + i.getUsername());
                }
            }
            System.out.println("-----------------------------\n"
                    + "Enter Id of user you are sending to (0 to cancel): ");
<<<<<<< HEAD
            transfer.setAccountTo(Integer.parseInt(scanner.nextLine()));
            transfer.setAccountFrom(currentUser.getUser().getId());
            if (transfer.getAccountTo() != 0) {
                System.out.println("Enter amount: ");
                try {
                    transfer.setAmount(new BigDecimal(Double.parseDouble(scanner.nextLine())));
                } catch (NumberFormatException e) {
                    System.out.println("Error when entering amount: ");
                }
                String amount = restTemplate.exchange(baseUrl + "account/transfer", HttpMethod.POST, makeAuthEntity(), String.class).getBody();
                System.out.println(amount);
            }
        } catch (Exception e) {
            System.out.println("Invalid");
        }

    }

=======
            transfers.setAccountTo(Integer.parseInt(scanner.nextLine()));
            transfers.setAccountFrom(currentUser.getUser().getId());
            if (transfers.getAccountTo() != 0) {
                System.out.println("Enter amount: ");
                try {
                    transfers.setAmount(new BigDecimal(Double.parseDouble(scanner.nextLine())));
                } catch (NumberFormatException e) {
                    System.out.println("Error when entering amount: ");
                }
                String amount = restTemplate.exchange(baseUrl + "transfer", HttpMethod.POST, makeAuthEntity(), String.class).getBody();
                System.out.println(amount);
            }
        } catch (Exception e) {
            System.out.println("");
        }

    }
>>>>>>> f52de27e0fa660ba2afab6e6924fa100067a641d
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
