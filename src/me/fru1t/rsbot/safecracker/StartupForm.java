package me.fru1t.rsbot.safecracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.fru1t.common.annotations.Inject;
import me.fru1t.rsbot.RoguesDenSafeCracker;
import me.fru1t.rsbot.common.framework.AbstractSettings;
import me.fru1t.rsbot.common.framework.AbstractStartupForm;
import me.fru1t.rsbot.common.items.Food;
import me.fru1t.slick.util.Provider;


public class StartupForm extends AbstractStartupForm<Settings> {
	// Form testing main method.
	public static void main(String[] args) {
		new StartupForm();
	}

	// For testing
	private StartupForm() {
		super();
	}

	private JFrame settingsForm;

	/**
	 * Create the application.
	 */
	@Inject
	public StartupForm(Provider<AbstractSettings.Callback<Settings>> callback) {
		super(callback);

		this.initialize();
		this.settingsForm.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		settingsForm = new JFrame();
		settingsForm.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 16));
		settingsForm.setResizable(false);
		settingsForm.getContentPane().setForeground(Color.CYAN);
		settingsForm.getContentPane().setBackground(Color.DARK_GRAY);
		settingsForm.getContentPane().setLayout(null);

		JLabel lblFood = new JLabel("Food");
		lblFood.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFood.setForeground(Color.CYAN);
		lblFood.setBounds(20, 20, 46, 14);
		settingsForm.getContentPane().add(lblFood);

		JLabel lblNewLabel = new JLabel("Have food in your inventory to automatically set this");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(20, 35, 364, 14);
		settingsForm.getContentPane().add(lblNewLabel);

		// Java 7 shenanigans doesn't have 'effectively final'.
		final JComboBox foodSelectorComboBox = new JComboBox();
		foodSelectorComboBox.setForeground(Color.BLACK);
		foodSelectorComboBox.setToolTipText("Select food and click add to add it to the list of food you want consumed during safecracking");
		foodSelectorComboBox.setModel(new DefaultComboBoxModel(Food.values()));
		foodSelectorComboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		foodSelectorComboBox.setBackground(Color.WHITE);
		foodSelectorComboBox.setBounds(20, 52, 364, 20);
		settingsForm.getContentPane().add(foodSelectorComboBox);

		JLabel lblPreferredSafe = new JLabel("Preferred Safe");
		lblPreferredSafe.setFont(new Font("Calibri", Font.BOLD, 16));
		lblPreferredSafe.setForeground(Color.CYAN);
		lblPreferredSafe.setBounds(20, 231, 141, 14);
		settingsForm.getContentPane().add(lblPreferredSafe);

		JLabel lblUnlessYouHave = new JLabel("Unless you have an extreme prejudice against a certain safe,");
		lblUnlessYouHave.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUnlessYouHave.setForeground(Color.GRAY);
		lblUnlessYouHave.setBounds(20, 247, 364, 14);
		settingsForm.getContentPane().add(lblUnlessYouHave);

		JLabel lblThisSettingOn = new JLabel("it's best to leave this on automatic.");
		lblThisSettingOn.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblThisSettingOn.setForeground(Color.GRAY);
		lblThisSettingOn.setBounds(20, 259, 364, 14);
		settingsForm.getContentPane().add(lblThisSettingOn);

		final JComboBox preferredSafeComboBox = new JComboBox();
		preferredSafeComboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		preferredSafeComboBox.setModel(new DefaultComboBoxModel(RoguesDenSafeCracker.Safe.values()));
		preferredSafeComboBox.setForeground(Color.BLACK);
		preferredSafeComboBox.setBackground(Color.WHITE);
		preferredSafeComboBox.setBounds(20, 277, 354, 20);
		settingsForm.getContentPane().add(preferredSafeComboBox);

		final JSpinner foodAmountSpinner = new JSpinner();
		foodAmountSpinner.setEnabled(false);
		foodAmountSpinner.setModel(new SpinnerNumberModel(4, 0, 27, 1));
		foodAmountSpinner.setFont(new Font("Dialog", Font.PLAIN, 11));
		foodAmountSpinner.setForeground(Color.CYAN);
		foodAmountSpinner.setBackground(Color.DARK_GRAY);
		foodAmountSpinner.setBounds(322, 141, 54, 17);
		settingsForm.getContentPane().add(foodAmountSpinner);

		JLabel lblFoodAmountPer = new JLabel("Banking Style");
		lblFoodAmountPer.setForeground(Color.CYAN);
		lblFoodAmountPer.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFoodAmountPer.setBounds(20, 103, 204, 14);
		settingsForm.getContentPane().add(lblFoodAmountPer);

		final JRadioButton bankConstant = new JRadioButton("Same amount every time");
		bankConstant.setFont(new Font("Calibri", Font.PLAIN, 14));
		bankConstant.setForeground(Color.CYAN);
		bankConstant.setBackground(Color.DARK_GRAY);
		bankConstant.setBounds(20, 138, 266, 23);
		settingsForm.getContentPane().add(bankConstant);

		final JRadioButton bankAutomatic = new JRadioButton("Automatic amount");
		bankAutomatic.setSelected(true);
		bankAutomatic.setForeground(Color.CYAN);
		bankAutomatic.setFont(new Font("Calibri", Font.PLAIN, 14));
		bankAutomatic.setBackground(Color.DARK_GRAY);
		bankAutomatic.setBounds(20, 118, 266, 23);
		settingsForm.getContentPane().add(bankAutomatic);
		settingsForm.setTitle("Fru1tstand's Safe Cracker");
		settingsForm.setBackground(Color.BLACK);
		settingsForm.setAlwaysOnTop(true);
		settingsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingsForm.setBounds(100, 100, 400, 465);

		final JRadioButton bankPreset1 = new JRadioButton("Preset 1");
		bankPreset1.setForeground(Color.CYAN);
		bankPreset1.setFont(new Font("Dialog", Font.PLAIN, 14));
		bankPreset1.setBackground(Color.DARK_GRAY);
		bankPreset1.setBounds(20, 158, 266, 23);
		settingsForm.getContentPane().add(bankPreset1);

		final JRadioButton bankPreset2 = new JRadioButton("Preset 2");
		bankPreset2.setForeground(Color.CYAN);
		bankPreset2.setFont(new Font("Dialog", Font.PLAIN, 14));
		bankPreset2.setBackground(Color.DARK_GRAY);
		bankPreset2.setBounds(20, 178, 266, 23);
		settingsForm.getContentPane().add(bankPreset2);

		ButtonGroup foodSelectMethod = new ButtonGroup();
		foodSelectMethod.add(bankConstant);
		foodSelectMethod.add(bankAutomatic);
		foodSelectMethod.add(bankPreset1);
		foodSelectMethod.add(bankPreset2);

		JButton startBtn = new JButton("Start!");
		startBtn.setForeground(Color.BLACK);
		startBtn.setBackground(Color.WHITE);
		startBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
		startBtn.setBounds(20, 392, 110, 23);
		settingsForm.getContentPane().add(startBtn);

		JLabel lblQuestionsCommentsWant = new JLabel("Questions? Comments? Feature requests?");
		lblQuestionsCommentsWant.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuestionsCommentsWant.setForeground(Color.GRAY);
		lblQuestionsCommentsWant.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblQuestionsCommentsWant.setBounds(20, 355, 354, 14);
		settingsForm.getContentPane().add(lblQuestionsCommentsWant);

		JLabel lblPostInThe = new JLabel("Post in the forums and I'll be sure to respond.");
		lblPostInThe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPostInThe.setForeground(Color.GRAY);
		lblPostInThe.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPostInThe.setBounds(20, 365, 354, 14);
		settingsForm.getContentPane().add(lblPostInThe);

		JLabel lblCreatedWithLove = new JLabel("Created, with love, by fru1tstand.");
		lblCreatedWithLove.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCreatedWithLove.setForeground(Color.CYAN);
		lblCreatedWithLove.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCreatedWithLove.setBounds(20, 401, 354, 14);
		settingsForm.getContentPane().add(lblCreatedWithLove);

		JLabel lblThisScriptIs = new JLabel("This script is open source. Check it out on Github.");
		lblThisScriptIs.setHorizontalAlignment(SwingConstants.TRAILING);
		lblThisScriptIs.setForeground(Color.GRAY);
		lblThisScriptIs.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblThisScriptIs.setBounds(20, 375, 354, 14);
		settingsForm.getContentPane().add(lblThisScriptIs);

		// Events and listeners
		{
			// Enable/Disable food amount spinner
			bankConstant.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					foodAmountSpinner.setEnabled(bankConstant.isSelected());
				}
			});

			// Start button action
			// TODO: #validate settings before closing the form and display user friendly errors.
			startBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					settingsForm.setVisible(false);
					Settings settings = new Settings();
					settings.setPreferredSafe((RoguesDenSafeCracker.Safe) preferredSafeComboBox.getSelectedItem());
					if (bankAutomatic.isSelected()) {
						settings.setBankStyle(Settings.BankStyle.AUTOMATIC);
					} else if (bankConstant.isSelected()) {
						settings.setBankStyle(Settings.BankStyle.CONSTANT);
					} else if (bankPreset1.isSelected()) {
						settings.setBankStyle(Settings.BankStyle.PRESET_1);
					} else if (bankPreset2.isSelected()) {
						settings.setBankStyle(Settings.BankStyle.PRESET_2);
					}
					settings.setFoodQuantity((Integer) foodAmountSpinner.getModel().getValue());
					settings.setFood((Food) foodSelectorComboBox.getSelectedItem());
					settingsForm.dispose();

					setSettings(settings);
				}
			});
		}
	}
}
