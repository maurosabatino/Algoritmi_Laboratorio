package sabatino.esercizio13.AVLTree;

import java.util.Scanner;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.GraphicsEnvironment.*;
import java.io.File;
import static javax.swing.JFileChooser.*;
import static javax.swing.JOptionPane.*;


/**
 interfaccia grafica per albero di ricerca di studenti
*/

public class BinSearchTreeGUI extends JFrame {
  private static final long serialVersionUID = 1L;
  private AVLTree tree;
  private JPanel controlPanel = new JPanel();
  private JTextField text1 = new JTextField(20);
  private JTextField text2 = new JTextField(4);
  private JButton delButton = new JButton("elimina");
  private JButton insButton = new JButton("inserisci");
  private JButton searchButton = new JButton("ricerca");
  private JLabel labelChiave = new JLabel("             chiave (num. intero):");
  private JLabel labelNome = new JLabel("  nome:");
  private JLabel labelVuota = new JLabel("             ");


  public BinSearchTreeGUI(String title) {
    super(title);
    tree = new AVLTree();
    readFile();
    draw();
    tree.printInOrder();

    insButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String nome = text1.getText().trim();
          int num = Integer.parseInt(text2.getText());
          tree.put(new Elem(nome, num));
          repaint();
          tree.printInOrder();
        }
      }
    );

    delButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int key = Integer.parseInt(text2.getText());
          tree.remove(key);
          repaint();
        }
      }
    );

    searchButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int key = Integer.parseInt(text2.getText());
          Elem elem = (Elem) tree.get(key);
          if(elem == null) text1.setText("chiave non presente");
          else text1.setText(elem.elem);
          repaint();
        }
      }
    );
  }

  public void draw() {
    GraphicsEnvironment ge = getLocalGraphicsEnvironment();
    Rectangle bounds = ge.getMaximumWindowBounds();
    int x = bounds.x + bounds.width/4;
    int y = bounds.y + bounds.height/4;
    setBounds(x,y,2*bounds.width/3,2*bounds.height/3);
    add(BorderLayout.CENTER, new DrawingPanel(tree));
    controlPanel.add(searchButton);
    controlPanel.add(delButton);
    controlPanel.add(labelChiave);
    controlPanel.add(text2);
    controlPanel.add(labelNome);
    controlPanel.add(text1);
    controlPanel.add(labelVuota);
    controlPanel.add(insButton);
    add(BorderLayout.SOUTH, controlPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  private class FiltroTxt extends javax.swing.filechooser.FileFilter {
    public boolean accept(File file) {
      if (file.isDirectory()) return true;
      else
        return file.getName().endsWith(".txt");
    }

    public String getDescription() {
      return "*.txt";
    }
  }

  private File directoryCorrente() {
    File f;
    try {
      f = new File(new File(".").getCanonicalPath());
    } catch (IOException e) {
      showErrorMessage("Errore: non posso accedere alla cartella corrente");
      return null;
    }
    return f;
  }

  private void readFile() {
    JFileChooser fileChooser = new JFileChooser(directoryCorrente());
    fileChooser.setFileSelectionMode(FILES_AND_DIRECTORIES);
    fileChooser.setFileFilter(new FiltroTxt());
    int option = fileChooser.showOpenDialog(this);
    if(option == APPROVE_OPTION) {
      File fileScelto = fileChooser.getSelectedFile();
      try {
        Scanner input = new Scanner(fileScelto);
        while(input.hasNextLine()) {
          int matr = input.nextInt();
          String nome = input.nextLine().trim();
          tree.put(new Elem(nome, matr));
        }
        input.close();
      }
      catch(Exception ex) {showErrorMessage("Errore: impossibile aprire il file " + ex);}
    }
  }

  private void showErrorMessage(String s) {
    showMessageDialog(this, s);
  }

  public static void main(String[] args) {
    new BinSearchTreeGUI("Albero di ricerca");
  }
}
