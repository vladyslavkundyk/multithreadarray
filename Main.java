package threads2;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		// Подсчет суммы простым алгоритмом из одного массива

		Random rannum = new Random();
		Thread thr = Thread.currentThread();

		double timeTest = System.currentTimeMillis();
		int arrayTest[] = new int[200000000];
		long resultTest = 0;

		for (int i = 0; i < arrayTest.length; i++) {
			arrayTest[i] = (rannum.nextInt(arrayTest.length));
		}
		for (int i = 0; i < arrayTest.length; i++) {
			resultTest = resultTest + arrayTest[i];
		}

		double timeResultTest = ((double) System.currentTimeMillis() - timeTest);
		System.out.println("Thread-" + thr.getName() + "\nСумма одного массива: " + resultTest);
		System.out.println("Скорость: " + timeResultTest + " ms");
		System.out.println();

		// Многопоточный подсчет суммы из четырех массивов

		double time = System.currentTimeMillis();
		long result = 0;
		int array[] = new int[200000000];

		for (int i = 0; i < array.length; i++) {
			array[i] = (rannum.nextInt(array.length));
		}

		ArrayMultiThread array1 = new ArrayMultiThread(array, 0, 50000000);
		ArrayMultiThread array2 = new ArrayMultiThread(array, 50000000, 100000000);
		ArrayMultiThread array3 = new ArrayMultiThread(array, 100000000, 150000000);
		ArrayMultiThread array4 = new ArrayMultiThread(array, 150000000, 199999999);

		Thread thread1 = new Thread(array1);
		Thread thread2 = new Thread(array2);
		Thread thread3 = new Thread(array3);
		Thread thread4 = new Thread(array4);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		result = array1.getResult() + array2.getResult() + array3.getResult() + array4.getResult();
		double timeResult = ((double) System.currentTimeMillis() - time);
		System.out.println();
		System.out.println("Сумма из четырёх массивов: " + result);
		System.out.println("Скорость: " + timeResult + " ms");

		double timeResultDifference = timeResultTest - timeResult;
		System.out.println();
		System.out.println("Разница в скорости: " + timeResultDifference + " ms");
	}
}
