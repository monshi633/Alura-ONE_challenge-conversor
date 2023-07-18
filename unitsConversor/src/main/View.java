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
import util.ValidateInput;

import java.awt.CardLayout;
import javax.swing.event.CaretListener;

import javax.swing.event.CaretEvent;
import java.awt.Color;
import java.awt.EventQueue;

import com.formdev.flatlaf.*;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel contentPane;
	private JTextField inputTextField;
	private JTextField outputTextField;

	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatDarculaLaf());
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private View() {
		setResizable(false);
//		Attributes
		Font font = new Font("Montserrat", Font.PLAIN, 14);
		Color errorColor = new Color(207, 102, 121);

		/*
		 * Window and elements setup
		 */
//		ContentPane
		setTitle("Conversor de unidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 275);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[20px][grow][][][][25px:n][][15px:n][]"));
		setContentPane(contentPane);

//		Label
		JLabel titleLabel = new JLabel("Elije el tipo de unidades a convertir");
		titleLabel.setFont(font);
		contentPane.add(titleLabel, "cell 0 0 3 1");
		
//		TypeSelector
		JRadioButton currenciesRadioButton = new JRadioButton("Monedas");
		currenciesRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		currenciesRadioButton.setFont(font);
		currenciesRadioButton.setSelected(true);
		contentPane.add(currenciesRadioButton, "cell 0 2");
		
		JRadioButton temperaturesRadioButton = new JRadioButton("Temperaturas");
		temperaturesRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
		temperaturesRadioButton.setFont(font);
		contentPane.add(temperaturesRadioButton, "cell 1 2");
		
		ButtonGroup units = new ButtonGroup();
		units.add(currenciesRadioButton);
		units.add(temperaturesRadioButton);
		
//		TextField
		inputTextField = new JTextField("");
		inputTextField.setFont(font);
		contentPane.add(inputTextField, "cell 0 4,growx");

		JLabel validationErrorLabel = new JLabel();
		validationErrorLabel.setFont(font);
		validationErrorLabel.setForeground(errorColor);
		validationErrorLabel.setVisible(false);
		contentPane.add(validationErrorLabel, "cell 0 5 2 1");
		
		outputTextField = new JTextField();
		outputTextField.setFont(font);
		outputTextField.setEditable(false);
		contentPane.add(outputTextField, "cell 0 6,growx");

//		UnitSelectorPane
		JPanel subSelectorTopPane = new JPanel();
		subSelectorTopPane.setLayout(new CardLayout(0, 0));
		contentPane.add(subSelectorTopPane, "cell 1 4,grow");

		JPanel subSelectorBottomPane = new JPanel();
		subSelectorBottomPane.setLayout(new CardLayout(0, 0));
		contentPane.add(subSelectorBottomPane, "cell 1 6,grow");

//		UnitSelectors
		JComboBox<CurrencyUnit> currenciesTopBox = new JComboBox<>(CurrencyUnit.values());
		currenciesTopBox.setSelectedIndex(0);
		currenciesTopBox.setFont(font);
		subSelectorTopPane.add(currenciesTopBox);

		JComboBox<CurrencyUnit> currenciesBottomBox = new JComboBox<>(CurrencyUnit.values());
		currenciesBottomBox.setSelectedIndex(1);
		currenciesBottomBox.setFont(font);
		subSelectorBottomPane.add(currenciesBottomBox);

		JComboBox<TemperatureUnit> temperaturesTopBox = new JComboBox<>(TemperatureUnit.values());
		temperaturesTopBox.setSelectedIndex(0);
		temperaturesTopBox.setVisible(false);
		temperaturesTopBox.setFont(font);
		subSelectorTopPane.add(temperaturesTopBox);

		JComboBox<TemperatureUnit> temperaturesBottomBox = new JComboBox<>(TemperatureUnit.values());
		temperaturesBottomBox.setSelectedIndex(1);
		temperaturesBottomBox.setVisible(false);
		temperaturesBottomBox.setFont(font);
		subSelectorBottomPane.add(temperaturesBottomBox);

//		ConvertButton
		JButton convertButton = new JButton("Convertir");
		convertButton.setFont(font);
		convertButton.setEnabled(false);
		contentPane.add(convertButton, "cell 0 8 2 1,alignx center,aligny center");

		
//		Action listeners
		/*
		 * currenciesRadioButton
		 * temperaturesRadioButton
		 * 
		 * This action listeners change the value of which boxes are visible
		 * depending on the radio button selected
		 */
		currenciesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temperaturesTopBox.setVisible(false);
				temperaturesBottomBox.setVisible(false);
				currenciesTopBox.setVisible(true);
				currenciesBottomBox.setVisible(true);
			}
		});
		
		temperaturesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temperaturesTopBox.setVisible(true);
				temperaturesBottomBox.setVisible(true);
				currenciesTopBox.setVisible(false);
				currenciesBottomBox.setVisible(false);
			}
		});
		
		/*
		 * temperaturesTopBox
		 * temperaturesBottomBox
		 * currenciesTopBox
		 * currenciesBottomBox
		 * 
		 * This action listeners change the value of the non-selected box to un-match
		 * the value of the selected one
		 * i.e: There can't be a conversion from Celsius to Celsius
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
		
		/*
		 * This action listener enables input validation in real time
		 */

		inputTextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (!ValidateInput.validateInput(inputTextField.getText().toString())) {
					inputTextField.setForeground(errorColor);
					validationErrorLabel.setText(ValidateInput.getValidationError());
					validationErrorLabel.setVisible(true);
					convertButton.setEnabled(false);
				} else {
					inputTextField.setForeground(new Color(187, 187, 187));
					validationErrorLabel.setVisible(false);
					convertButton.setEnabled(true);
					/*
					 * disabled because of HttpResponseCode: 429 - Too many requests
					 * otherwise it would eliminate the need of a convert button
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
					 */
				}
			}
		});

		/*
		 * This action listener performs the conversion and returns the output value
		 */
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
	}
}