package org.jetbrains.coffee_machine;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		//Required variables
	     Scanner scanner = new Scanner(System.in);
	     //Data input
	     System.out.println("Informe a quantidade de água disponível (ml): ");
	     Integer qtdWater = scanner.nextInt();
	     System.out.println("Informe a quantidade de leite disponível (ml): ");
	     Integer qtdMilk = scanner.nextInt();
	     System.out.println("Informe a quantidade de grãos de café disponíveis (g): ");
	     Integer qtdCoffee = scanner.nextInt();
	     System.out.println("Informe quantas xícaras de café deseja: ");
	     Integer qtdCupsNeeded = scanner.nextInt();
	     //How many can we produce ?
	     Integer qtdCanBeProduce = howMuchCanIProduce(qtdWater, qtdMilk, qtdCoffee);
	     if (qtdCupsNeeded > qtdCanBeProduce) {
	         System.out.println("Desculpe, atualmente posso preparar apenas " + qtdCanBeProduce + " café(s)");
	     } else if (qtdCupsNeeded < qtdCanBeProduce) {
	         System.out.println("Sim, eu posso preparar a quantidade solicitada (ou até mais " + (qtdCanBeProduce - qtdCupsNeeded) + ", caso deseje)");
	     } else {
	         System.out.println("Sim, eu posso preparar esta quantidade.");
	     }
	     scanner.close();
	}
	
	/**
	 * Function used to display information about the amount of ingredients needed to prepare a given number 
	 * of cups of coffee
	 * @param int qtdXicaras number of coffee cups to be prepared
	 * @return Text containing the amount of ingredients needed
	 */
	public static String infoQttNecessaryIngredients(int qttCups) {
		//variable for text concatenation
		StringBuilder sb = new StringBuilder();
		//building result text
		sb.append("Para preparar " + qttCups + " xícaras de café são necessários : ");
		sb.append("\n - " + qttCups * 200 + " ml de água");
		sb.append("\n - " + qttCups * 50 + " ml de leite");
		sb.append("\n - " + qttCups * 15 + " g de grãos de café");
		//returning concatenated text
		return sb.toString();
	}
	
	/**
     * Method used to calculate the amount of coffee the machine can brew
     * @param water amount of ml water the machine has
     * @param milk amount of ml milk the machine has
     * @param coffee amount of g of coffe beans the machine has
     * @return qtdCanBeProduce amount of cups that can be produced
     */
    public static Integer howMuchCanIProduce(int water, int milk, int coffee){
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
}
