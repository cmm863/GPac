import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GPac {
	public static void main(String[] args) {
		Population myPop = new Population(10, 7);
		Algorithm.setKSize(4);
		Algorithm.setMutationRate(0.05);
		myPop = Algorithm.evolvePopulation(myPop);
		for(int i = 0; i < myPop.size(); i++) {
			System.out.println(i);
			myPop.getIndividual(i).printDebugTree();
		}
		
		/*
		List<String> lines = new ArrayList<String>();
		PrintWriter pw;
		int evalCount = 0;
		int runCount = 0;
		int currentHighest = 0;
		File f = new File("default.txt");
		if(args.length > 0) {
			f = new File(args[0]);
		}
		try {
			Scanner in = new Scanner(f);
			pw = new PrintWriter((f.getName() + ".log"), "UTF-8");
			while(in.hasNextLine()) {
				lines.add(in.nextLine());
			}
			for(String l : lines) {
				 String[] lineAttributes = l.split("\\s+");
				 if("evaluations".equals(lineAttributes[0])) {
					 evalCount = Integer.parseInt(lineAttributes[1]);
				 } else if("runs".equals(lineAttributes[0])) {
					 runCount = Integer.parseInt(lineAttributes[1]);
				 }
			}
			pw.write("Result log: \n");
			for(int i = 0; i < runCount; i++) {
				pw.write("\nRun " + (i+1) + "\n");
				currentHighest = 0;
				for(int j = 0; j < evalCount; j++) {
					Game game = new Game(lines);
					while(!game.gameEnd) {
						game.takeTurn();
					}
					if((int) (game.score * 100) > currentHighest) {
						currentHighest = (int) (game.score * 100);
						pw.write((j+1) + "\t" + currentHighest + "\n");
					}
				}
				pw.write("");
			}
			in.close();
			pw.close();
		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("UH.");
			e.printStackTrace();
		}
		*/
		
	}
}
