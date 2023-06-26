import javax.swing.*;

import java.util.ConcurrentModificationException;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Elevator elevator = new Elevator();

        int []errCounter = new int[1];
        errCounter[0] = 0;
        Thread moveElevator = new Thread(()->{
            while (true){
                try {
                    if (elevator.passengers.size() == 0 && elevator.waiting.size() == 0){
                        sleep(100);
                    } else {
                        elevator.move();
                    }
                } catch (InterruptedException e) {
                    break;
                } catch (ConcurrentModificationException e){
                    errCounter[0]++;
                }
            }
        });
        moveElevator.start();

        int  i = 0;
        while (true){
            try {
                int rand = (int)((Math.random())*10_000); // rand (1ms - 10s)
                sleep(rand);
                new Traveler(elevator);
                if ((i++) >= 100){
                    moveElevator.interrupt();
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        System.out.println("Waiting");
        System.out.println(elevator.getWaiting());
        System.out.println("Passengers");
        System.out.println(elevator.getPassengers());
        System.out.println("Errors="+errCounter[0]);


//        JFrame frame = guiApp.show(elevator);

    }
}
