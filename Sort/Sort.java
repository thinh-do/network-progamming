package Exercise_Week1;

public class Sort {
	public void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			boolean isSorted = true;
			for (int j = 0; j < n - i - 1; j++) {// vì phần từ j+1<n, và sau mỗi vòng lặp i số lượng phần tử
				if (arr[j] > arr[j + 1]) { // sẽ giảm đi 1 nên j+1<n-i <=> j<n-i-1
					isSorted = false;
					int t = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = t;
				}

			}
			printArr(i, arr);
			if (isSorted)
				break;
		}

	}

	public void insertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int ai = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > ai) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = ai;
			printArr(i, arr);
		}

	}

	public void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min])
					min = j;
			}
			if (min != i) {
				int t = arr[i];
				arr[i] = arr[min];
				arr[min] = t;

			}
			printArr(i, arr);
		}
	}

	private static void printArr(int i, int[] arr) {
		System.out.print(i + ": ");
		for (int j = 0; j < arr.length; j++) {
			System.out.printf("%d ", arr[j]);
		}
		System.out.println();

	}

	public static void main(String[] args) {
		Sort s = new Sort();
		int[] arr = { 5, 3, 2, 7, 8, 1, 2 };
		// s.bubbleSort(arr);
		// s.insertionSort(arr);
		s.selectionSort(arr);
	}

}
