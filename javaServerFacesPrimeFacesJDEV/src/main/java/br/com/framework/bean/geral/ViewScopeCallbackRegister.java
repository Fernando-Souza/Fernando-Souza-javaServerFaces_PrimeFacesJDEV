package br.com.framework.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

public class ViewScopeCallbackRegister implements Serializable,ViewMapListener {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isListenerForSource(Object source) {
		
		return source instanceof UIViewRoot;
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		
		if(event instanceof PostConstructViewMapEvent) {
			
			PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent) event;
			UIViewRoot uiViewRoot = (UIViewRoot) viewMapEvent.getComponent();
			uiViewRoot.getViewMap().put(ViewScope.VIEW_SCOPE_CALLBACKS, new HashMap<String, Runnable>());
		
	}else if(event instanceof PreDestroyViewMapEvent){
			PreDestroyViewMapEvent viewMapEvent = (PreDestroyViewMapEvent) event;
			UIViewRoot uiViewRoot = (UIViewRoot) viewMapEvent.getComponent();
			Map<String,Runnable>callbaks = (Map<String, Runnable>)uiViewRoot.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);
			if(callbaks !=null) {
				for(Runnable c: callbaks.values()) {
					c.run();
				}
				callbaks.clear();
			}
		}

}
}
