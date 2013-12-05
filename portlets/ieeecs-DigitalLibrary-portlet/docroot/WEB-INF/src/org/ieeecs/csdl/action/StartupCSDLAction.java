package org.ieeecs.csdl.action;

import org.ieeecs.csdl.util.DigitalLibraryUtil;

import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.events.ActionException;


public class StartupCSDLAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {

		try {

			DigitalLibraryUtil.initializeProperties();
			DigitalLibraryUtil.initializeCache();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}