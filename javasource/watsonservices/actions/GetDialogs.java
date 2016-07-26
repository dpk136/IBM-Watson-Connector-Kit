// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package watsonservices.actions;

import java.util.ArrayList;
import java.util.List;
import com.ibm.watson.developer_cloud.dialog.v1.DialogService;
import com.ibm.watson.developer_cloud.dialog.v1.model.Dialog;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import watsonservices.proxies.ExistingDialog;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class GetDialogs extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private String username;
	private String password;

	public GetDialogs(IContext context, String username, String password)
	{
		super(context);
		this.username = username;
		this.password = password;
	}

	@Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		DialogService service = new DialogService();
		service.setUsernameAndPassword(this.username,this.password);
		List<Dialog> dialogs = service.getDialogs();

		//Create output
		List<IMendixObject> result = new ArrayList<IMendixObject>();
		for (Dialog dialog : dialogs) {
			IMendixObject iMendixObject = Core.instantiate(getContext(), ExistingDialog.entityName);
			ExistingDialog newExistingDialog = ExistingDialog.load(getContext(), iMendixObject.getId());
			newExistingDialog.setDialogID(dialog.getId());
			newExistingDialog.setDialogName(dialog.getName());
			result.add(newExistingDialog.getMendixObject());
		}
		return result;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "GetDialogs";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}