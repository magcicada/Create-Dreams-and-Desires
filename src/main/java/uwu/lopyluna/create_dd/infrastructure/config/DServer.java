package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class DServer extends ConfigBase {

	public final ConfigGroup infrastructure = group(0, "infrastructure", Comments.infrastructure);

	public final DRecipes recipes = nested(0, DRecipes::new, Comments.recipes);
	public final DKinetics kinetics = nested(0, DKinetics::new, Comments.kinetics);
	public final DLogistics logistics = nested(0, DLogistics::new, Comments.logistics);
	public final DEquipment equipment = nested(0, DEquipment::new, Comments.equipment);

	@Override
	public String getName() {
		return "server";
	}

	private static class Comments {
		static String recipes = "Packmakers' control panel for internal recipe compat";
		static String kinetics = "Parameters and abilities of Create: Dream n' Desire's kinetic mechanisms";
		static String logistics = "Tweaks for logistical components";
		static String equipment = "Equipment and gadgets added by Create: Dream n' Desire";
		static String infrastructure = "The Backbone of Create: Dream n' Desire";
	}

}
