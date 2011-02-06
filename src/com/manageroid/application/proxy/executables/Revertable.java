package com.manageroid.application.proxy.executables;

/**
 * Marks that changes made by some class can be reverted after given time
 * @author Administrator
 *
 */
public interface Revertable {

	/**
	 * Purpose - to revert some changes made to something by coresponding
	 * {@link Executable} class
	 */
	public void undo();
}
