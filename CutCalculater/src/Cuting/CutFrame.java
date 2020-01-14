package Cuting;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CutFrame implements ActionListener, ListSelectionListener {
	JTextField cutSpeed, diamCutTool, podachaNaZub;
	JList<String> jlst;
	JScrollPane jscrlp;
	JSpinner numbersOfCuts;

	JButton butCalc;
	JLabel labCutSpeed, labContents, labdiamCutTool, labnumbersOfCuts, labOut01, labOut02, labPodachaNaZub;
	String[] metalls = { "��������", "����", "�����", "����������" };

	public CutFrame() {
		JFrame jfrm = new JFrame("������ ������� V1.0");
		jfrm.setLayout(new FlowLayout());
		jfrm.setBounds(300, 250, 250, 300);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cutSpeed = new JTextField(4);
		diamCutTool = new JTextField(4);
		numbersOfCuts = new JSpinner();
		podachaNaZub = new JTextField(4);

		jlst = new JList<String>(metalls);
		jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cutSpeed.setText("100");
		diamCutTool.setText("10");
		numbersOfCuts.setValue(2);
		podachaNaZub.setText("0.05");

		butCalc = new JButton("����������");

		labCutSpeed = new JLabel("C������� ������� =");
		labdiamCutTool = new JLabel("������� ����� =");
		labnumbersOfCuts = new JLabel("���������� ������ =");
		labPodachaNaZub = new JLabel("������ �� ��� (��) =");
		labOut01 = new JLabel("");
		labOut02 = new JLabel("");

		labOut01.setPreferredSize(new Dimension(230, 20));
		labOut02.setPreferredSize(new Dimension(230, 20));
		jlst.setPreferredSize(new Dimension(150, 75));
		numbersOfCuts.setSize(120, 10);

		butCalc.addActionListener(this);
		jlst.addListSelectionListener(this);

		jfrm.add(jlst);
		jfrm.add(labCutSpeed);
		jfrm.add(cutSpeed);
		jfrm.add(labdiamCutTool);
		jfrm.add(diamCutTool);
		jfrm.add(labnumbersOfCuts);
		jfrm.add(numbersOfCuts);
		jfrm.add(labPodachaNaZub);
		jfrm.add(podachaNaZub);

		jfrm.add(butCalc);
		jfrm.add(labOut01);
		jfrm.add(labOut02);

		jfrm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int s = Integer.parseInt(cutSpeed.getText());
		double d = Double.parseDouble(diamCutTool.getText());
		int n = (int) numbersOfCuts.getValue();
		double p = Double.parseDouble(podachaNaZub.getText());
		double out1 = (s * 1000) / (Math.PI * d);
		double out2 = ((out1 > 8000) ? out1 = 8000 : out1) * n * p;
		labOut01.setText("������� ��������: " + Math.round((out1 > 8000) ? out1 = 8000 : out1));
		labOut02.setText("������: " + Math.round(out2));

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int ind = jlst.getSelectedIndex();
		podachaNaZub.setText(calcPodachNaZub(ind,diamCutTool.getText()));
		switch (ind) {
		case 0:
			cutSpeed.setText("350");
			numbersOfCuts.setValue(2);
			break;
		case 1:
			cutSpeed.setText("250");
			numbersOfCuts.setValue(2);
			break;
		case 2:
			cutSpeed.setText("120");
			numbersOfCuts.setValue(4);
			break;
		case 3:
			cutSpeed.setText("60");
			numbersOfCuts.setValue(4);
			
			break;

		default:
			break;
		}
		actionPerformed(null);

	}
	private static String calcPodachNaZub(int ind, String ds) {
		String out="";
		double d = Double.parseDouble(ds);
		switch (ind) {
		case 0:
			out += (0.25/25)*d;
			break;
		case 1:
			out += (0.15/25)*d;
			break;
		case 2:
			out += (0.1/25)*d;
			break;
		case 3:
			out += (0.065/25)*d;
			break;

		default:
			break;
		}
		
		return out;
	}

}
