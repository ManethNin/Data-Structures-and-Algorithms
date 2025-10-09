class SenateBusStop {
    private int waitingRiders = 0;
    private int ridersToBoard = 0;
    private final int CAPACITY = 50;
    private boolean busArrived = false;
    
    public synchronized void riderArrives() throws InterruptedException {
        // Wait if a bus is currently boarding
        while (busArrived) {
            wait();
        }
        
        // Register as waiting
        waitingRiders++;
        
        // Wait for bus to arrive and assign us a spot
        while (!busArrived || ridersToBoard == 0) {
            wait();
        }
        
        // Board the bus
        ridersToBoard--;
        waitingRiders--;
        
        System.out.println(Thread.currentThread().getName() + " boarded");
        
        notifyAll(); // Notify bus we've boarded
    }
    
    public synchronized void busArrives() throws InterruptedException {
        busArrived = true;
        
        // Determine how many can board
        ridersToBoard = Math.min(waitingRiders, CAPACITY);
        
        // If no riders, leave immediately
        if (ridersToBoard == 0) {
            System.out.println("Bus departing with 0 riders");
            busArrived = false;
            notifyAll();
            return;
        }
        
        int expectedRiders = ridersToBoard;
        notifyAll(); // Signal riders they can board
        
        // Wait until all assigned riders have boarded
        while (ridersToBoard > 0) {
            wait();
        }
        
        System.out.println("Bus departing with " + expectedRiders + " riders");
        
        // Done boarding
        busArrived = false;
        notifyAll(); // Wake up riders who arrived during boarding
    }
    
    public static void main(String[] args) {
        SenateBusStop busStop = new SenateBusStop();
        
        // Bus thread - arrives every 20 minutes on average (exponentially distributed)
        Thread busThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    // Exponential distribution: mean = 20 minutes = 1200 seconds
                    double busDelay = -Math.log(1 - Math.random()) * 1200;
                    Thread.sleep((long) (busDelay * 10)); // Scale down for demo (divide by 100)
                    
                    System.out.println("\n=== Bus " + i + " arriving ===");
                    busStop.busArrives();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Rider threads - arrive every 30 seconds on average (exponentially distributed)
        Thread riderGenerator = new Thread(() -> {
            try {
                int riderId = 1;
                long simulationEnd = System.currentTimeMillis() + 60000; // Run for 1 minute
                
                while (System.currentTimeMillis() < simulationEnd) {
                    final int currentRiderId = riderId++;
                    
                    // Create rider thread
                    Thread rider = new Thread(() -> {
                        try {
                            System.out.println("Rider-" + currentRiderId + " arrived and waiting");
                            busStop.riderArrives();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return; // Exit gracefully on interrupt
                        }
                    }, "Rider-" + currentRiderId);
                    
                    rider.setDaemon(true); // Make rider threads daemon threads
                    rider.start();
                    
                    // Exponential distribution: mean = 30 seconds
                    double riderDelay = -Math.log(1 - Math.random()) * 30;
                    Thread.sleep((long) (riderDelay * 10)); // Scale down for demo
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        System.out.println("=== Senate Bus Simulation Started ===");
        System.out.println("Bus capacity: 50 riders");
        System.out.println("Bus arrival: ~20 min intervals (exponential)");
        System.out.println("Rider arrival: ~30 sec intervals (exponential)");
        System.out.println("=====================================\n");
        
        busThread.start();
        riderGenerator.start();
        
        try {
            // Wait for both main threads to complete
            busThread.join();
            riderGenerator.join();
            
            // Give a moment for any remaining operations
            Thread.sleep(1000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== Simulation Complete ===");
        System.exit(0); // Force program termination
    }
}