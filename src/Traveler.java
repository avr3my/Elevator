import java.util.Random;

public class Traveler {
    private final String name;
    private final int callTime;
    private int entranceTime, leavingTime;
    private final byte src, dest;
    private final Direction direction;

    public Traveler(Elevator elevator) throws InterruptedException {
        this.name = Names.getName();
        this.callTime = (int) (System.currentTimeMillis()/1000);
        this.src = getRandomSrc();
        this.dest = getRandomDest(this.src);
        this.direction = src>dest? Direction.DOWN:Direction.UP;
        elevator.callElevator(this);
    }

    public int getCallTime() { return callTime; }

    public byte getSrc() { return src; }

    public byte getDest() { return dest; }

    public String getName() { return name; }

    public int getEntranceTime() { return entranceTime; }

    public int getLeavingTime() { return leavingTime; }

    public Direction getDirection() { return direction; }

    private static byte getRandomSrc(){ return (byte)(Math.random()*10); }

    private static byte getRandomDest(byte src){
        byte rand = (byte) (Math.random() * 10);
        return (rand != src) ? rand: getRandomDest(src);
    }
    
    public void entered(){ this.entranceTime = (int) (System.currentTimeMillis()/1000); }

    public void left(){
        this.leavingTime = (int) (System.currentTimeMillis()/1000);
//        System.out.println(this);
        System.out.println(Log.travelerStats(this));
    }
    


    @Override
    public String toString() {
        return name +
                "\ncallTime=" + callTime%100 +
                ", entranceTime=" + entranceTime%100 +
                ", leavingTime=" + leavingTime%100 +
                "\nsrc=" + src +
                ", dest=" + dest +
                "\n";
    }
}
