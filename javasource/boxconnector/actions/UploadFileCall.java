// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package boxconnector.actions;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import boxconnector.proxies.constants.Constants;

public class UploadFileCall extends CustomJavaAction<java.lang.String>
{
	private IMendixObject __AccessTokenParameter1;
	private boxconnector.proxies.AccessToken AccessTokenParameter1;
	private IMendixObject __FileToUpload;
	private system.proxies.FileDocument FileToUpload;
	private IMendixObject __BoxFolderParameter1;
	private boxconnector.proxies.BoxFolder BoxFolderParameter1;

	public UploadFileCall(IContext context, IMendixObject AccessTokenParameter1, IMendixObject FileToUpload, IMendixObject BoxFolderParameter1)
	{
		super(context);
		this.__AccessTokenParameter1 = AccessTokenParameter1;
		this.__FileToUpload = FileToUpload;
		this.__BoxFolderParameter1 = BoxFolderParameter1;
	}

	@Override
	public java.lang.String executeAction() throws Exception
	{
		this.AccessTokenParameter1 = __AccessTokenParameter1 == null ? null : boxconnector.proxies.AccessToken.initialize(getContext(), __AccessTokenParameter1);

		this.FileToUpload = __FileToUpload == null ? null : system.proxies.FileDocument.initialize(getContext(), __FileToUpload);

		this.BoxFolderParameter1 = __BoxFolderParameter1 == null ? null : boxconnector.proxies.BoxFolder.initialize(getContext(), __BoxFolderParameter1);

		// BEGIN USER CODE
		
		String URL = Constants.getBoxAPI_URL_Upload() + "/content" ;

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(URL);
		postMethod.setRequestHeader("Authorization", "Bearer " + this.AccessTokenParameter1.gettoken());

		List<Part> parts = new ArrayList<Part>();

		JSONObject parent = new JSONObject();
		parent.put("id", this.BoxFolderParameter1.get_id());

		JSONObject attributes = new JSONObject();
		attributes.put("parent", parent);
		attributes.put("name", this.FileToUpload.getName());

		StringPart strPart = new StringPart("attributes", attributes.toString());
		strPart.setContentType("text/plain");
		parts.add(strPart);

		ByteArrayPartSource source = new ByteArrayPartSource(this.FileToUpload.getName(),
				IOUtils.toByteArray(Core.getFileDocumentContent(getContext(), this.FileToUpload.getMendixObject())));
		parts.add(new FilePart("file", source));

		postMethod.setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), postMethod.getParams()));
		httpClient.executeMethod(postMethod);
		
		int status = postMethod.getStatusCode();
		
		if(status == HttpURLConnection.HTTP_CREATED ) {
			int BUFFER_SIZE = 8192;
			InputStreamReader input = new InputStreamReader(postMethod.getResponseBodyAsStream(),	StandardCharsets.UTF_8);
			
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[BUFFER_SIZE];
	
			try {
				int read = input.read(buffer, 0, BUFFER_SIZE);
				while (read != -1) {
					builder.append(buffer, 0, read);
					read = input.read(buffer, 0, BUFFER_SIZE);
				}
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
			} finally {
				input.close();
			}
			postMethod.releaseConnection();
			
			String jsonText = builder.toString();
			
			return jsonText;
		} else {
			postMethod.releaseConnection();
			throw new com.mendix.systemwideinterfaces.MendixRuntimeException("" + status);
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "UploadFileCall";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
