package tree;
import java.util.ArrayList;
import java.util.Iterator;
import interfaces.Tree;
import tools.Target;
/**
 * 
 * @author Alejandra Casanova
 *
 * @param <E>
 */
public abstract class AbstractTree<E> implements Tree<E> {

	/**
	 * Returns true of the corresponding target has more than one child
	 */
	public boolean isInternal(Target p) throws IllegalArgumentException {
		return this.numChildren(p) > 0;
	}
	/**
	 * Returns true if the corresponding target does not contain children
	 */
	@Override
	public boolean isExternal(Target p) throws IllegalArgumentException {
		return this.numChildren(p) == 0;
	}
	/**
	 * returns true if the corresponding target is the root of the LinkedTree
	 */

	@Override
	public boolean isRoot(Target p) throws IllegalArgumentException {
		return p.getSponsor() == null; 
	}
	/**
	 * Returns true if the linked tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

}
