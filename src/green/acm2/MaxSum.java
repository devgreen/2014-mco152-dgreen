package green.acm2;

import java.util.Scanner;

public class MaxSum {

	/*
	 * public MaxSum(int[][] maxSum) { Scanner keyboard = new
	 * Scanner(System.in); // maxSum = new int[size][size]; for (int i = 0; i <
	 * maxSum.length; i++) { for (int j = 0; j < maxSum.length; j++) {
	 * maxSum[i][j] = keyboard.nextInt();
	 * 
	 * } }
	 * 
	 * }
	 */

	public int getMax(int maxSum[][]) {
		int[] temp = new int[maxSum.length];
		int currMax = 0;
		int min = 0;

		for (int i = maxSum.length - 1; i >= 0; i--) {
			for (int j = maxSum.length - 1; j >= 0; j--) {
				temp[j] = 0;
			}
			for (int k = i; k >= 0; k--) {
				for (int l = maxSum.length - 1; l >= 0; l--) {
					if (min == 0 && maxSum[l][k] < min) {
						min = maxSum[l][k];
					} else if (min < maxSum[l][k]) {
						min = maxSum[k][l];
					}
					temp[l] += maxSum[l][k];
				}

				int maxSoFar = kadane(temp);
				if (maxSoFar > currMax) {
					currMax = maxSoFar;
				}

			}
		}
		if (currMax == 0) {
			return min;
		}

		return currMax;

	}

	public int kadane(int[] temp) {
		int max = 0;
		int currMax = 0;

		for (int i = 0; i < temp.length; i++) {
			currMax += temp[i];
			if (currMax < 0) {
				currMax = 0;
			}
			if (max < currMax) {
				max = currMax;

			}

		}
		return max;

	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();
		int maxSum[][] = new int[size][size];
		for (int i = 0; i < maxSum.length; i++) {
			for (int j = 0; j < maxSum.length; j++) {
				maxSum[i][j] = keyboard.nextInt();

			}
		}
		MaxSum problem = new MaxSum();
		int answer = problem.getMax(maxSum);
		System.out.println(answer);
		keyboard.close();

	}

}
