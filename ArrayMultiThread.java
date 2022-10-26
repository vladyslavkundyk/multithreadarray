package threads2;

public class ArrayMultiThread implements Runnable {

	private long result;
	private int[] array;
	private int arrayStart;
	private int arrayFinish;

	public ArrayMultiThread(int[] array, int arrayStart, int arrayFinish) {
		super();
		this.array = array;
		this.arrayStart = arrayStart;
		this.arrayFinish = arrayFinish;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getArrayStart() {
		return arrayStart;
	}

	public void setArrayStart(int arrayStart) {
		this.arrayStart = arrayStart;
	}

	public int getArrayFinish() {
		return arrayFinish;
	}

	public void setArrayFinish(int arrayFinish) {
		this.arrayFinish = arrayFinish;
	}

	public long count(int[] array) {
		for (int i = arrayStart; i <= arrayFinish; i++) {
			result = array[i];
		}
		return result;
	}

	@Override
	public void run() {

		Thread thr = Thread.currentThread();
		System.out.println(thr.getName() + " 1/4 из массива = " + count(array));
	}
}
