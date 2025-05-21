package ticket_reservation;

public class TicketPool {
    private int remaining;

    public TicketPool(int total) {
        this.remaining = total;
    }

    public synchronized Ticket reserve(String buyer) {
        if (remaining <= 0) {
          
            System.out.println("Sorry " + buyer + ", no tickets left at this time.");
            return null;
        }

        remaining--;
        // Ticket ID is from total sold and not remaining
        Ticket t = new Ticket((int)(Math.random() * 10000), "LiveMusicFest");
        System.out.println(">>> " + buyer + " got ticket #" + t.getId());
        return t;
    }

    public synchronized int ticketsLeft() {
        return remaining;
    }
}
