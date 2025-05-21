package ticket_reservation;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        TicketPool pool = new TicketPool(6);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        String[] customers = {
            "Alice", "Bob", "Carlos", "Diana", "Eli", "Fatima", "George", "Hanna", "Iris", "Jay"
        };

        for (String customer : customers) {
            executor.submit(new Customer(customer, pool));
        }

        executor.shutdown(); // Initiates shutdown but doesn't block

        try {
            // Wait up to 5 seconds for all tasks to complete
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Some customer threads did not finish in time.");
            } else {
                System.out.println("All customers finished their attempts.");
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted while waiting.");
            Thread.currentThread().interrupt();
        }
    }
}
