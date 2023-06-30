package u6000486;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class Interfaz3 extends JFrame {


	private JPanel contentPane;
	
	String nombre;
	String cantante;
	String ruta;
	int posicion;
	File archivo;
	Funciones f = null;
	DefaultListModel nombres = new DefaultListModel();
	DefaultListModel cantantes = new DefaultListModel();
	Lista lista;
	File[] direc;
	ArrayList <String> rutas = new ArrayList();
	int clicks=0;
	

	public static void Interfaz(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz3 in = new Interfaz3();
					in.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		}); 
	}

	
	private FileNameExtensionFilter fl = new FileNameExtensionFilter("Archivos mp3","mp3"); 
	
	
	
	
	
	public Interfaz3() {
		
		f = new Funciones();
		lista = new Lista();
	
		setSize(622, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		this.setLocation(350, 5);
		//this.setLocationRelativeTo(null); 
		this.setUndecorated(true);
		
		JPanel Jpanel = new JPanel();
		Jpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		Jpanel.setLayout(null); 
		this.getContentPane().add(Jpanel);
		
		
		
		
		
		
		
		
		JLabel lblNombre = new JLabel();
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(157, 250, 310, 19);
		Jpanel.add(lblNombre);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
	
		scrollPane.setBounds(97, 42, 436, 192);
		Jpanel.add(scrollPane);
		
		JList <String> Jlista = new JList();
		Jlista.setBorder(null);
		scrollPane.setViewportView(Jlista);
		Jlista.setForeground(UIManager.getColor("Button.foreground"));
		Jlista.setBackground(new Color(255, 255, 224));
		Jlista.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Jlista.setModel(nombres);
//		Jlista.setModel(cantantes);
		
		Jlista.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				
					if(e.getClickCount() == 1) {
					
					posicion=Jlista.getSelectedIndex();
					ruta = rutas.get(posicion);
					cantante=cantantes.get(posicion).toString();
					nombre = nombres.get(posicion).toString();
					lblNombre.setText(nombre+" - "+cantante);
					
					try {
						
						f.reproCancion(ruta);
						
					} catch (Exception e1) {
					
						e1.printStackTrace();
					
					}
					
				}
				
			}
			
		});
	
		
		JLabel lblBuscar = new JLabel("");
		lblBuscar.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				int sw=0;
				do {
				 sw=0; int pos=0;
				
				String nom=JOptionPane.showInputDialog( null, new JLabel("\nIngrese cancion",ico("/imagenes/busqueda.png",32,32), JLabel.CENTER),"BUSQUEDA", JOptionPane.PLAIN_MESSAGE);
				for(int z=0; z<nombres.getSize();z++) {
					if(nom.equalsIgnoreCase((String) nombres.getElementAt(z))) {
					sw=0;
					pos=z;
					break;
					}else {sw=1;}  
				}
					if(sw==0) {
						Jlista.setSelectedIndex(pos);
						ruta=rutas.get(pos);
						nombre = nombres.get(pos).toString();
						cantante = cantantes.get(pos).toString();
						lblNombre.setText(nombre+" - "+cantante);
						
						try {
						
							f.reproCancion(ruta);
							
						} catch (Exception e1) {
						
							e1.printStackTrace();
						
						
					}}else {
						
						
						JOptionPane.showMessageDialog(null, "ERROR: Cancion no encontrada","ERROR", JOptionPane.PLAIN_MESSAGE,ico("/imagenes/error.png", 32, 32));
						
					}
					
				}while(sw==1);
			}
		});
		lblBuscar.setIcon(ico("/imagenes/Buscarok.png",32,32));
		lblBuscar.setToolTipText("Buscar");
		lblBuscar.setBounds(20, 42, 49, 48);
		Jpanel.add(lblBuscar);
		
		
		JButton btnPlay = new JButton("");
	
		
		btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa 2.png")));

		btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa3.png")));

		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setToolTipText("Inicio/Pausa");

		 
		btnPlay.addMouseListener(new MouseAdapter() {
		     
		     public void mousePressed(MouseEvent e) {
		        clicks++;
		        
		     }
		  });
		 
				
		btnPlay.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
		
				
				ruta = rutas.get(Jlista.getSelectedIndex());
				posicion = Jlista.getSelectedIndex();
				
				try {
					if(clicks%2!=0){
						f.pausar();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play2.png")));

						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play3.png")));

					}else {
						f.seguir();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa 2.png")));
						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa3.png")));

					}
		
				} catch (Exception e) {
		
					e.printStackTrace();
			
				};
			}
		});
		
		btnPlay.setBounds(264, 313, 65, 63);
		Jpanel.add(btnPlay);
		Jlista.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				try {
					if(clicks%2!=0){
						f.pausar();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play2.png")));

						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play3.png")));

					}else {
						f.seguir();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa 2.png")));
						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa3.png")));

					}
		
				} catch (Exception n) {
		
					n.printStackTrace();
			
				};
				
			}
			
});
		lblBuscar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				try {
					if(clicks%2!=0){
						f.pausar();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play2.png")));

						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play3.png")));

					}else {
						f.seguir();
						btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa 2.png")));
						
						btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa3.png")));

					}
		
				} catch (Exception n) {
		
					n.printStackTrace();
			
				};
			}
		});
			
		Jlista.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_SPACE) {
					clicks++;
					ruta = rutas.get(Jlista.getSelectedIndex());
					posicion = Jlista.getSelectedIndex();
					
					
					try {
						if(clicks%2!=0){
							f.pausar();
							btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play2.png")));

							
							btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Play3.png")));

						}else {
							f.seguir();
							
							btnPlay.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa 2.png")));
							btnPlay.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Pausa3.png")));

						}
			
					} catch (Exception e) {
			
						e.printStackTrace();
				
					};
				}
			}
		});
		
		
		
		JButton btnAtras = new JButton("");
		btnAtras.setToolTipText("Atras");
		btnAtras.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Anterior 2.png")));

		btnAtras.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Anterior.png")));
		
		btnAtras.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Jlista.setSelectedIndex(posicion);
				posicion = Jlista.getSelectedIndex();
				if(posicion==0) {
					Jlista.setSelectedIndex(nombres.getSize()-1);
					posicion = Jlista.getSelectedIndex();
					ruta=rutas.get(posicion);
					nombre = nombres.get(posicion).toString();
					cantante = cantantes.get(posicion).toString();
					lblNombre.setText(nombre+" - "+cantante);
					
				}else {
				Jlista.setSelectedIndex(posicion-1);
				posicion = Jlista.getSelectedIndex();
				ruta=rutas.get(posicion);
				nombre = nombres.get(posicion).toString();
				cantante = cantantes.get(posicion).toString();
				lblNombre.setText(nombre+" - "+cantante);
				
				}
					try {
						
						f.reproCancion(ruta);;
						
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
			}
		});
		

		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBounds(157, 313, 65, 63);
		Jpanel.add(btnAtras);
		Jlista.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
					
						Jlista.setSelectedIndex(posicion);
						posicion = Jlista.getSelectedIndex();
						if(posicion==0) {
							Jlista.setSelectedIndex(nombres.getSize()-1);
							posicion = Jlista.getSelectedIndex();
							ruta=rutas.get(posicion);
							nombre = nombres.get(posicion).toString();
							cantante = cantantes.get(posicion).toString();
							lblNombre.setText(nombre+" - "+cantante);
							
						}else {
						Jlista.setSelectedIndex(posicion-1);
						posicion = Jlista.getSelectedIndex();
						ruta=rutas.get(posicion);
						nombre = nombres.get(posicion).toString();
						cantante = cantantes.get(posicion).toString();
						lblNombre.setText(nombre+" - "+cantante);
						
						}
							try {
								
								f.reproCancion(ruta);;
								
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
					
				}
				}
		});
		
		
		JButton btnSig = new JButton("");
		btnSig.setToolTipText("Adelante");
		
		btnSig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Jlista.setSelectedIndex(posicion);
				posicion = Jlista.getSelectedIndex();
				if(posicion==(nombres.getSize()-1)) {
					Jlista.setSelectedIndex(0 );
					posicion = Jlista.getSelectedIndex();
					ruta=rutas.get(posicion);
					nombre = nombres.get(posicion).toString();
					cantante = cantantes.get(posicion).toString();
					lblNombre.setText(nombre+" - "+cantante);
				}else {
				Jlista.setSelectedIndex(posicion+1);
				posicion = Jlista.getSelectedIndex();
				ruta=rutas.get(posicion);
				nombre = nombres.get(posicion).toString();
				cantante = cantantes.get(posicion).toString();
				lblNombre.setText(nombre+" - "+cantante);
				
				}
					try {
						
						f.reproCancion(ruta);;
						
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
						 
			
				
			}
		});
		btnSig.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Siguiente 2.png")));
		btnSig.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Siguiente.png")));

		btnSig.setContentAreaFilled(false);
		btnSig.setBorderPainted(false);
		btnSig.setBounds(366, 326, 65, 50);
		Jpanel.add(btnSig);
		Jlista.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
					Jlista.setSelectedIndex(posicion);
					posicion = Jlista.getSelectedIndex();
					if(posicion==(nombres.getSize()-1)) {
						Jlista.setSelectedIndex(0 );
						posicion = Jlista.getSelectedIndex();
						ruta=rutas.get(posicion);
						nombre = nombres.get(posicion).toString();
						cantante = cantantes.get(posicion).toString();
						lblNombre.setText(nombre+" - "+cantante);
						
					}else {
					Jlista.setSelectedIndex(posicion+1);
					posicion = Jlista.getSelectedIndex();
					ruta=rutas.get(posicion);
					nombre = nombres.get(posicion).toString();
					cantante = cantantes.get(posicion).toString();
					lblNombre.setText(nombre+" - "+cantante);
					}
						try {
							
							f.reproCancion(ruta);;
							
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
				}
			}
		});
	
		
		JButton btnMinimizar = new JButton("");
		
		btnMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setExtendedState(ICONIFIED);
			}
		});
		btnMinimizar.setRolloverSelectedIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Minimizar3.png")));
		btnMinimizar.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Minimizar2.png")));
		btnMinimizar.setName("btnMinimizar");
		btnMinimizar.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Minimizar.png")));
		btnMinimizar.setToolTipText("Minimizar");

		btnMinimizar.setBounds(527, 11, 31, 15);
		
		Jpanel.add(btnMinimizar);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		btnSalir.setRolloverSelectedIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Salir3.png")));
		btnSalir.setRolloverIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Salir2.png")));
		btnSalir.setName("btnSalir");
		btnSalir.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Salir.png")));
		btnSalir.setToolTipText("Salir");

		btnSalir.setBounds(574, 11, 38, 15);
		Jpanel.add(btnSalir);
		
		JLabel lblAgregar = new JLabel("");

		lblAgregar.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Agregar3.png")));
		lblAgregar.setToolTipText("Insertar");
		
		
		lblAgregar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				JFileChooser arch = new JFileChooser();
				arch.setFileFilter(fl);
				arch.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				
				if( arch.showOpenDialog(Jpanel) == JFileChooser.APPROVE_OPTION) {
					archivo = new File("" + arch.getSelectedFile());
					if(archivo.isFile()) {
						
						
						
						lista.insertar(new Icancion(archivo), nombres, cantantes, rutas);
					
					
				}else if(archivo.isDirectory()) {
						
						direc=new File[archivo.list().length];
						direc=archivo.listFiles();
						
						for(int i=0; i<archivo.list().length;i++) {
							
							
							
								lista.insertar(new Icancion(direc[i]), nombres,cantantes, rutas);
							
						
						}
						
					}
				}
			}
			});		lblAgregar.setBounds(574, 307, 38, 35); Jpanel.add(lblAgregar);

		
		JLabel lblEliminar = new JLabel("");
		lblEliminar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				posicion=Jlista.getSelectedIndex();
				lista.eliminar(posicion, nombres, cantantes, rutas);
			
				
				
				
				
				
				try {
				
					f.parar();
					
				} catch (Exception e1) {
				
					e1.printStackTrace();
				
				}
				ruta = rutas.get(posicion);
			}
		});

		lblEliminar.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Eliminar.png")));
		lblEliminar.setToolTipText("Eliminar");
		lblEliminar.setBounds(574, 353, 38, 35);
		Jpanel.add(lblEliminar);
		
		
		
	
		
		JLabel lblFond1 = new JLabel("New label");
		lblFond1.addMouseListener(new MouseAdapter() {
		
			
		});
		
		lblFond1.setIcon(new ImageIcon(Interfaz3.class.getResource("/imagenes/Fondo.png")));
		lblFond1.setBounds(-28, -6, 650, 292);
		Jpanel.add(lblFond1);
		
	
		
}
	public  Icon ico(String path, int w, int h) {
		Icon img =new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(w,h, java.awt.Image.SCALE_SMOOTH));
		return img ;
	}
	
}
