package WordSort;

import java.util.*;
import java.io.*;

/*알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.

길이가 짧은 것부터
길이가 같으면 사전 순으로
단, 중복된 단어는 하나만 남기고 제거해야 한다.
*/

public class WordSort {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int Num = Integer.parseInt(bf.readLine());
		
		String[] arr = new String[Num];
		
		for(int i = 0; i < Num; i++) {
			arr[i] = bf.readLine();
		}
		
		mergeSort(arr, 0, Num - 1);
		
		// 중복 제거 set은 중복을 허용하지 않음
		Set<String> set = new LinkedHashSet<>(Arrays.asList(arr)); 
		
		for(String i : set) {
			bw.write(i + "\n");
		}
		bw.flush();
		bw.close();
		
	}
	//병합정렬을 통해 중복을 허용한 채로 정렬
	public static void mergeSort(String[] arr, int left, int right) {
		if(left < right) {
			int middle = (left + right) / 2;
			
			mergeSort(arr, left, middle);
			mergeSort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	
	public static void merge(String[] arr, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		String[] arrleft = new String[n1];
		String[] arrright = new String[n2];
		
		for(int i = 0; i < n1; i++) {
			arrleft[i] = arr[i + left];
		}
		for(int i = 0; i < n2; i++) {
			arrright[i] = arr[i + middle + 1];
		}
		
		int i = 0; int j =0; int k = left;
		while(i < n1 && j < n2) {
			if(arrleft[i].length() < arrright[j].length()) {
					arr[k] = arrleft[i];
					i++;
				
			}
			else if(arrright[j].length() < arrleft[i].length()) {
				arr[k] = arrright[j];
				j++;
			}
			else {
				if(arrleft[i].compareTo(arrright[j]) <= 0) {
					arr[k] = arrleft[i];
					i++;
				}
				else {
					arr[k] = arrright[j];
					j++;
				}
			}
			k++;
		}
		
		while(i < n1) {
			arr[k] = arrleft[i];
			i++;
			k++;
		}
		while(j < n2) {
			arr[k] = arrright[j];
			j++;
			k++;
		}
	
	}
}
