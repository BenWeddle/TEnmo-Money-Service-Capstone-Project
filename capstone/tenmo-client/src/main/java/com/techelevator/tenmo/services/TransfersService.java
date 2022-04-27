package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransfersService {
    private String baseUrl;
    RestTemplate restTemplate = new RestTemplate();
    private String authToken;
    private AuthenticatedUser currentUser;

    public TransfersService(String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        baseUrl = url;
    }

    public String sendTransfer() {
        User[] users = null;
        Transfers transfers = new Transfers();

        Scanner scanner = new Scanner(System.in);
        users = restTemplate.exchange(baseUrl + "listUsers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
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
    }
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
