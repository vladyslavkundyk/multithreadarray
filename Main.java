package threads3;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		int[] array1 = new int[200000000];
		Random rannum = new Random();
		for (int i = 0; i < array1.length; i++) {
			array1[i] = rannum.nextInt(10);
		}

		// Подсчет суммы простым алгоритмом

		long timeStart = System.currentTimeMillis();
		long result1 = ArrayMultiThread.singleThreadSum(array1);
		long timeStop = System.currentTimeMillis();
		long timeResult1 = timeStop - timeStart;
		System.out.println("Сумма через простой алгоритм: " + result1);
		System.out.println("Скорость: " + (timeResult1) + " ms");

		System.out.println();

		// Многопоточный подсчет суммы

		timeStart = System.currentTimeMillis();
		long result2 = ArrayMultiThread.multiThreadSum(array1);
		timeStop = System.currentTimeMillis();
		long timeResult2 = timeStop - timeStart;
		System.out.println("Сумма через многопоточность: " + result2);
		System.out.println("Скорость: " + (timeResult2) + " ms");
		
		System.out.println();
		System.out.println("Многопоточный вариант быстрее на: " + (timeResult1 - timeResult2) + " ms");
	}
}
