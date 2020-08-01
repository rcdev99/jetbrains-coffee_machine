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
    //Main
    public static void main(String[] args) {
        //Required variables
        String instruction;
        //Execution
        displaySupplyInformation();
        //Identifying action to be taken
        System.out.println("Informe a ação desejada (comprar, repor, sacar):");
        instruction = scanner.next();
        executeSelectedInstruction(instruction);
    }
    //Methods
    /**
     * Method used to calculate the amount of coffee the machine can brew
     * @param water amount of ml water the machine has
     * @param milk amount of ml milk the machine has
     * @param coffee amount of g of coffe beans the machine has
     * @return qtdCanBeProduce amount of cups that can be produced
     */
    public static Integer verifyCapacityProduction(int water, int milk, int coffee){
        //Local variables
        Integer qtdCanBeProduce = 0;
        boolean canIProduce = true;
        //Processing
        do {
            water -= 200; //amount of water needed per cup
            milk -= 50; //amount of milk needed per cup
            coffee -= 15; //amount of coffee needed per cup
            //Can i produce ?
            if (water >= 0 && milk >= 0 && coffee >= 0) {
                qtdCanBeProduce++;
            }else{
                canIProduce = false;
            }
        } while (canIProduce);
        //Returns
        return qtdCanBeProduce;
    }

    /**
     * Method used to show current supply information
     */
    public static void displaySupplyInformation(){
        System.out.println("\nSua Coffee Machine tem:");
        System.out.println(qttWater + " ml(s) de água");
        System.out.println(qttMilk + " ml(s) de leite");
        System.out.println(qttCoffeeBeans + " (g) de café em grão");
        System.out.println(qttDisposableCups + " copos descartáveis disponíveis");
        System.out.println("R$ " + storedMoney + " em caixa");
    }

    /**
     * Method used to execute the instruction selected by user
     * @param selectedInstruction Option selected by user
     */
    public static void executeSelectedInstruction(String selectedInstruction){
        switch (selectedInstruction) {
            case "comprar":
                System.out.println("Qual tipo de café você deseja? 1 - Expresso, 2 - Café com leite, 3 - Cappuccino:");
                int option = scanner.nextInt();
                buyCoffee(option);
                displaySupplyInformation();
                break;
            case "repor":
                replenishSupplies();
                displaySupplyInformation();
                break;
            case "sacar":
                takeStoredMoney();
                displaySupplyInformation();
                break;
            default: System.out.println("Opção inválida");
                break;
        }
    }

    /**
     * Method used to buy a coffee and update the supply level after purchase
     * @param typeCoffee
     */
    public static void buyCoffee(int typeCoffee) {
        //Decreasing quantity of cups
        qttDisposableCups --;
        //Updating the supply level according to the type of chosen coffee
        switch (typeCoffee) {
            //1 - espresso
            case 1:
                qttWater -= 250;
                qttCoffeeBeans -= 16;
                storedMoney += 4;
                break;
            //2 - latte
            case 2:
                qttWater -= 350;
                qttMilk -= 75;
                qttCoffeeBeans -= 20;
                storedMoney += 7;
                break;
            //3 - cappuccino
            case 3:
                qttWater -= 200;
                qttMilk -= 100;
                qttCoffeeBeans -= 12;
                storedMoney += 6;
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
