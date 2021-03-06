package me.fru1t.rsbot;

import me.fru1t.rsbot.safecracker.strategies.DepositToBank;
import me.fru1t.rsbot.safecracker.strategies.Unknown;
import me.fru1t.rsbot.safecracker.strategies.WithdrawFromBank;
import me.fru1t.rsbot.safecracker.strategies.WalkToBank;
import me.fru1t.rsbot.safecracker.strategies.OpenBank;
import me.fru1t.rsbot.safecracker.strategies.CrackSafe;
import me.fru1t.rsbot.safecracker.strategies.Eat;
import me.fru1t.rsbot.safecracker.strategies.WalkToSafe;
import org.powerbot.script.Locatable;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import me.fru1t.common.annotations.Nullable;
import me.fru1t.rsbot.common.framework.Script;
import me.fru1t.rsbot.common.framework.StateInterface;
import me.fru1t.rsbot.common.framework.Strategy;
import me.fru1t.rsbot.common.script.rt6.Camera;
import me.fru1t.rsbot.safecracker.Settings;
import me.fru1t.rsbot.safecracker.StartupForm;
import me.fru1t.rsbot.safecracker.strategies.WithdrawFromBankManually;
import me.fru1t.rsbot.safecracker.strategies.WithdrawFromBankWithPresets;

@Manifest(
		name = "Rogue's Den Safe Cracker",
		description = "Cracks safes in Rogue's Den",
		properties = "client=6;")
public class RoguesDenSafeCracker
		extends Script<ClientContext, RoguesDenSafeCracker.State, Settings> {
	public static final int[] SAFE_OBJECT_BOUNDS_MODIFIER = {-244, 244, -1140, 0, -64, 128};
	public static final int SAFE_OBJECT_ID = 7235;
	public static final int SAFE_OPENED_OBJECT_ID = 64296;
	public static final int SAFE_SPIKES_OBJECT_ID = 7227;
	public static final int PLAYER_CRACK_ANIMATION = 15576;
	public static final int PLAYER_CRACK_PRE_HURT_ANIMATION = 15575;
	public static final int PLAYER_HURTING_ANIMATION = 18353;
	public static final String MENU_CRACK_ACTIVE_TEXT = "Crack";

	/**
	 * Defines this script's possible states.
	 */
	public enum State implements StateInterface<State> {
		// Bank
		WALK_TO_BANK(WalkToBank.class),
		OPEN_BANK(OpenBank.class),
		DEPOSIT(DepositToBank.class),
		WITHDRAW(WithdrawFromBank.class),
		WITHDRAW_WITH_PRESETS(WithdrawFromBankWithPresets.class),
		WITHDRAW_MANUALLY(WithdrawFromBankManually.class),

		// Safe cracking
		WALK_TO_SAFE(WalkToSafe.class),
		CRACK_SAFE(CrackSafe.class),
		EAT(Eat.class),

		// Other
		UNKNOWN(Unknown.class);

		private final Class<? extends Strategy<State>> controllingClass;
		State(Class<? extends Strategy<State>> controllingClass) {
			this.controllingClass = controllingClass;
		}

		@Override
		public Class<? extends Strategy<State>> getControllingClass() {
			return controllingClass;
		}
	}

	/**
	 * The safes to crack with data associated to each safe.
	 */
	public enum Safe {
		SW(new Tile(3041, 4957), new Tile(3041, 4956), Camera.Direction.WIDE_N),
		SE(new Tile(3043, 4957), new Tile(3043, 4956), Camera.Direction.WIDE_N),
		NW(new Tile(3041, 4962), new Tile(3041, 4963), Camera.Direction.WIDE_S),
		NE(new Tile(3043, 4962), new Tile(3043, 4963), Camera.Direction.WIDE_S);

		public final Tile location;
		public final Tile playerLocation;
		public final Camera.Direction cameraDirection;
		Safe(Tile location, Tile playerLocation, Camera.Direction direction) {
			this.location = location;
			this.playerLocation = playerLocation;
			this.cameraDirection = direction;
		}

		/**
		 * Returns the corresponding Safe Enum from the given Locatable.
		 * @param l
		 * @return The Safe object corresponding to the Locatable, or null if one is not found.
		 */
		@Nullable
		public static Safe fromLocation(Locatable l) {
			for (Safe safe : Safe.values()) {
				if (safe.location.equals(l.tile())) {
					return safe;
				}
			}
			return null;
		}
	}

	@Override
	public void init() {
		showStartupForm(StartupForm.class);
	}

	@Override
	protected State getResetState() {
		return State.UNKNOWN;
	}
}
