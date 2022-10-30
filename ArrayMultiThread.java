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

	public static long countSingleThread(int[] array) {
		long resultSingleThreadArray = 0;
		for (int i = 0; i < array.length; i++) {
			resultSingleThreadArray += array[i];
		}
		return resultSingleThreadArray;
	}

	// Многопоточный подсчет суммы

	public static long countMultiThread(int[] array) {

		int threadCount = Runtime.getRuntime().availableProcessors() * 2;

		ArrayMultiThread[] threadArray = new ArrayMultiThread[threadCount];

		int arraySize = array.length / threadCount;
		int arrayStart = 0;
		int arrayEnd = arraySize;
		for (int i = 0; i < threadArray.length; i++) {
			if (i == threadArray.length - 1) {
				arrayEnd = array.length - 1;
			}
			threadArray[i] = new ArrayMultiThread(array, arrayStart, arrayEnd);
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

		long resultMultiThreadArray = 0;
		for (int i = 0; i < threadArray.length; i++) {
			resultMultiThreadArray += threadArray[i].getResult();
		}
		return resultMultiThreadArray;
	}

	@Override
	public void run() {
		for (int i = arrayStart; i <= arrayEnd; i++) {
			result += array[i];
		}
	}
}
