import java.util.Scanner;
public class BellmanFord {
    static final int INF = 999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices: ");
        int n = sc.nextInt();
        int[][] w = new int[n+1][n+1];
        System.out.println("Enter adjacency matrix (use 999 for no-edge):");
        for (int i=1;i<=n;i++) for (int j=1;j<=n;j++) w[i][j] = sc.nextInt();
        System.out.print("Source vertex: "); int src = sc.nextInt();

        int[] dist = new int[n+1];
        for (int i=1;i<=n;i++) dist[i] = INF;
        dist[src] = 0;

        for (int k=1;k<=n-1;k++) {
            for (int i=1;i<=n;i++)
                for (int j=1;j<=n;j++)
                    if (w[i][j] != INF && dist[i] + w[i][j] < dist[j])
                        dist[j] = dist[i] + w[i][j];
        }
        // check negative cycle
        for (int i=1;i<=n;i++)
            for (int j=1;j<=n;j++)
                if (w[i][j] != INF && dist[i] + w[i][j] < dist[j]) {
                    System.out.println("Graph has negative weight cycle");
                    return;
                }

        for (int i=1;i<=n;i++) System.out.println("dist to " + i + " = " + dist[i]);
    }
}
