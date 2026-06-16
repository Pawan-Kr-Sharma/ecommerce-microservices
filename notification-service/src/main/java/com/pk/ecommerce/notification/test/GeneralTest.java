package com.pk.ecommerce.notification.test;

import java.util.Arrays;

public class GeneralTest {

	public static void main(String[] args) {

		// Input: arr[] = [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
		// Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]

		// int[] nums = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
		// System.out.println(Arrays.toString(segregateoand1(nums)));

		// Input: nums = [0,0,1,1,1,2,2,3,3,4]
		// Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
		// int[] nums = {0,0,1,1,1,2,2,3,3,4};
		// System.out.println(removeDuplicates(nums));

		int[] nums = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
		System.out.println(removeDuplicateswithCondition(nums));
	}

	private static int removeDuplicateswithCondition(int[] nums) {
		int k = 2;
		int count = 0;
		int n = nums.length - 1;
		int i = 0;
		int j = 1;
		int[] ans = new int[nums.length];
		while (j < n) {

			if (nums[i] == nums[j]) {

				if (k >= count) {
					//nums[i] = nums[i];
					ans[i] = nums[i];
					i++;
					// nums[i] = nums[j];
					count++;
				} else {

					count = 0;
				}
				j++;
			}
			if (nums[i] != nums[j]) {
				i++;
				//nums[i] = nums[j];
				ans[i] = nums[j];
				j++;
			}
		}
		System.out.println(Arrays.toString(ans));

		return i + 1;
	}

	private static int removeDuplicates(int[] nums) {
		int n = nums.length - 1;
		int i = 0;
		int j = 1;

		while (j < n) {

			if (nums[i] == nums[j]) {
				j++;
			}

			if (nums[i] != nums[j]) {
				i++;
				nums[i] = nums[j];
				j++;
			}
		}
		return i + 1;
	}

	private static int[] segregateoand1(int[] nums) {
		// TODO Auto-generated method stub
//		int n=nums.length;
		int i = 0;
		int j = nums.length - 1;

		while (i < j) {

			if (nums[i] == 0) {
				i++;
			}
			if (nums[j] == 1) {
				j--;
			}

			if (nums[i] == 1 && nums[j] == 0) {

				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
				j--;
			}
		}
		return nums;
	}
}
