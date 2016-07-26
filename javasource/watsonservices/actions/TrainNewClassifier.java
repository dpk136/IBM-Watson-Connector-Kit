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
import com.ibm.watson.developer_cloud.visual_recognition.v2.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v2.model.VisualClassifier;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class TrainNewClassifier extends CustomJavaAction<String>
{
	private String username;
	private String password;
	private IMendixObject __VRClassigier;
	private watsonservices.proxies.VRClassification VRClassigier;

	public TrainNewClassifier(IContext context, String username, String password, IMendixObject VRClassigier)
	{
		super(context);
		this.username = username;
		this.password = password;
		this.__VRClassigier = VRClassigier;
	}

	@Override
	public String executeAction() throws Exception
	{
		this.VRClassigier = __VRClassigier == null ? null : watsonservices.proxies.VRClassification.initialize(getContext(), __VRClassigier);

		// BEGIN USER CODE
		
		VisualRecognition service = new VisualRecognition(com.ibm.watson.developer_cloud.visual_recognition.v2.VisualRecognition.VERSION_DATE_2015_12_02);
		service.setUsernameAndPassword(this.username, this.password);
		
		watsonservices.proxies.trainingImagesZipFile posTrainingImagesZipFile = VRClassigier.getpositives_trainingImages();
		system.proxies.FileDocument posZipFileDocument = posTrainingImagesZipFile;
		File posTempFile = new File(Core.getConfiguration().getTempPath() + posZipFileDocument.getName());
		InputStream stream = Core.getFileDocumentContent(getContext(), posTrainingImagesZipFile.getMendixObject());
		Files.copy(stream, posTempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		stream.close();
		
		watsonservices.proxies.trainingImagesZipFile negTrainingImagesZipFile = VRClassigier.getnegative_trainingImages();
		system.proxies.FileDocument negZipFileDocument = negTrainingImagesZipFile;
		File negTempFile = new File(Core.getConfiguration().getTempPath() + negZipFileDocument.getName());
		stream = Core.getFileDocumentContent(getContext(), negTrainingImagesZipFile.getMendixObject());
		Files.copy(stream, negTempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		stream.close();
		
	    
	    VisualClassifier classifier = service.createClassifier(VRClassigier.getclassificationName(), posTempFile, negTempFile);
	    return classifier.getId();

		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "TrainNewClassifier";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}