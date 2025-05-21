package ticket_reservation;

public class Customer implements Runnable {
    private final String person;
    private final TicketPool source;

    public Customer(String name, TicketPool pool) {
        this.person = name;
        this.source = pool;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long)(Math.random() * 200)); // simulate staggered access
        } catch (InterruptedException e) {
            System.err.println("Thread for " + person + " was interrupted.");
        }

        Ticket t = source.reserve(person);

        if (t != null) {
            System.out.println("✅ " + person + " confirmed ticket: " + t.getId());
        } else {
            System.out.println("❌ " + person + " could not get a ticket.");
        }
    }
}
