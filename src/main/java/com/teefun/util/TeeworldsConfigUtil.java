/**
 *
 */
package com.teefun.util;

import java.util.Random;

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
	 * Symbols allowed in password.
	 */
	private static final char[] symbols;

	/**
	 * Random generator.
	 */
	private static final Random random = new Random();

	static {
		final StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch) {
			tmp.append(ch);
		}
		for (char ch = 'a'; ch <= 'z'; ++ch) {
			tmp.append(ch);
		}
		symbols = tmp.toString().toCharArray();
	}

	/**
	 * @return server default configuration
	 */
	public static TeeworldsConfig getDefaultConfig() {
		final TeeworldsConfig config = new TeeworldsConfig();

		config.setVariable("password", "");
		config.setVariable("sv_name", "TeeFun server");
		config.setVariable("sv_port", 8303);
		config.setVariable("sv_map", "dm1");
		config.setVariable("sv_max_clients", 16);
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
		config.setVariable("sv_scorelimit", 10);
		config.setVariable("sv_timelimit", 5);
		config.setVariable("sv_gametype", "dm");
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

	/**
	 * Generate a random string.
	 *
	 * @param length the string length
	 * @return the string
	 */
	public static String getRandomString(final int length) {
		final char[] buf = new char[length];
		for (int idx = 0; idx < buf.length; ++idx) {
			buf[idx] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}
}
