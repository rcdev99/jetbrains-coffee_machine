package org.jetbrains.coffee_machine;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
        /*Needed variables*/
        final Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400.0, 540.0, 120.0, 9,550.0);
        /*Processing*/
        while (coffeeMachine.isAvailable()) {
            coffeeMachine.executeSelectedInstruction(scanner.next());
        }
        scanner.close();
    }
}
