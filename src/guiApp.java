import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class guiApp extends JFrame {
    private JPanel peer, floors;
    private ArrayList<Traveler> [] floorsArray;
    public guiApp(Elevator elevator){
        super("Elevator");
        this.floorsArray = getFloors(elevator.waiting);
        initFrame();
        this.peer = new JPanel(new BorderLayout());
        peer.add(new Label("peer"));
        peer.setBorder(new LineBorder(Color.BLACK, 1));
        peer.setPreferredSize(new Dimension(50,300));
        this.getContentPane().add(peer);
        this.floors  = new JPanel(new BorderLayout());
        floors.add(new Label("hello"));
        this.getContentPane().add(floors);

    }

    private void initFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }
    private static ArrayList<Traveler> [] getFloors(ArrayList<Traveler> travelers){
        ArrayList<Traveler>[] result = new ArrayList[10];
        travelers.forEach(traveler -> {
            result[traveler.getSrc()].add(traveler);
        });
        return result;
    }

}
