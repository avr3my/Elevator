public class Log {
    public static String travelerStats(Traveler t){
        int traveled = Math.abs(t.getSrc() - t.getDest());
        int waitTime = (t.getEntranceTime() - t.getCallTime());
        int travelTime = (t.getLeavingTime() - t.getEntranceTime());
        double ratio = calcRatio(traveled, waitTime, travelTime);
        return t.getName()+
                ": {Floors traveled: " + traveled +
                ". Wait time: " + waitTime +
                ". Travel time: " + travelTime +
                "}"
                ;
    }
    private static double calcRatio(int traveled, int waitTime, int travelTime ){
        return 1;
    }
}
