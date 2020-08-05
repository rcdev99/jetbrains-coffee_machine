package org.jetbrains.coffee_machine;

import java.util.Scanner;

public class Principal {
    //Static variables
	public static final Scanner scanner = new Scanner(System.in);
    public static Double qttWater = 400.0;
    public static Double qttMilk = 540.0;
    public static Double qttCoffeeBeans = 120.0;
    public static Integer qttDisposableCups = 9;
    public static Double storedMoney = 550.00;
    public static boolean isAvailable = true;
    //Main
    public static void main(String[] args) {
        //Required variables
        String instruction;
        //Execution
        //identifying action to be taken
        do {
            System.out.println("Informe a ação desejada (comprar, repor, retirada, recursos, sair):");
            instruction = scanner.next();
            executeSelectedInstruction(instruction);
        } while (isAvailable);
    }
    //Methods
    /**
     * Method responsible for checking available resources before preparing coffee
     * @param typeCoffee
     * @return true if can produce a cup coffee
     */
    public static boolean canIProduce (String typeCoffee){
        //Local variables
        Integer waterNeeded = -1;
        Integer coffeeNeeded = -1;
        Integer milkNeeded = -1;
        //filling variables ​according to the type of coffee chosen
        switch (typeCoffee) {
            case "1":
                waterNeeded = 250;
                coffeeNeeded = 16;
                break;
            //2 - latte
            case "2":
                waterNeeded = 350;
                milkNeeded = 75;
                coffeeNeeded = 20;
                break;
            //3 - cappuccino
            case "3":
                waterNeeded = 200;
                milkNeeded  = 100;
                coffeeNeeded = 12;
                break;
            //back - return to main menu
            case "voltar":
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        //validations
        if (waterNeeded <= qttWater && coffeeNeeded <= qttCoffeeBeans && milkNeeded <= qttMilk && qttDisposableCups >= 1) {
            System.out.println("Temos os recursos necessários, estamos preparando seu café!");
            return true;
        } else {
            System.out.print("Desculpe, não temos ");
            //without water?
            if (waterNeeded > qttWater) {
                System.out.print("água ");
            }
            //without coffee?
            if (coffeeNeeded > qttCoffeeBeans) {
                System.out.print("café ");
            }
            //without milk?
            if (milkNeeded > qttMilk) {
                System.out.print("leite ");
            }
            //without cups?
            if (qttDisposableCups < 1) {
                System.out.print("copos descartáveis ");
            }
            System.out.print("!\n");
        }
        return false;
    }

    /**
     * Method used to show current supply information
     */
    public static void displaySupplyInformation(){
        System.out.println("\nSua coffee machine tem:");
        System.out.println(qttWater + " ml de água");
        System.out.println(qttMilk + " ml de leite");
        System.out.println(qttCoffeeBeans + " g de café");
        System.out.println(qttDisposableCups + " copo(s)");
        System.out.println("R$ " + storedMoney + " em caixa");
    }

    /**
     * Method used to execute the instruction selected by user
     * @param selectedInstruction Option selected by user
     */
    public static void executeSelectedInstruction(String selectedInstruction){
        switch (selectedInstruction) {
            case "comprar":
            	System.out.println("Qual tipo de café você deseja? 1 - Expresso, 2 - Café com leite, 3 - Cappuccino, voltar - Para retornar ao menu principal :");
                String option = scanner.next();
                if (canIProduce(option)) {
                    buyCoffee(option);
                }
                break;
            case "repor":
                replenishSupplies();
                break;
            case "retirada":
                takeStoredMoney();
                break;
            case "recursos":
                displaySupplyInformation();
                break;
            case "sair":
                isAvailable = false;
                break;
            default: System.out.println("Opção inválida");
                break;
        }
    }

    /**
     * Method used to buy a coffee and update the supply level after purchase
     * @param typeCoffee type of coffee chosen
     */
    public static void buyCoffee(String typeCoffee) {
        //Updating the supply level according to the type of chosen coffee
        switch (typeCoffee) {
            //1 - espresso
            case "1":
                qttWater -= 250;
                qttCoffeeBeans -= 16;
                storedMoney += 4;
                qttDisposableCups --;
                break;
            //2 - latte
            case "2":
                qttWater -= 350;
                qttMilk -= 75;
                qttCoffeeBeans -= 20;
                storedMoney += 7;
                qttDisposableCups --;
                break;
            //3 - cappuccino
            case "3":
                qttWater -= 200;
                qttMilk -= 100;
                qttCoffeeBeans -= 12;
                storedMoney += 6;
                qttDisposableCups --;
                break;
            default: System.out.println("Opção inválida");
                break;
        }
    }

    /**
     * Method responsible for replenishing machine supplies
     */
    public static void replenishSupplies(){
        System.out.println("Informe quantos ml(s) de água irá adicionar:");
        qttWater += scanner.nextDouble();
        System.out.println("Informe quantos ml(s) de leite irá adicionar:");
        qttMilk += scanner.nextDouble();
        System.out.println("Informe quantos g(s) de grãos de café irá adicionar:");
        qttCoffeeBeans += scanner.nextDouble();
        System.out.println("Informe quantos copos descartáveis irá adicionar");
        qttDisposableCups += scanner.nextInt();
    }

    /**
     *Method used to withdraw money stored in the coffee machine
     */
    public static void takeStoredMoney(){
        System.out.println("Você retirou R$ " + storedMoney);
        storedMoney = 0.00;
    }
}
