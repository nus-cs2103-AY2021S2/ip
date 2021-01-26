import java.io.*;
import java.util.*;

/**
 * Eases the use of Buffered Reader for faster I/O operations.
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter {

    /** BufferedReader object to read input */
    BufferedReader br;

    /** Indicates String */
    StringTokenizer st;

    /**
     * Creates new FastIO object to read input.
     */
    public FastIO() {
        super(new BufferedOutputStream(System.out)); 
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads next input as a String.
     *
     * @return String of next input.
     */
    public String next() {
        while (st == null || !st.hasMoreElements()) {
            try
            {
                st = new StringTokenizer(br.readLine()); 
            } catch (IOException e) {
                e.printStackTrace(); 
            } 
        }
        return st.nextToken(); 
    }

    /**
     * Reads next input as an Integer.
     *
     * @return Integer of the next input.
     */
    int nextInt() {
        return Integer.parseInt(next()); 
    }

    /**
     * Reads next input as a Long.
     *
     * @return Long of the next input.
     */
    long nextLong() {
        return Long.parseLong(next()); 
    }

    /**
     * Reads next input as a Double.
     *
     * @return Double of the next input.
     */
    double nextDouble() {
        return Double.parseDouble(next()); 
    }

    /**
     * Reads the next line of input as a String.
     *
     * @return String consisting of next line of input.
     */
    String nextLine() {
        String str = ""; 
        try {
            str = br.readLine(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        } 
        return str; 
    } 
}

