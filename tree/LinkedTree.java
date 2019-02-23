package tree;
import java.util.ArrayList;
import tools.Target;

public class LinkedTree<E> extends AbstractTree<E>  {

	private Target root; 
	private int size;
	private ArrayList<Target> totalTargets;
	private int maxArrests;
	private ArrayList<Target> finalRoute;

	private ArrayList<ArrayList<Target>> finalRoutes;
	private ArrayList<ArrayList<Target>> totalFinalRoutes;	

	private int maxAssets;
	private int totalMaxAssets=0;

	private int currentMax=0;
	/**
	 * Constructs an empty LinkedTree object
	 */
	public LinkedTree() { 
		root = null; 
		size = 0; 
		totalTargets = new ArrayList<>();
		finalRoute = new ArrayList<>();
		totalFinalRoutes = new ArrayList<>();
		finalRoutes = new ArrayList<>();

	}
	/**
	 * 
	 * @param p current target to validate
	 * @return a validated Target object
	 * @throws IllegalArgumentException if there is another object treated as a Target object
	 */
	private Target validate(Target p) throws IllegalArgumentException { 
		if (!(p instanceof Target)) 
			throw new IllegalArgumentException("Invalid position type for this implementation."); 
		Target np = (Target) p; 
		if (np.getSponsor() == np)
			throw new IllegalArgumentException("Target position is not part of a tree.");	
		return np; 
	}
	/**
	 * returns the root of the current linked tree
	 */
	@Override
	public Target root() {
		return root;
	}
	/**
	 * returns the parent of the current target
	 */
	@Override
	public Target parent(Target p) throws IllegalArgumentException {
		Target np = this.validate(p); 
		return np.getSponsor(); 
	}
	/**
	 * 
	 * @param root current Target position on the tree in which the determined Target is going to be searched
	 * @param rootName name of the target that wants to be found in the tree
	 * @return the target object that is being searched
	 */
	public Target preOrder(Target root, String rootName) {
		Target temp = null;
		if(root.getUserName().equals(rootName)) 
			return root;
		if(root.getChildren().size()>0) {
			for(int i = 0; i<=root.getChildren().size()-1;i++) {
				temp =  preOrder(root.getChildren().get(i),rootName);
				if (temp !=null) break;
			}

		}return temp;

	}
	/**
	 * returns the number of children on the current target p
	 */
	@Override
	public int numChildren(Target p) throws IllegalArgumentException {
		Target np = validate(p);  
		if (np.getChildren() != null) 
			return np.getChildren().size();
		else 
			return 0; 

	}
	/**
	 * returns the size of the three, number of targets in it
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * 
	 * @param target current root of the three
	 * @throws IllegalStateException if the tree has a root already it will throw the exception
	 */

	public void addRoot(Target target) throws IllegalStateException { 
		if (this.root != null) 
			throw new IllegalStateException("Tree must be empty to add a root."); 
		root = target;  
		totalTargets.add(root);
		size++; 
	}

	/**
	 * Add a new element as a child to a given position in the tree
	 * @param p the position to be the parent of the new element position
	 * @param element the new element to add to the tree
	 * @return the Position<E> of where the new element is stored
	 * @throws IllegalArgumentException if the position is not valid.....
	 */
	public void addChild(Target parent, Target newChild) 
			throws IllegalArgumentException { 

		Target np = validate(parent);  
		Target nc = validate(newChild);
		totalTargets.add(nc);
		np.setChildren(nc);
		size++; 

	}
	/**
	 * Assigns the mentor to each of the targets in the three.
	 * The parent of each node is its Sponsor
	 * If the target is the first child its sponsor is also its mentor.
	 */
	public void mentorAssigment() {

		/*XYZ: there are a lot of redundant code in the following block.
 	           the concept is very fine though. It can be summarized a lot.

		for(int i = 0; i<= totalTargets.size()-1;i++) {
			Target current = totalTargets.get(i);
			if(totalTargets.get(i).equals(this.root)) {
				current.setMentor(null);

				//XYZ: what if the root does not have any children?
				//XYZ: in that case, the following line is going to throw an exception.
				current.getChildren().get(0).setMentor(current);
				for(int m = 1;m<=current.getChildren().size()-1;m++) {
					current.getChildren().get(m).setMentor(current.getChildren().get(m-1));
				}

			}
			else 
				if(current.getChildren().size()==0 && current.getSponsor().getChildren().get(0).equals(current)) {
					current.setMentor(current.getSponsor());    //??
				}
				else 
					if(current.getChildren().size()==1) {
						current.getChildren().get(0).setMentor(current);

					}else if(current.getChildren().size()>1) {
						current.getChildren().get(0).setMentor(current);
						for(int j = 1; j <= current.getChildren().size()-1;j++) {
							current.getChildren().get(j).setMentor(current.getChildren().get(j-1));
						}
					}
		}
		 */

		//XYZ: instead, let's do this:
		for(int i = 0; i<= totalTargets.size()-1;i++) {
			Target current = totalTargets.get(i);
			for(int m = 1;m<=current.getChildren().size()-1;m++) 
				current.getChildren().get(m).setMentor(current.getChildren().get(m-1));
		}

	}

	/**
	 * 
	 * @param p target to remove from the tree
	 * @return the target's name that has been removed
	 * @throws IllegalArgumentException throws exception if the root is tried to be removed
	 */
	public String remove(Target p) throws IllegalArgumentException { 
		Target ntd = validate(p); 
		String etr = ntd.getUserName(); 
		Target parent = ntd.getSponsor(); 
		if (parent == null)    // then ntd is the root
			if (numChildren(ntd) > 1) 
				throw new IllegalArgumentException
				("Cannot remove a root having more than one child."); 
			else if (numChildren(ntd) == 0)
				root = null; 
			else { 
				root = ntd.getChildren().get(0);    // the only child
				root.setSponsor(null);
			}
		else { 
			for (Target childNTD : ntd.getChildren()) { 
				parent.getChildren().add(childNTD);   
				childNTD.setSponsor(parent); 
			}

		}

		root.getChildren().remove(ntd);
		ntd.setAsset(0);
		ntd.setChildren(null);
		ntd.setMentor(null);
		ntd.setSponsor(null);
		size--; 
		return etr;   // return removed value
	}
	//	int currentMax = 0;
	/**
	 * traverse the Linked Tree starting with each of the targets individually
	 */
	public void pyramidTraversal() {
		//currentMax = 0;
		ArrayList<Target> currentList = new ArrayList<>();

		for(int i = 0; i<=totalTargets.size()-1;i++) {
			finalRoutes.clear();
			currentList.clear();
			currentList.add(totalTargets.get(i));
			totalTargets.get(i).setArrested(true);
			currentMax=totalTargets.get(i).getAsset();
			maxAssets=0;
			arrestOp(totalTargets.get(i),this.maxArrests,/*currentMax,*/currentList);   

			totalTargets.get(i).setArrested(false);

			//currentMax -= currentList.get(currentList.size()-1).getAsset();
			//currentList.remove(currentList.size()-1);

			if (maxAssets > totalMaxAssets) {
				totalMaxAssets = maxAssets;
				totalFinalRoutes.clear();
				totalFinalRoutes.addAll(finalRoutes);
			}
			else if (maxAssets == totalMaxAssets) 
				totalFinalRoutes.addAll(finalRoutes);

			// reset arrested condition, currentlist, and currentM
		}
	}

	/**
	 * 
	 * @param targetZero target that starts the route
	 * @param maxArr maximum number of targets to be arrested
	 * @param currentMax current maximum asset accumulated with the arrested targets
	 * @param currentList list that saves the arrested targets
	 */
	@SuppressWarnings("unchecked")
	public void arrestOp(Target targetZero, int maxArr,/*int currentMax,*/ ArrayList<Target> currentList) {

		//System.out.println("next to current criminal "+ targetZero.getUserName());

		if(maxArr<=0) {}
		else {
			if(maxArr==1) {
				if(currentMax>maxAssets) {

					//XYZ: the for loop should be removed
					//					for(int g =0;g<=currentList.size()-1;g++) {
					//						System.out.println("currentList reaching the max arrests:"+currentList.get(g).getUserName()+ "");
					//					}
					finalRoutes.clear();
					maxAssets =currentMax;
					//finalRoute.addAll(currentList);
					//XYZ: java issue. finalRoute should be cloned first
					finalRoutes.add((ArrayList<Target>) currentList.clone());
					//					currentMax-=currentList.get(currentList.size()-1).getAsset();
					//					currentList.get(currentList.size()-1).setArrested(false);
					//					currentList.remove(currentList.size()-1);
					//System.out.println("current criminal"+ targetZero.getUserName());
				}
				else if (currentMax==maxAssets) 
				{
					if (!finalRoutes.contains(currentList))
						finalRoutes.add((ArrayList<Target>) currentList.clone());
				}
			}
			else {
				for( int i = 0; i <= targetZero.getChildren().size()-1;i++) {
					Target child=targetZero.getChildren().get(i);
					if (!child.isArrested()) {

						child.setArrested(true);
						// addmoney to current Max
						// add person to currentList
						currentList.add(child);
						currentMax+=child.getAsset();
						arrestOp(child,maxArr-1,/*currentMax,*/currentList);
						child.setArrested(false);
						currentMax-=child.getAsset();
						currentList.remove(currentList.size()-1);
					}
					//System.out.println("searching for children of "+ targetZero.getUserName());
				}

				//XYZ: the following line could throw exception if a node does not have mentor
				/*XYZ:
			if(!targetZero.getMentor().isArrested() && !targetZero.equals(this.root()) ) {
				 */

				//XYZ: So, check if the current node has a mentor
				//XYZ: in addition to the root, the first child of each node does not
				//XYZ: have a mentor even though its sponsor is logically considered 
				//XYZ: as its mentor
				Target ment=targetZero.getMentor();
				if (ment!=null)
					if (!ment.isArrested()) {
						ment.setArrested(true);
						currentList.add(ment);
						currentMax+=ment.getAsset();
						arrestOp(ment,maxArr-1,/*currentMax,*/currentList);

						//XYZ: it was a clerical error in next line, instead of 
						//XYZ: releasing targetZero.getMentor(),
						//XYZ: the targetZero was released. Already fixed.
						ment.setArrested(false);

						currentMax-=ment.getAsset();
						currentList.remove(currentList.size()-1);
						//System.out.println("current criminal"+ targetZero.getUserName());
					}

				//XYZ: exactly similar to the above code, just instead of mentor,
				//XYZ: it's sponsor
				// if(!targetZero.getSponsor().isArrested() && !targetZero.equals(this.root())) {

				Target spns=targetZero.getSponsor();
				if (spns !=null)
					if (!spns.isArrested()) {
						spns.setArrested(true);
						currentList.add(spns);
						currentMax +=spns.getAsset();
						arrestOp(spns,maxArr-1,/*currentMax,*/currentList);
						spns.setArrested(false);
						currentMax-=spns.getAsset();
						currentList.remove(currentList.size()-1);
					}

				//System.out.println(targetZero.getUserName()+" "+currentMax+" "+maxAssets);
				if(currentMax>maxAssets) {
					//XYZ: the for loop should be removed
					for(int g =0;g<=currentList.size()-1;g++) {
						//System.out.println("currentList reaching the max arrests:"+currentList.get(g).getUserName()+ "");
					}
					finalRoutes.clear();
					maxAssets =currentMax;
					//finalRoute.addAll(currentList);
					//XYZ: java issue. finalRoute should be cloned first
					finalRoutes.add((ArrayList<Target>) currentList.clone());
					//					currentMax-=currentList.get(currentList.size()-1).getAsset();
					//					currentList.get(currentList.size()-1).setArrested(false);
					//					currentList.remove(currentList.size()-1);
					//System.out.println("current criminal"+ targetZero.getUserName());
				}
				else if (currentMax==maxAssets) 
					finalRoutes.add((ArrayList<Target>) currentList.clone());				

				//				if(currentMax>=maxAssets){
				//					for(int g =0;g<=currentList.size()-1;g++) {
				//						//System.out.println("currentList NOT reaching the max arrests:"+currentList.get(g).getUserName()+ "");
				//					}
				//					ArrayList<Target> doubledMaxRoute = new ArrayList<>();
				//					doubledMaxRoute.addAll(currentList);
				//					finalRoutes.add(doubledMaxRoute);
				//
				//				}	
			}
		}		
	}
	//		finalRoutes =  new ArrayList<>();
	//		finalRoute = new ArrayList<>();
	//		finalRoute2 = new ArrayList<>();
	//		finalRoute.add(totalTargets.get(0));
	//		finalRoute.add(totalTargets.get(1));
	//		finalRoute.add(totalTargets.get(2));
	//		
	//		
	//		finalRoute2.add(totalTargets.get(3));
	//		finalRoute2.add(totalTargets.get(4));
	//		finalRoute2.add(totalTargets.get(5));
	//		finalRoutes.add(finalRoute);
	//		finalRoutes.add(finalRoute2);
	//		maxAssets = 45;



	/**
	 * 
	 * @return the route with the highest assest
	 */
	public ArrayList<Target> getFinalRoute() {
		return finalRoute;

	}
	/**
	 * 
	 * @param list is the list of total targets that is read from the input files
	 */
	public void setTotalTargetList(ArrayList<Target> list) {
		totalTargets = list;
	}
	/**
	 * 
	 * @return the maximum asset when starting at determined target
	 */
	public int getMaxAsset() {
		return totalMaxAssets;
	}
	/**
	 * 
	 * @return the list that contains the lists with higher assets
	 */
	public ArrayList<ArrayList<Target>> getFinalRoutes(){
		return totalFinalRoutes;
	}
	/**
	 * 
	 * @param theMax sets the maximum number of arrests read from the input file to the current route
	 */
	public void setMaxArrest(int theMax) {
		this.maxArrests = theMax;
	}
}
