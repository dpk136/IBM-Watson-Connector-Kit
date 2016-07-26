// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package watsonservices.actions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import com.ibm.watson.developer_cloud.dialog.v1.DialogService;
import com.ibm.watson.developer_cloud.dialog.v1.model.Dialog;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class UpdateDialog extends CustomJavaAction<String>
{
	private String dialogName;
	private IMendixObject __dialogContent;
	private system.proxies.FileDocument dialogContent;
	private String DialogId;
	private String username;
	private String password;

	public UpdateDialog(IContext context, String dialogName, IMendixObject dialogContent, String DialogId, String username, String password)
	{
		super(context);
		this.dialogName = dialogName;
		this.__dialogContent = dialogContent;
		this.DialogId = DialogId;
		this.username = username;
		this.password = password;
	}

	@Override
	public String executeAction() throws Exception
	{
		this.dialogContent = __dialogContent == null ? null : system.proxies.FileDocument.initialize(getContext(), __dialogContent);

		// BEGIN USER CODE
		DialogService service = new DialogService();
		service.setUsernameAndPassword(this.username, this.password);
		
		//Create temporary file to easily upload the script
		File tempFile = new File(Core.getConfiguration().getTempPath() + dialogName);
		InputStream is = Core.getFileDocumentContent(getContext(), __dialogContent);
		Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		is.close();
		Dialog dialog = service.updateDialog(DialogId, tempFile);
		tempFile.delete();
		return dialog.getId();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "UpdateDialog";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}