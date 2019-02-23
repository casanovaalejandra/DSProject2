package tools;

import java.util.ArrayList;

public class Target {
	private int asset;
	private Target Sponsor;
	private String userName;
	private Target mentor;
	private ArrayList<Target> children = new ArrayList<>();
	private boolean isArrested;
	/**
	 * 
	 * @param name of the target object
	 * @param ass is the asset corresponding to it
	 * @param spn the sponsor of the created target
	 */
	public Target(String name, int ass, Target spn) {
		this.asset = ass;
		this.userName = name;
		this.Sponsor = spn;
		this.mentor= null;     //XYZ: initially assume the mentor is null
		isArrested = false;

	}
	/**
	 * 
	 * @param name of the new target
	 * @param ass the corresponding asset of that target
	 * @param spn sponsor of the new target
	 * @param men mentor of the new target
	 */
	public Target(String name, int ass, Target spn, Target men) {
		this.asset = ass;
		this.userName = name;
		this.Sponsor = spn;
		this.mentor= men;
		isArrested = false;
	}
	/**
	 * 
	 * @return the asset of the corresponding target
	 */

	public int getAsset() {
		return asset;
	}

	/**
	 * 
	 * @param asset is the asset to be assign to the target
	 */
	public void setAsset(int asset) {
		this.asset = asset;
	}
	/**
	 * 
	 * @return the Sponsor of the corresponding target
	 */
	public Target getSponsor() {
		return Sponsor;
	}
	/**
	 * 
	 * @param sponsor to be assign to the corresponding target
	 */

	public void setSponsor(Target sponsor) {
		Sponsor = sponsor;
	}
	/**
	 * 
	 * @return string of the name of the corresponding target
	 */

	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @param userName name to be assigned to the corresponding target
	 */

	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return the mentor target object of the corresponding target
	 */
	public Target getMentor() {
		return mentor;
	}
	/**
	 * 
	 * @param mentor target object corresponding to the current target
	 */
	public void setMentor(Target mentor) {
		this.mentor = mentor;
	}
	/**
	 * 
	 * @return arrayList of children of the corresponding target
	 */


	public ArrayList<Target> getChildren() {
		return children;
	}
	/**
	 * 
	 * @param child adds a new child to the corresponding ArrayList of the target 
	 */

	public void setChildren(Target child) {
		this.children.add(child);
	}
	/**
	 * 
	 * @return status of the corresponding target
	 */
	public boolean isArrested() {
		return isArrested;
	}
	/**
	 * @param isArrested set status of the target
	 */
	public void setArrested(boolean isArrested) {
		this.isArrested = isArrested;
	}

}
