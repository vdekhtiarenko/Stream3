import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private static final int SEQUENTIAL_THRESHOLD = 2;
    private static final int MAX_LONG_FACTORIAL_INPUT = 20;

    private final int start;
    private final int end;

    public FactorialTask(int n) {
        if (n < 0 || n > MAX_LONG_FACTORIAL_INPUT) {
            throw new IllegalArgumentException("n must be between 0 and 20 for a long result");
        }
        this.start = 1;
        this.end = n;
    }

    private FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (start > end) return 1L;

        if (end - start + 1 <= SEQUENTIAL_THRESHOLD) {
            long product = 1L;
            for (int factor = start; factor <= end; factor++) {
                product *= factor;
            }
            return product;
        }

        int middle = start + (end - start) / 2;
        FactorialTask leftTask = new FactorialTask(start, middle);
        FactorialTask rightTask = new FactorialTask(middle + 1, end);

        leftTask.fork();
        long rightProduct = rightTask.compute();
        long leftProduct = leftTask.join();
        return leftProduct * rightProduct;
    }
}
