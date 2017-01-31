// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package boxconnector.actions;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import boxconnector.proxies.constants.Constants;

public class PreflightCheckCall extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __BoxFileParameter1;
	private boxconnector.proxies.BoxFile BoxFileParameter1;
	private IMendixObject __BoxFolderParameter1;
	private boxconnector.proxies.BoxFolder BoxFolderParameter1;
	private IMendixObject __AccessTokenParameter1;
	private boxconnector.proxies.AccessToken AccessTokenParameter1;

	public PreflightCheckCall(IContext context, IMendixObject BoxFileParameter1, IMendixObject BoxFolderParameter1, IMendixObject AccessTokenParameter1)
	{
		super(context);
		this.__BoxFileParameter1 = BoxFileParameter1;
		this.__BoxFolderParameter1 = BoxFolderParameter1;
		this.__AccessTokenParameter1 = AccessTokenParameter1;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.BoxFileParameter1 = __BoxFileParameter1 == null ? null : boxconnector.proxies.BoxFile.initialize(getContext(), __BoxFileParameter1);

		this.BoxFolderParameter1 = __BoxFolderParameter1 == null ? null : boxconnector.proxies.BoxFolder.initialize(getContext(), __BoxFolderParameter1);

		this.AccessTokenParameter1 = __AccessTokenParameter1 == null ? null : boxconnector.proxies.AccessToken.initialize(getContext(), __AccessTokenParameter1);

		// BEGIN USER CODE
		String URL = Constants.getBoxAPI_URL_Files() + "/content";

		HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
		connection.setRequestMethod("OPTIONS");
		connection.setDoOutput(true);
		connection.addRequestProperty("Authorization", "Bearer " + this.AccessTokenParameter1.gettoken());
		connection.addRequestProperty("Content-Type", "application/json");
		connection.connect();

		JSONObject params = new JSONObject();
		params.put("name", this.BoxFileParameter1.getname());

		JSONObject parent = new JSONObject();
		parent.put("id", this.BoxFolderParameter1.get_id());

		params.put("parent", parent);
		params.put("size", 0);

		OutputStream out = connection.getOutputStream();
		out.write(params.toString().getBytes());
		out.close();
		
		int status = connection.getResponseCode();
		
		connection.disconnect();

		if (status == HttpURLConnection.HTTP_OK) {
			return true;
		} else {
			return false;
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "PreflightCheckCall";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
