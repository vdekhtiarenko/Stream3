import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10;
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        try {
            long result = forkJoinPool.invoke(new FactorialTask(n));
            System.out.println("Факториал " + n + "! = " + result);
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
