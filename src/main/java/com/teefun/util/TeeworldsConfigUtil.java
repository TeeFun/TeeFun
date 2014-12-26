/**
 *
 */
package com.teefun.util;

import com.teefun.model.teeworlds.TeeworldsConfig;

/**
 * Utility class for {@link TeeworldsConfig}.
 *
 * @author Rajh
 *
 */
public final class TeeworldsConfigUtil {

	/**
	 * Private constructor for utility classes.
	 */
	private TeeworldsConfigUtil() {
	}

	/**
	 * @return server default configuration
	 */
	public static TeeworldsConfig getDefaultConfig() {
		TeeworldsConfig config = new TeeworldsConfig();

		// config.setVariable("password", "");
		config.setVariable("sv_name", "TeeFun server");
		// config.setVariable("sv_port", 0);
		// config.setVariable("sv_map", "");
		// config.setVariable("sv_max_clients", 16);
		config.setVariable("sv_max_clients_per_ip", 1);
		config.setVariable("sv_register", 1);
		config.setVariable("sv_rcon_password", "");
		config.setVariable("sv_auto_demo_record", 1);
		config.setVariable("sv_auto_demo_max", 0);

		config.setVariable("sv_warmup", 1);
		config.setVariable("sv_motd", "Welcome on TeeFun server!");
		config.setVariable("sv_teamdamage", 0);
		config.setVariable("sv_maprotation", "");
		config.setVariable("sv_rounds_per_map", 1);
		config.setVariable("sv_round_swap", 1);
		config.setVariable("sv_powerups", 0);
		// config.setVariable("sv_scorelimit", 600);
		// config.setVariable("sv_timelimit", 0);
		// config.setVariable("sv_gametype", 0);
		config.setVariable("sv_tournament_mode", 1);
		config.setVariable("sv_spamprotection", 1);
		config.setVariable("sv_respawn_delay_tdm", 2);
		config.setVariable("sv_teambalance_time", 0);
		config.setVariable("sv_inactivekick_time", 0);
		config.setVariable("sv_inactivekick", 1);
		config.setVariable("sv_strict_spectate_mode", 0);
		config.setVariable("sv_vote_spectate", 1);
		config.setVariable("sv_vote_spectate_rejoindelay", 0);
		config.setVariable("sv_vote_kick", 1);
		config.setVariable("sv_vote_kick_min", 0);
		config.setVariable("sv_vote_kick_bantime", 3);

		return config;
	}

}
