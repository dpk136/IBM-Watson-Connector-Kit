package system;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.mendix.core.Core;
import com.mendix.core.component.LocalComponent;
import com.mendix.core.component.MxRuntime;
import com.mendix.integration.Integration;

@Component(immediate = true, properties = {"event.topics:String=com/mendix/events/model/loaded"})
public class UserActionsRegistrar implements EventHandler
{
	private MxRuntime mxRuntime;
	private LocalComponent component;
	private Integration integration;
	
	@Reference
	public void setMxRuntime(MxRuntime runtime)
	{
		mxRuntime = runtime;
		mxRuntime.bundleComponentLoaded();
	}
	
	@Reference
	public void setIntegration(Integration integration)
	{
		this.integration = integration;
	}
	
	@Override
	public void handleEvent(Event event)
	{
		if (event.getTopic().equals(com.mendix.core.event.EventConstants.ModelLoadedTopic()))        
		{
			component = mxRuntime.getMainComponent();
			Core.initialize(component, integration);   
			component.actionRegistry().registerUserAction(cfcommons.actions.getEnvVariables.class);
			component.actionRegistry().registerUserAction(system.actions.VerifyPassword.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.CallAlchemyVision.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.CallAlchemyVisionTags.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.Converse.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.CreateDialog.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.GetDialogs.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.GetSupportLanguages.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.RecognizeImage.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.TextToSpeech.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.TrainNewClassifier.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.Translate.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.UpdateDialog.class);
			component.actionRegistry().registerUserAction(watsonservices.actions.UrlKeywordExtractionCore.class);
		}
	}
}