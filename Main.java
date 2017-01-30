import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		obj.setBounds(10, 10, 700, 600); //set size of frame
		obj.setTitle("ZX Ball"); //set title of frame
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
		System.out.println("hey");
	}

}
