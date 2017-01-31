// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package boxconnector.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import static boxconnector.proxies.microflows.Microflows.deleteFolderImpl;

/**
 * Used to delete a folder. A recursive parameter must be included in order to delete folders that have items inside of them.
 * 
 * Required
 * BoxFolder: the folder to delete
 * The _id attribute is required.
 * 
 * Recursive: Whether to delete this folder if it has items inside of it.
 */
public class DeleteFolder extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __BoxFolderParameter1;
	private boxconnector.proxies.BoxFolder BoxFolderParameter1;
	private java.lang.Boolean Recursive;

	public DeleteFolder(IContext context, IMendixObject BoxFolderParameter1, java.lang.Boolean Recursive)
	{
		super(context);
		this.__BoxFolderParameter1 = BoxFolderParameter1;
		this.Recursive = Recursive;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.BoxFolderParameter1 = __BoxFolderParameter1 == null ? null : boxconnector.proxies.BoxFolder.initialize(getContext(), __BoxFolderParameter1);

		// BEGIN USER CODE
		Boolean result = deleteFolderImpl(getContext(), BoxFolderParameter1, this.Recursive);
		return result;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "DeleteFolder";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
