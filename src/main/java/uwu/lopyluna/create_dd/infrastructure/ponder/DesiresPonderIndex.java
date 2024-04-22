package uwu.lopyluna.create_dd.infrastructure.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import uwu.lopyluna.create_dd.DesiresCreate;

public class DesiresPonderIndex {

	static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(DesiresCreate.MOD_ID);

	public static final boolean REGISTER_DEBUG_SCENES = false;

	public static void register() {
	}

	public static boolean editingModeActive() {
		return AllConfigs.client().editingMode.get();
	}

}
