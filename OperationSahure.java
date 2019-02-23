import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import tools.ArrayIndexList;
import tools.Target;
import tree.LinkedTree;

public class OperationSahure<E> {

	private static Scanner in;
	private static int totalCriminals;
	private  static ArrayIndexList<Target> listOfCriminals;
	private static LinkedTree<Target> scam;
	private static ArrayIndexList<Integer> maxArrests;
	/**
	 * 
	 */
	public OperationSahure() {
	}
	/**
	 * 
	 * @param args Main method to read the input files
	 * @throws FileNotFoundException if the file is not found it throws this exception
	 */
	public static void main(String[] args) throws FileNotFoundException {

		File fileText = new File(System.getProperty("user.dir")+ "/input.txt");
		ArrayIndexList<File> theFiles = new ArrayIndexList<File>();
		maxArrests = new ArrayIndexList<Integer>();

		try {
			in = new Scanner(fileText);
			while(in.hasNextLine()) {
				//XYZ in the following block, you have assumed that the maxArrest is always one digit.
				//XYZ this is a bad assumption. mazArrest could be any number of digits.

				/*XYZ				
				String currentMax = in.next().substring(0, 1);
				int integerCurrMax = Integer.parseInt(currentMax);
				maxArrests.add(integerCurrMax);
				String filename = in.nextLine().substring(1);
				 */

				//XYZ Instead, I suggest the following 4 lines
				String[] params=in.nextLine().trim().split(" ");
				int integerCurrMax = Integer.parseInt(params[0]);
				maxArrests.add(integerCurrMax);
				String filename = params[1]; 

				File newFile = new File(filename);
				theFiles.add(newFile);

			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();{

			}
		}	

		for(int m = 0; m<= theFiles.size()-1;m++) {
			readFile(theFiles.get(m));
			scam.mentorAssigment();
			scam.setMaxArrest(maxArrests.get(m));
			scam.pyramidTraversal();
			//scam.getFinalRoute();
			int numberOfList;
			try {
				//String fileName = theFiles.get(m).getName();
				//String baseName = fileName.substring(0, fileName.length()-4);

				String outputName = "output"+(m+1)+".out";
				PrintWriter outputStream = new PrintWriter(outputName);

				outputStream.write("Maximum seized assets: "+ scam.getMaxAsset()	
				+System.getProperty("line.separator"));
				for(int l = 0; l<=scam.getFinalRoutes().size()-1;l++) {
					numberOfList = l+1;
					outputStream.write("List: "+ numberOfList+":");
					for(int j = 0; j<=scam.getFinalRoutes().get(l).size()-1;j++) {
						outputStream.write(" \n"+scam.getFinalRoutes().get(l).get(j).getUserName()+", ");
					}
					outputStream.write(System.getProperty("line.separator"));
				}
				outputStream.close();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}	

	}
	/**
	 * 
	 * @param file to be read, separates the data into target objects
	 */

	public static void readFile(File file)  {
		try {
			Scanner in = new Scanner(file);
			listOfCriminals = new ArrayIndexList<>();
			scam = new LinkedTree<>();

			String dataRoot = in.nextLine();
			String[] separatedDataRoot = dataRoot.split("#");

			//  ILU, are u sure it has a root?
			if(separatedDataRoot.length==0) {
				System.out.print("The log file is empty\n");
			}
			Target rootCriminal = new Target(separatedDataRoot[0].toString(),Integer.parseInt(separatedDataRoot[1]),null);

			scam.addRoot(rootCriminal);
			listOfCriminals.add(rootCriminal);

			while(in.hasNext()) {
				String data = in.nextLine();
				String[] separatedData = data.split("#");
				Target spns=scam.preOrder(rootCriminal,separatedData[2].toString());

				Target currentCriminal = new Target(separatedData[0].toString(),Integer.parseInt(separatedData[1]), spns);
				scam.addChild(spns, currentCriminal);
				listOfCriminals.add(currentCriminal);
				totalCriminals++;
			}

			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println(scam.getMaxAsset());
	}
	/**
	 * 
	 * @return the list containing all the targets on that file
	 */
	public static ArrayIndexList<Target> getTheCriminals() {
		return listOfCriminals;
	}
	/**
	 * 
	 * @return maximum number of arrest in a determined route
	 */
	public static ArrayIndexList<Integer> getMaxArrests(){
		return maxArrests;
	}
}

