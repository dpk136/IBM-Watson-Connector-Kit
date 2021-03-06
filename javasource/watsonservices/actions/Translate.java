// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package watsonservices.actions;

import java.util.List;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiableLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

/**
 * FromLang could be empty. Language will be detected if possible. If not possible an exception will be thrown
 * 
 */
public class Translate extends CustomJavaAction<String>
{
	private String text;
	private String FromLang;
	private String ToLang;
	private String username;
	private String password;

	public Translate(IContext context, String text, String FromLang, String ToLang, String username, String password)
	{
		super(context);
		this.text = text;
		this.FromLang = FromLang;
		this.ToLang = ToLang;
		this.username = username;
		this.password = password;
	}

	@Override
	public String executeAction() throws Exception
	{
		// BEGIN USER CODE
		LanguageTranslation service = new LanguageTranslation();
	    service.setUsernameAndPassword(username, password);
	    
	    if(FromLang == null | FromLang.length() == 0) 	    {
	    	List<IdentifiedLanguage> identifiedLanguages = service.identify(text);
	    	if(identifiedLanguages != null && !identifiedLanguages.isEmpty()) {
	    		IdentifiableLanguage identifiableLanguage = identifiedLanguages.get(0);
	    		FromLang = identifiableLanguage.getLanguage();	    		
	    	}
	    	if(FromLang == null) {
	    		throw new com.mendix.systemwideinterfaces.MendixRuntimeException("No language detected");
	    	}
	    }
	    
	    TranslationResult result = service.translate(text, FromLang, ToLang);
	    List<Translation> translations = result.getTranslations();
	    if(translations.size() > 0) {
	    	return translations.get(0).getTranslation();
	    }
	    throw new com.mendix.systemwideinterfaces.MendixRuntimeException("No translation found for given text");
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "Translate";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
