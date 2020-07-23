package org.jetbrains.coffee_machine;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		//Variáveis necessárias
		Scanner scanner = new Scanner(System.in);
		//Inicializando sistema
		System.out.println("Seja bem-vindo a sua Coffe-Machine !");
		System.out.println("\nInforme quantas xícaras de café deseja: ");
		int qtdXicaras = scanner.nextInt();
		scanner.close();
		System.out.println(infoQtdIngredientesNecessario(qtdXicaras));
	}
	
	/**
	 * Função utilizada para exibir informações sobre a quantidade de ingredientes necessários para a preparação de um dado
	 * número de xícaras de café
	 * @return
	 * @param int qtdXicaras número de xícaras a serem preparadas
	 */
	public static String infoQtdIngredientesNecessario(int qtdXicaras) {
		//Instanciando variável que conterá o texto do resualtado
		StringBuilder sb = new StringBuilder();
		//Construindo texto do resultado
		sb.append("Para preparar " + qtdXicaras + " xícaras de café são necessários : ");
		sb.append("\n - " + qtdXicaras * 200 + " ml de água");
		sb.append("\n - " + qtdXicaras * 50 + " ml de leite");
		sb.append("\n - " + qtdXicaras * 15 + " g de grãos de café");
		//Retornando informação sobre a quantiade necessária de ingredientes
		return sb.toString();
	}
}
