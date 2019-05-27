package com.test.userstore;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.models.Command;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ValidationException {
        Scanner scanner = new Scanner(System.in);
        boolean isOpen = true;
        System.out.print("--- Use help to view commands --- \n");

        while (isOpen) {

            System.out.print("Enter command: ");

            Command input = Command.from(scanner.nextLine());

            input.doAction();

            if (input == Command.QUITE) {
                isOpen = false;
            }
        }

        scanner.close();
    }

}
