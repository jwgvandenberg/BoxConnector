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
import static boxconnector.proxies.microflows.Microflows.updateFileInfoImpl;

/**
 * Used to update individual or multiple fields in the file object, including renaming the file, changing its description, and creating a shared link for the file. To move a file, change the ID of its parent folder.
 * 
 * Required
 * BoxFile : The file to update 
 * 
 * Optional (but at least one of them must be specified)
 * UpdateName : The new name
 * UpdateDescription: The new description
 * UpdateParentFolder: The folder where to move the file
 * UpdateSharedLink: The SharedLink to update with its new attributes
 * UpdateTags: All tags attached to this file. 
 */
public class UpdateFileInfo extends CustomJavaAction<IMendixObject>
{
	private IMendixObject __BoxFileParameter1;
	private boxconnector.proxies.BoxFile BoxFileParameter1;
	private java.lang.String UpdateName;
	private java.lang.String UpdateDescription;
	private IMendixObject __UpdateParentFolder;
	private boxconnector.proxies.BoxFolder UpdateParentFolder;
	private IMendixObject __UpdateSharedLink;
	private boxconnector.proxies.SharedLink UpdateSharedLink;
	private java.util.List<IMendixObject> __UpdateTags;
	private java.util.List<boxconnector.proxies.TagValue> UpdateTags;

	public UpdateFileInfo(IContext context, IMendixObject BoxFileParameter1, java.lang.String UpdateName, java.lang.String UpdateDescription, IMendixObject UpdateParentFolder, IMendixObject UpdateSharedLink, java.util.List<IMendixObject> UpdateTags)
	{
		super(context);
		this.__BoxFileParameter1 = BoxFileParameter1;
		this.UpdateName = UpdateName;
		this.UpdateDescription = UpdateDescription;
		this.__UpdateParentFolder = UpdateParentFolder;
		this.__UpdateSharedLink = UpdateSharedLink;
		this.__UpdateTags = UpdateTags;
	}

	@Override
	public IMendixObject executeAction() throws Exception
	{
		this.BoxFileParameter1 = __BoxFileParameter1 == null ? null : boxconnector.proxies.BoxFile.initialize(getContext(), __BoxFileParameter1);

		this.UpdateParentFolder = __UpdateParentFolder == null ? null : boxconnector.proxies.BoxFolder.initialize(getContext(), __UpdateParentFolder);

		this.UpdateSharedLink = __UpdateSharedLink == null ? null : boxconnector.proxies.SharedLink.initialize(getContext(), __UpdateSharedLink);

		this.UpdateTags = new java.util.ArrayList<boxconnector.proxies.TagValue>();
		if (__UpdateTags != null)
			for (IMendixObject __UpdateTagsElement : __UpdateTags)
				this.UpdateTags.add(boxconnector.proxies.TagValue.initialize(getContext(), __UpdateTagsElement));

		// BEGIN USER CODE
		boxconnector.proxies.BoxFile boxFile = updateFileInfoImpl(getContext(), BoxFileParameter1, UpdateParentFolder, UpdateSharedLink, UpdateName, UpdateDescription, UpdateTags);
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
		return "UpdateFileInfo";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}