package unitsConversor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField inputTextField;
	private JTextField outputTextField;

	public Window() {
//		Attributes
		Font font = new Font("Montserrat", Font.PLAIN, 14);
		String[] currenciesList = { "ARS", "USD", "EUR", "GBP", "JPY", "KRW" };
		String[] temperaturesList = { "C°", "F°", "K°" };

//		ContentPane
		setTitle("Conversor de unidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][]", "[][][][][][][grow]"));

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

//		SubSelector
		JComboBox<?> currenciesTopBox = new JComboBox<>(currenciesList);
		currenciesTopBox.setSelectedIndex(0);
		subSelectorTopPane.add(currenciesTopBox);

		JComboBox<?> temperaturesTopBox = new JComboBox<>(temperaturesList);
		temperaturesTopBox.setSelectedIndex(0);
		subSelectorTopPane.add(temperaturesTopBox);

		JComboBox<?> currenciesBottomBox = new JComboBox<>(currenciesList);
		currenciesBottomBox.setSelectedIndex(1);
		subSelectorBottomPane.add(currenciesBottomBox);

		JComboBox<?> temperaturesBottomBox = new JComboBox<>(temperaturesList);
		temperaturesBottomBox.setSelectedIndex(1);
		subSelectorBottomPane.add(temperaturesBottomBox);

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
		inputTextField = new JTextField();
		inputTextField.setFont(font);
		contentPane.add(inputTextField, "cell 0 3,growx");
		inputTextField.setColumns(10);

		outputTextField = new JTextField();
		outputTextField.setFont(font);
		outputTextField.setEditable(false);
		contentPane.add(outputTextField, "cell 0 5,growx");
		outputTextField.setColumns(10);

//		ConvertBurron
		JButton convertButton = new JButton("Convertir");
		convertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obtainedInput = inputTextField.getText();
				outputTextField.setText(obtainedInput + "convertido");
			}
		});
		contentPane.add(convertButton, "cell 2 4");

//		UnitsSelector
		JRadioButton currenciesRadioButton = new JRadioButton("Monedas");
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
	}
}