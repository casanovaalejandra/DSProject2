package interfaces;
import tools.Target;
/**
 * 
 * @author Alejandra Casanova
 *
 * @param <E>
 */

public interface Tree<E>{		
	/**
	 * 
	 * @return root of the tree
	 */
	Target root(); 
	/**
	 * 
	 * @param p target to which the parent want to be known
	 * @return the corresponding parent of the target
	 * @throws IllegalArgumentException if the p is the root of the target then it does not contain a parent,
	 * if the node to check for the parent does not exits
	 */
	Target parent(Target p) throws IllegalArgumentException; 
	/**
	 * 
	 * @param p target to check the number of childs
	 * @return number of children
	 * @throws IllegalArgumentException if the target does not contain any children
	 */
	int numChildren(Target p) throws IllegalArgumentException; 
	/**
	 * 
	 * @param p target to verify 
	 * @return true if it has one of more children
	 * @throws IllegalArgumentException if its not a target in the linked tree
	 */
	boolean isInternal(Target p) throws IllegalArgumentException; 
	/**
	 * 
	 * @param p target to check
	 * @return true if the target does not have children
	 * @throws IllegalArgumentException if the target is not in the linked tree
	 */
	boolean isExternal(Target p) throws IllegalArgumentException; 
	/**
	 * 
	 * @param p target to verify
	 * @return true if its the root of the tree
	 * @throws IllegalArgumentException if the target is not a target object on the tree
	 */
	boolean isRoot(Target p) throws IllegalArgumentException; 
	/**
	 * 
	 * @return size of the tree
	 */
	int size();
	/**
	 * 
	 * @return true if the tree does not contain targets
	 */
	boolean isEmpty(); 
}
