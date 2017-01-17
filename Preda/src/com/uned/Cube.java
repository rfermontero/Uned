package com.uned;


import java.util.Arrays;
import java.util.Random;

/**
 * Se dispone de n cubos indentificados del 1 a n. Cada cubo tiene en una de sus caras una letra distinta. Se india
 * una palabra de n letras.
 * Colocar los n cubos uno a continuacion del otro de forma que con esa disposicion se forme la palabra dada.
 * En diferentes cubos puede haber letras repetidas, la solucion puede ser no unica o no existir.
 */
public class Cube {

	public static void main(String[] args) {
		String wordToSearch = args[0];
		int n = wordToSearch.length();

		char[][] matrixCube = new char[n][n];
		fillCubesWithRandomLetters(matrixCube);

		int solution[][] = new int[n][2];
		for (int[] aSolution : solution) {
			Arrays.fill(aSolution, -1);
		}

		System.out.println("Word to search is: " + wordToSearch);

		for (char[] aMatrixCube : matrixCube) {
			for (char anAMatrixCube : aMatrixCube) {
				System.out.print(anAMatrixCube);
			}
			System.out.println();
		}

		search(wordToSearch, matrixCube, solution, 0);
	}

	private static boolean search(String wordToSearch, char[][] matrixCube, int[][] solution, int searchLetterPosition) {
		if (solution[solution.length - 1][0] != -1) {
			printSolution(solution);
			return true;
		}
		char letterToFound = wordToSearch.charAt(searchLetterPosition);
		for (int cube = 0; cube < matrixCube.length; cube++) {
			if (cubeIsFree(solution, cube) && !isSolved(solution)) {
				for (int faceInCube = 0; faceInCube < matrixCube[cube].length; faceInCube++) {
					boolean letterFounded = matrixCube[cube][faceInCube] == letterToFound;
					boolean nextLetterFounded;
					if (letterFounded) {
						solution[searchLetterPosition][0] = cube;
						solution[searchLetterPosition][1] = faceInCube;
						nextLetterFounded = search(wordToSearch, matrixCube, solution, searchLetterPosition + 1);
						if (!nextLetterFounded && cube == matrixCube.length - 1 && !isSolved(solution)) {
							solution[Math.max(searchLetterPosition - 1, 0)][0] = -1;
							solution[Math.max(searchLetterPosition - 1, 0)][1] = -1;
						}
					}
				}
			}
		}
		return solution[searchLetterPosition][0] != -1;
	}

	private static boolean isSolved(int[][] solution) {
		for (int[] aSolution : solution) {
			if (aSolution[0] == -1) {
				return false;
			}
		}
		return true;
	}

	private static void printSolution(int[][] solution) {
		System.out.println("Solution is:");
		for (int[] cubeAndFace : solution) {
			System.out.printf("%s %d%n", cubeAndFace[0], cubeAndFace[1]);
		}
	}

	private static boolean cubeIsFree(int[][] solution, int cube) {
		for (int[] aSolution : solution) {
			if (aSolution[0] == cube) {
				return false;
			}
		}
		return true;
	}

	private static void fillCubesWithRandomLetters(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (char) (new Random().nextInt(8) + 97);
			}
		}
	}

}
