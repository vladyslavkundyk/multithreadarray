package threads3;

public class ArrayMultiThread implements Runnable {

	private int[] array;
	private int arrayStart;
	private int arrayEnd;
	private long result;
	private Thread thread;

	public ArrayMultiThread(int[] array, int arrayStart, int arrayEnd) {
		super();
		this.array = array;
		this.arrayStart = arrayStart;
		this.arrayEnd = arrayEnd;
		thread = new Thread(this);
		thread.start();
	}

	public long getResult() {
		return result;
	}

	public Thread getThread() {
		return thread;
	}

	// Подсчет суммы простым алгоритмом

	public static long singleThreadSum(int[] array) {
		long singleThreadArraySum = 0;
		for (int i = 0; i < array.length; i++) {
			singleThreadArraySum += array[i];
		}
		return singleThreadArraySum;
	}

	// Многопоточный подсчет суммы

	public static long multiThreadSum(int[] arr) {

		int threadCount = Runtime.getRuntime().availableProcessors() * 2;

		ArrayMultiThread[] threadArray = new ArrayMultiThread[threadCount];

		int arraySize = arr.length / threadCount;
		int arrayStart = 0;
		int arrayEnd = arraySize;
		for (int i = 0; i < threadArray.length; i++) {
			if (i == threadArray.length - 1) {
				arrayEnd = arr.length - 1;
			}
			threadArray[i] = new ArrayMultiThread(arr, arrayStart, arrayEnd);
			arrayStart += arraySize + 1;
			arrayEnd += arraySize + 1;
		}

		for (int i = 0; i < threadArray.length; i++) {
			try {
				threadArray[i].getThread().join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		long multiThreadArraySum = 0;
		for (int i = 0; i < threadArray.length; i++) {
			multiThreadArraySum += threadArray[i].getResult();
		}
		return multiThreadArraySum;
	}

	@Override
	public void run() {
		for (int i = arrayStart; i <= arrayEnd; i++) {
			result += array[i];
		}
	}
}
