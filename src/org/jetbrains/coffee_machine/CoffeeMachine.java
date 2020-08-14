package org.jetbrains.coffee_machine;

import java.util.Scanner;

public class CoffeeMachine {
	public static Scanner scanner = new Scanner(System.in);
    public Double qttWater;
    public Double qttMilk;
    public Double qttCoffeeBeans;
    public Integer qttDisposableCups;
    public Double storedMoney;
    public boolean isAvailable = true;
    //Final variables
    public static final Integer waterNeededForEspresso = 250;
    public static final Integer waterNeededForLatte = 350;
    public static final Integer waterNeededForCappuccino = 200;
    public static final Integer milkNeededForEspresso = 0;
    public static final Integer milkNeededForLatte = 75;
    public static final Integer milkNeededForCappuccino = 100;
    public static final Integer coffeBeansNeededForEspresso = 16;
    public static final Integer coffeBeansNeededForLatte = 20;
    public static final Integer coffeBeansNeededForCappuccino = 12;
    public static final Integer priceOfEspresso = 4;
    public static final Integer priceOfLatte = 7;
    public static final Integer priceOfCappuccino = 6;

    /*Constructor*/
    CoffeeMachine(Double qttWater, Double qttMilk, Double qttCoffeeBeans, Integer qttDisposableCups, Double storedMoney){
        //ensuring valid values ​​in the main variables
        this.qttWater = qttWater >= 0 ? qttWater: 0;
        this.qttMilk = qttMilk >= 0 ? qttMilk : 0;
        this.qttCoffeeBeans = qttCoffeeBeans >= 0 ? qttCoffeeBeans : 0;
        this.qttDisposableCups = qttDisposableCups >= 0 ? qttDisposableCups : 0;
        this.storedMoney = storedMoney >= 0 ? storedMoney : 0;
    }

    /*Methods*/
    public boolean isAvailable() {
        if (isAvailable) {
            System.out.println("Informe a ação desejada (comprar, repor, retirada, recursos, sair):");
        }
        return isAvailable;
    }

    /**
     * Method responsible for checking the possibility of producing a certain type of coffee
     * @param typeCoffee
     * @return true if it is possible to produce
     */
    private boolean canIProduce (String typeCoffee){
        //Local variables
        Integer waterNeeded = 0;
        Integer coffeeNeeded = 0;
        Integer milkNeeded = 0;
        //filling variables ​according to the type of coffee chosen
        switch (typeCoffee) {
            //1 - Espresso
            case "1":
                waterNeeded = waterNeededForEspresso;
                coffeeNeeded = coffeBeansNeededForEspresso;
                milkNeeded = milkNeededForEspresso;
                break;
            //2 - latte
            case "2":
                waterNeeded = waterNeededForLatte;
                milkNeeded = milkNeededForLatte;
                coffeeNeeded = coffeBeansNeededForLatte;
                break;
            //3 - cappuccino
            case "3":
                waterNeeded = waterNeededForCappuccino;
                milkNeeded  = milkNeededForCappuccino;
                coffeeNeeded = coffeBeansNeededForCappuccino;
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
    public void displaySupplyInformation(){
    	System.out.println("\nSua Coffee-Machine tem:");
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
    public void executeSelectedInstruction(String selectedInstruction){
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
    public void buyCoffee(String typeCoffee) {
        //Updating the supply level according to the type of chosen coffee
        switch (typeCoffee) {
            //1 - espresso
            case "1":
                qttWater -= waterNeededForEspresso;
                qttCoffeeBeans -= coffeBeansNeededForEspresso;
                storedMoney += priceOfEspresso;
                qttDisposableCups --;
                break;
            //2 - latte
            case "2":
                qttWater -= waterNeededForLatte;
                qttMilk -= milkNeededForLatte;
                qttCoffeeBeans -= coffeBeansNeededForLatte;
                storedMoney += priceOfLatte;
                qttDisposableCups --;
                break;
            //3 - cappuccino
            case "3":
                qttWater -= waterNeededForCappuccino;
                qttMilk -= milkNeededForCappuccino;
                qttCoffeeBeans -= coffeBeansNeededForCappuccino;
                storedMoney += priceOfCappuccino;
                qttDisposableCups --;
                break;
            default: System.out.println("Sorry, invalid option");
                break;
        }
    }

    /**
     * Method responsible for replenishing machine supplies
     */
    public void replenishSupplies(){
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
    public void takeStoredMoney(){
    	System.out.println("Você retirou R$ " + storedMoney);
        storedMoney = 0.00;
    }
}
