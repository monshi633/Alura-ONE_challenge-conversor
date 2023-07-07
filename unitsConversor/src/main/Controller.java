package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import enums.CurrencyUnit;
import enums.TemperatureUnit;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

import net.miginfocom.swing.MigLayout;
import util.CurrencyConverter;
import util.TemperatureConverter;

import java.awt.CardLayout;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	private JPanel contentPane;
	private JTextField inputTextField;
	private JTextField outputTextField;

	public Controller() {
//		Attributes
		Font font = new Font("Montserrat", Font.PLAIN, 14);

//		ContentPane
		setTitle("Conversor de unidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[20px][][][][25px:n][][][][grow]"));

//		Label
		JLabel titleLabel = new JLabel("Elije el tipo de unidades a convertir");
		titleLabel.setFont(font);
		contentPane.add(titleLabel, "cell 0 0 3 1");

//		SelectorPane
		JPanel subSelectorTopPane = new JPanel();
		contentPane.add(subSelectorTopPane, "cell 1 3,grow");
		subSelectorTopPane.setLayout(new CardLayout(0, 0));

		JPanel subSelectorBottomPane = new JPanel();
		contentPane.add(subSelectorBottomPane, "cell 1 5,grow");
		subSelectorBottomPane.setLayout(new CardLayout(0, 0));

//		UnitSelectors
		JComboBox<CurrencyUnit> currenciesTopBox = new JComboBox<>(CurrencyUnit.values());
		currenciesTopBox.setSelectedIndex(0);
		subSelectorTopPane.add(currenciesTopBox);

		JComboBox<CurrencyUnit> currenciesBottomBox = new JComboBox<>(CurrencyUnit.values());
		currenciesBottomBox.setSelectedIndex(1);
		subSelectorBottomPane.add(currenciesBottomBox);

		JComboBox<TemperatureUnit> temperaturesTopBox = new JComboBox<>(TemperatureUnit.values());
		temperaturesTopBox.setSelectedIndex(0);
		subSelectorTopPane.add(temperaturesTopBox);
		temperaturesTopBox.setVisible(false);

		JComboBox<TemperatureUnit> temperaturesBottomBox = new JComboBox<>(TemperatureUnit.values());
		temperaturesBottomBox.setSelectedIndex(1);
		subSelectorBottomPane.add(temperaturesBottomBox);
		temperaturesBottomBox.setVisible(false);

		/*
		 * This action listeners change the value of the non-selected box to un-match
		 * the value of the selected one
		 */
		temperaturesTopBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temperaturesTopBox.getSelectedIndex() == temperaturesBottomBox.getSelectedIndex()) {
					try {
						temperaturesBottomBox.setSelectedIndex(temperaturesBottomBox.getSelectedIndex() + 1);
					} catch (Exception e1) {
						temperaturesBottomBox.setSelectedIndex(0);
					}
				}
			}
		});

		temperaturesBottomBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temperaturesBottomBox.getSelectedIndex() == temperaturesTopBox.getSelectedIndex()) {
					try {
						temperaturesTopBox.setSelectedIndex(temperaturesTopBox.getSelectedIndex() + 1);
					} catch (Exception e1) {
						temperaturesTopBox.setSelectedIndex(0);
					}
				}
			}
		});

		currenciesTopBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currenciesTopBox.getSelectedIndex() == currenciesBottomBox.getSelectedIndex()) {
					try {
						currenciesBottomBox.setSelectedIndex(currenciesBottomBox.getSelectedIndex() + 1);
					} catch (Exception e1) {
						currenciesBottomBox.setSelectedIndex(0);
					}
				}
			}
		});

		currenciesBottomBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currenciesBottomBox.getSelectedIndex() == currenciesTopBox.getSelectedIndex()) {
					try {
						currenciesTopBox.setSelectedIndex(currenciesTopBox.getSelectedIndex() + 1);
					} catch (Exception e1) {
						currenciesTopBox.setSelectedIndex(0);
					}
				}
			}
		});

//		TextField
		JLabel validationErrorLabel = new JLabel();
		validationErrorLabel.setFont(font);
		validationErrorLabel.setForeground(new Color(255, 0, 0));
		validationErrorLabel.setVisible(false);
		contentPane.add(validationErrorLabel, "cell 0 4 2 1");

		inputTextField = new JTextField();
		inputTextField.setFont(font);
		contentPane.add(inputTextField, "cell 0 3,growx");
		inputTextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (!ValidateInput.validateInput(inputTextField.getText().toString())) {
					inputTextField.setForeground(new Color(255, 0, 0));
					validationErrorLabel.setText(ValidateInput.getValidationError());
					validationErrorLabel.setVisible(true);
				} else {
					inputTextField.setForeground(new Color(0, 0, 0));
					validationErrorLabel.setVisible(false);
				}
			}
		});

		outputTextField = new JTextField();
		outputTextField.setFont(font);
		outputTextField.setEditable(false);
		contentPane.add(outputTextField, "cell 0 5,growx");

//		TypeSelector
		JRadioButton currenciesRadioButton = new JRadioButton("Monedas");
		currenciesRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		currenciesRadioButton.setFont(font);
		currenciesRadioButton.setSelected(true);
		currenciesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temperaturesTopBox.setVisible(false);
				temperaturesBottomBox.setVisible(false);
				currenciesTopBox.setVisible(true);
				currenciesBottomBox.setVisible(true);
			}
		});
		contentPane.add(currenciesRadioButton, "cell 0 1");

		JRadioButton temperaturesRadioButton = new JRadioButton("Temperaturas");
		temperaturesRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
		temperaturesRadioButton.setFont(font);
		temperaturesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temperaturesTopBox.setVisible(true);
				temperaturesBottomBox.setVisible(true);
				currenciesTopBox.setVisible(false);
				currenciesBottomBox.setVisible(false);
			}
		});
		contentPane.add(temperaturesRadioButton, "cell 1 1");

		ButtonGroup units = new ButtonGroup();
		units.add(currenciesRadioButton);
		units.add(temperaturesRadioButton);
		
		//		ConvertButton
				JButton convertButton = new JButton("Convertir");
				convertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							BigDecimal inputValue = new BigDecimal(inputTextField.getText().toString());
							BigDecimal outputValue = new BigDecimal("0");
							if (currenciesRadioButton.isSelected()) {
								CurrencyUnit fromUnit = (CurrencyUnit) currenciesTopBox.getSelectedItem();
								CurrencyUnit toUnit = (CurrencyUnit) currenciesBottomBox.getSelectedItem();
								outputValue = CurrencyConverter.getConversionValue(inputValue, fromUnit, toUnit);
							} else if (temperaturesRadioButton.isSelected()) {
								TemperatureUnit fromUnit = (TemperatureUnit) temperaturesTopBox.getSelectedItem();
								TemperatureUnit toUnit = (TemperatureUnit) temperaturesBottomBox.getSelectedItem();
								outputValue = TemperatureConverter.getConversionValue(inputValue, fromUnit, toUnit);
							}
							outputTextField.setText(outputValue.toString());
						} catch (Exception e2) {
							outputTextField.setText("NaN");
						}
					}
				});
				contentPane.add(convertButton, "cell 1 7");
	}
}