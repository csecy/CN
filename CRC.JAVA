// CRC.java -- simplified CRC remainder (bit strings)
import java.util.Scanner;

public class CRC {
    // XOR divide: returns remainder as string
    static String xorDivide(String data, String poly) {
        int n = poly.length();
        StringBuilder dividend = new StringBuilder(data);
        for (int i = 0; i <= data.length() - n; ) {
            if (dividend.charAt(i) == '1') {
                for (int j = 0; j < n; j++) {
                    char bit = dividend.charAt(i + j) == poly.charAt(j) ? '0' : '1';
                    dividend.setCharAt(i + j, bit);
                }
            }
            // advance to next '1' or next position
            i++;
            while (i < dividend.length() && dividend.charAt(i) == '0') i++;
        }
        // remainder = last n-1 bits
        return dividend.substring(dividend.length() - (n - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message bits (e.g. 1101):");
        String msg = sc.nextLine().trim();
        System.out.println("Enter generator polynomial bits (e.g. 10001000000100001 for CRC-16-CCITT):");
        String poly = sc.nextLine().trim();
        // append (n-1) zeros
        String augmented = msg + "0".repeat(poly.length() - 1);
        String rem = xorDivide(augmented, poly);
        String tx = msg + rem;
        System.out.println("Remainder: " + rem);
        System.out.println("Transmitted frame: " + tx);

        // receiver check
        System.out.println("Enter received frame to check (or press Enter to skip):");
        String recv = sc.nextLine().trim();
        if (!recv.isEmpty()) {
            String rrem = xorDivide(recv, poly);
            boolean ok = rrem.chars().allMatch(c -> c == '0');
            System.out.println(ok ? "No error detected" : "Error detected! remainder=" + rrem);
        }
    }
}
