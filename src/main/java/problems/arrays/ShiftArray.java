package main.java.problems.arrays;

// left right rotation of array
public class ShiftArray {
    public static void main(String[] args) {
        String str = "abcd";
        str = getShiftedString(str,1,2);
        System.out.println(str);
    }
    static String getShiftedString(String s, int left, int right) {
        // s = string to shift, left is left count, right is right count
        char[] original = s.toCharArray();
        rotateLeft(original, left);
        rotateLeft(original, right);
        return String.valueOf(original);
    }
    static void rotateRight(char[] arr, int r) {
        rotateLeft(arr, arr.length - r);
    }
    static void rotateLeft(char[] arr, int l) {
        // abcd , 2 => bcda => cdab
        int n = arr.length;
        reverse(arr, 0, l-1);   // bacd
        reverse(arr, l, n-1);      // badc
        reverse(arr, 0, n-1);   // cdab

    }
    static void reverse(char[] arr, int s, int e) {
        char temp;
        while( s < e) {
            temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++; e--;
        }
    }
}
