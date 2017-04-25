package gui;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometria3D.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtCx;
	private JTextField txtNx;
	private JTextField txtVx;
	private JTextField txtCy;
	private JTextField txtCz;
	private JTextField txtNy;
	private JTextField txtNz;
	private JTextField txtVy;
	private JTextField txtVz;
	private JLabel lblImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCamera = new JLabel("Camera");
		lblCamera.setForeground(Color.WHITE);
		lblCamera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamera.setBounds(10, 11, 185, 14);
		contentPane.add(lblCamera);
		
		JLabel lblC = new JLabel("C:");
		lblC.setForeground(Color.WHITE);
		lblC.setBounds(10, 36, 15, 20);
		contentPane.add(lblC);
		
		txtCx = new JTextField();
		txtCx.setText("0");
		txtCx.setBounds(35, 36, 30, 20);
		contentPane.add(txtCx);
		txtCx.setColumns(10);
		
		txtCy = new JTextField();
		txtCy.setText("0");
		txtCy.setColumns(10);
		txtCy.setBounds(35, 67, 30, 20);
		contentPane.add(txtCy);
		
		txtCz = new JTextField();
		txtCz.setText("0");
		txtCz.setColumns(10);
		txtCz.setBounds(35, 98, 30, 20);
		contentPane.add(txtCz);
		
		JLabel lblN = new JLabel("N:");
		lblN.setForeground(Color.WHITE);
		lblN.setBounds(75, 36, 15, 20);
		contentPane.add(lblN);
		
		txtNx = new JTextField();
		txtNx.setText("0");
		txtNx.setColumns(10);
		txtNx.setBounds(100, 36, 30, 20);
		contentPane.add(txtNx);
		
		txtNy = new JTextField();
		txtNy.setText("1");
		txtNy.setColumns(10);
		txtNy.setBounds(100, 67, 30, 20);
		contentPane.add(txtNy);
		
		txtNz = new JTextField();
		txtNz.setText("0");
		txtNz.setColumns(10);
		txtNz.setBounds(100, 98, 30, 20);
		contentPane.add(txtNz);
		
		JLabel lblV = new JLabel("V:");
		lblV.setForeground(Color.WHITE);
		lblV.setBounds(140, 36, 15, 20);
		contentPane.add(lblV);
		
		txtVx = new JTextField();
		txtVx.setText("0");
		txtVx.setColumns(10);
		txtVx.setBounds(165, 36, 30, 20);
		contentPane.add(txtVx);
		
		txtVy = new JTextField();
		txtVy.setText("0");
		txtVy.setColumns(10);
		txtVy.setBounds(165, 67, 30, 20);
		contentPane.add(txtVy);
		
		txtVz = new JTextField();
		txtVz.setText("-1");
		txtVz.setColumns(10);
		txtVz.setBounds(165, 98, 30, 20);
		contentPane.add(txtVz);
		
		lblImagem = new JLabel("");
		lblImagem.setBackground(Color.BLACK);
		lblImagem.setBounds(224, 11, 200, 200);
		contentPane.add(lblImagem);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				verImagem();
			}
		});
		btnNewButton.setBounds(224, 222, 200, 28);
		contentPane.add(btnNewButton);
	}
	
	private void verImagem() {
		Vetor3D c = new Vetor3D(Double.parseDouble(txtCx.getText()), Double.parseDouble(txtCy.getText()), Double.parseDouble(txtCz.getText()));
		Vetor3D n = new Vetor3D(Double.parseDouble(txtNx.getText()), Double.parseDouble(txtNy.getText()), Double.parseDouble(txtNz.getText()));
		Vetor3D v = new Vetor3D(Double.parseDouble(txtVx.getText()), Double.parseDouble(txtVy.getText()), Double.parseDouble(txtVz.getText()));
		Camera camera = new Camera(c, n, v, 1, 1, 1, (short)200, (short)200);
		Triangulo3D t = new Triangulo3D(new Vetor3D(0, 0, -1), new Vetor3D(1, -1, -1), new Vetor3D(-1, -1, -1));//Prototipo
		camera.verTriangulo(t);
		BufferedImage buffer = new BufferedImage(camera.xTela, camera.yTela, BufferedImage.TYPE_INT_ARGB);
		buffer.setRGB(0, 0, camera.xTela, camera.yTela, matrizParaArray(camera), 0, camera.xTela);
		lblImagem.setIcon(new ImageIcon(buffer));
	}
	
	private int[] matrizParaArray(Camera camera) {
		int[] array = new int[camera.xTela * camera.yTela];
		for (int col = 0; col < camera.xTela; col++) {
			for (int lin = 0; lin < camera.yTela; lin++) {
				array[camera.xTela * lin + col] = camera.tela[col][lin];
			}
		}
		return array;
	}
}
