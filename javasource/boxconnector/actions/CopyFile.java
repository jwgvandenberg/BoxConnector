// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package boxconnector.actions;

import static boxconnector.proxies.microflows.Microflows.copyFileImpl;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

/**
 * Used to copy a file to another folder. The original version of the file will not be altered.
 * 
 * Required
 * BoxFile: The source file to copy.
 * The _id attribute is required
 * 
 * DestBoxFolder: Folder representing the new location of the file
 * The _id attribute is required
 * 
 * 
 * Optional
 * Version: An optional file version id if you want to copy a specific file version
 * 
 * Name: An optional new name for the file
 */
public class CopyFile extends CustomJavaAction<IMendixObject>
{
	private IMendixObject __BoxFileParam;
	private boxconnector.proxies.BoxFile BoxFileParam;
	private IMendixObject __DestBoxFolder;
	private boxconnector.proxies.BoxFolder DestBoxFolder;
	private java.lang.String Version;
	private java.lang.String Name;

	public CopyFile(IContext context, IMendixObject BoxFileParam, IMendixObject DestBoxFolder, java.lang.String Version, java.lang.String Name)
	{
		super(context);
		this.__BoxFileParam = BoxFileParam;
		this.__DestBoxFolder = DestBoxFolder;
		this.Version = Version;
		this.Name = Name;
	}

	@Override
	public IMendixObject executeAction() throws Exception
	{
		this.BoxFileParam = __BoxFileParam == null ? null : boxconnector.proxies.BoxFile.initialize(getContext(), __BoxFileParam);

		this.DestBoxFolder = __DestBoxFolder == null ? null : boxconnector.proxies.BoxFolder.initialize(getContext(), __DestBoxFolder);

		// BEGIN USER CODE
		boxconnector.proxies.BoxFile boxFile = copyFileImpl(getContext(), BoxFileParam, DestBoxFolder, Version, Name);
		
		if (boxFile != null)
			return boxFile.getMendixObject();
		else
			return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "CopyFile";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
