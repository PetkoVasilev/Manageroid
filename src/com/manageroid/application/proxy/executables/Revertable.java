package com.manageroid.application.proxy.executables;

/**
 * Marks that changes made by some class can be reverted after given time
 * @author Administrator
 *
 */
public interface Revertable extends Executable{

	/**
	 * Purpose - to revert some changes made to something by corresponding
	 * {@link Executable} class
	 */
	public void undo();
}
