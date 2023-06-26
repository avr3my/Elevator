import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Elevator {
    byte currentFloor, heading;
    ArrayList<Traveler> passengers, waiting;
    Direction direction;

    public Elevator() {
        this.currentFloor = 0;
        this.passengers = new ArrayList<>();
        this.waiting = new ArrayList<>();
        this.direction = Direction.UP;
    }

    public ArrayList<Traveler> getPassengers() {
        return passengers;
    }

    public void move(Direction direction) throws InterruptedException {
        boolean newTraveler = false;
        sleep(1000);
        if (direction == Direction.UP) {
            this.currentFloor++;
        }
        if (direction == Direction.DOWN) {
            this.currentFloor--;
        }
        this.passengers.forEach(t->{
            if (t.getDest() == this.currentFloor){
                t.left();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.passengers.remove(t);
            }
        });
        this.waiting.forEach(t->{
            if (t.getSrc() == this.currentFloor && t.getDirection() == direction){
                t.entered();
                this.passengers.add(t);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        if (this.passengers.size() != 0){
            this.getDirections();
        }
        move();
    }

    public void move() throws InterruptedException {
        if (heading > currentFloor){
            move(Direction.UP);
        }
        if (heading < currentFloor){
            move(Direction.DOWN);
        }
        if (heading == currentFloor){
            getDirections();
        }
    }



    public ArrayList<Traveler> getWaiting() {
        return waiting;
    }

    public void callElevator(Traveler traveler) throws InterruptedException {
        this.waiting.add(traveler);
    }
    private void getDirections() throws InterruptedException {
        if (this.passengers.size() != 0){
            this.passengers.forEach(passenger -> {
                if(Math.abs(passenger.getDest() - currentFloor) > Math.abs(heading-currentFloor)){
                    heading = passenger.getDest();
                }
            });
        } else if (this.waiting.size() != 0) {
            this.waiting.forEach(passenger -> {
                if(Math.abs(passenger.getSrc() - currentFloor) > Math.abs(heading-currentFloor)){
                    heading = passenger.getSrc();
                }
            });
        }




        direction = heading>currentFloor?Direction.UP:Direction.DOWN;
        move(direction);

    }
}
