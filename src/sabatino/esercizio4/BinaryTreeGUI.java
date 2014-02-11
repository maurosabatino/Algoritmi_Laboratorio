package sabatino.esercizio4;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import static java.awt.GraphicsEnvironment.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BinaryTreeGUI extends JFrame {
  private static final long serialVersionUID = 1L;
  
  private static int numFrame = 0;
  private BinaryTree tree;
  private JPanel controlPanel = new JPanel();
  private JTextField text = new JTextField(5);
  private JTextField text2 = new JTextField(10);
  private JButton insButton = new JButton("inserisci");
  private JButton elimButton = new JButton("elimina");
  private JTextField elimField = new JTextField(5);
  private JButton searchButton = new JButton("cerca");
  private JTextField searchField = new JTextField(5);  
  private JButton trimButton = new JButton("trim");
  private JButton trimmedButton = new JButton("trimmed");
  private JButton numNodiLivButton = new JButton("n. nodi al liv.");
  private JTextField trimField = new JTextField(2);
  private JPanel filePanel = new JPanel();
  private JButton loadButton = new JButton("Inserisci da file");
  private JButton resetButton = new JButton("Reset");
  private JMenuBar menuBar = new JMenuBar();
  private JMenu menu = new JMenu("Operazioni");

  private int windowIdx;
  private static ArrayList<BinaryTreeGUI> btreeGUIs = new ArrayList<BinaryTreeGUI>();
  private static String windowListString = "Inserire il numero della finestra con cui comparare, o qualsiasi altra cosa per chiudere:";

  private BinaryTreeGUI(String title, BinaryTree tr) {
    super(title);
    tree = tr;

    windowIdx = btreeGUIs.size();
    windowListString += "\n" + windowIdx + ": " + title;
    btreeGUIs.add(this);

    draw();

    resetButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          tree = new BinaryTree();
          repaint();
        }
      }
    );

    loadButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter("text file","txt");
          chooser.setFileFilter(filter);
          int returnVal = chooser.showOpenDialog(filePanel);
          if(returnVal == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getPath();
            readFile(name);
          }

          repaint();
        }
      }
    );

    insButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          try {
            int x = Integer.parseInt(text.getText());
            String s = text2.getText();
            tree.add(x, s);
          }
          catch(NumberFormatException ex) {
            System.out.println("elemento non intero");
          }
          catch(IllegalArgumentException ill) {
            System.out.println("stringa cammino errata");
          }
          repaint();
        }
      }
    );

    elimButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          try {
            int x = Integer.parseInt(elimField.getText());
            tree.removeSubtree(x);
            repaint();
          }
          catch(NumberFormatException ex) {
            System.out.println("Numero in formato non valido");
          }
        }
      }
    );

    searchButton.addActionListener(
        new ActionListener() {
          @Override
					public void actionPerformed(ActionEvent e) {
            try {
              int x = Integer.parseInt(searchField.getText());
              System.out.println(x + (tree.search(x) ? " presente" : " non presente"));
            }
            catch(NumberFormatException ex) {
              System.out.println("Numero in formato non valido");
            }
          }
        }
      );
    
    
    trimButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          try {
            int n = Integer.parseInt(trimField.getText());
            tree.trim(n);
            repaint();
          }
          catch(NumberFormatException ex) {
            System.out.println("livello non intero");
          }
        }
      }
    );

    trimmedButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          try {
            int n = Integer.parseInt(trimField.getText());
            BinaryTree bt = tree.trimmed(n);
            new BinaryTreeGUI("Trimmed tree", bt);
          }
          catch(NumberFormatException ex) {
            System.out.println("livello non intero");
          }
        }
      }
    );

    numNodiLivButton.addActionListener(
      new ActionListener() {
        @Override
				public void actionPerformed(ActionEvent e) {
          try {
            int n = Integer.parseInt(trimField.getText());
            System.out.println(tree.numNodesAtLevel(n));
          }
          catch(NumberFormatException ex) {
            System.out.println("livello non intero");
          }
        }
      }
    );
  }   // end constructors

  public BinaryTreeGUI(String title) {
    this(title, new BinaryTree());
  }

  public void draw() {
    GraphicsEnvironment ge = getLocalGraphicsEnvironment();
    Rectangle bounds = ge.getMaximumWindowBounds();
    int x = bounds.x + bounds.width/4 + numFrame*30;
    int y = bounds.y + bounds.height/4 + (numFrame++)*30;
    setBounds(x,y,2*bounds.width/3,2*bounds.height/3);
    add(BorderLayout.CENTER, getDrawingPanel());
    controlPanel.add(insButton);
    controlPanel.add(text);
    controlPanel.add(new JLabel("dove: "));
    controlPanel.add(text2);
    controlPanel.add(elimButton);
    controlPanel.add(elimField);
    controlPanel.add(searchButton);
    controlPanel.add(searchField);    
    controlPanel.add(numNodiLivButton);
    controlPanel.add(trimButton);
    controlPanel.add(trimmedButton);
    controlPanel.add(new JLabel("al livello: "));
    controlPanel.add(trimField);
    add(BorderLayout.SOUTH, controlPanel);
    filePanel.add(loadButton);
    filePanel.add(resetButton);
    add(BorderLayout.NORTH, filePanel);
    setJMenuBar(menuBar);
    menuBar.add(menu);
    addMenuItems();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

/** agginge all'albero binario degli elementi come descritto
    in un file di testo di nome fileName */
  public void readFile(String fileName) {
    try {
      Scanner input = new Scanner(new File(fileName));
      while(input.hasNextLine()) {
        String line = input.nextLine();
        Scanner lineScan = new Scanner(line);
        int elem = lineScan.nextInt();
        String path = lineScan.hasNext()? lineScan.next() : "";
        tree.add(elem, path);
        lineScan.close();
      }
      input.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("impossibile leggere il file " + fileName);
    }
    catch(Exception ex) {
      System.out.println("Bad input format - Eccezione: " + ex);
    }
  }

  private class TreeDrawing<E> {
    E element;
    TreeDrawing<E> left, right;
    int x, y, width, height;

    TreeDrawing(E element, int x, int y, int width, int height, TreeDrawing<E> left, TreeDrawing<E> right) {
      this.element = element;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.left = left;
      this.right = right;
    }
  } // end class TreeDrawing

  private class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int visitedNodeNumber = 1;
    private Graphics graphics;
    private int hScale = 40;
    private int vScale = 40;

    TreeDrawing<Integer> build(BinaryTree.Node node, int level) {
      if(node==null) return null;
      else {
        TreeDrawing<Integer> left = build(node.left, level + 1);
        String str = node.element.toString();
        Rectangle2D rect = graphics.getFontMetrics().getStringBounds(str, graphics);
        int width = (int)Math.round(rect.getWidth())+2;
        int height = (int)Math.round(rect.getHeight());
        int dx = (int)Math.round(rect.getCenterX());
        int dy = (int)Math.round(rect.getCenterY());
        int x = hScale*visitedNodeNumber - dx;
        int y = vScale*level + dy;
        visitedNodeNumber++;
        TreeDrawing<Integer> right = build(node.right, level + 1);
        return new TreeDrawing<Integer>(node.element, x, y, width, height, left, right);
      }
    }

    void draw(TreeDrawing<Integer> td) {
      if(td != null) {
        String str = td.element.toString();
        graphics.drawRect(td.x, td.y, td.width, td.height);
        graphics.drawString(str, td.x+1, td.y + td.height - 2);
        if(td.left != null) {
          int x1 = td.x + td.width/2;
          int y1 = td.y + td.height;
          int x2 = td.left.x + td.left.width/2;
          int y2 = td.left.y;
          graphics.drawLine(x1, y1, x2, y2);
        }
        draw(td.left);
        if(td.right != null) {
          int x1 = td.x + td.width/2;
          int y1 = td.y + td.height;
          int x2 = td.right.x + td.right.width/2;
          int y2 = td.right.y;
          graphics.drawLine(x1, y1, x2, y2);
        }
        draw(td.right);
      }
    }

    @Override
		public void paintComponent(Graphics g) {
      super.paintComponent(g);
      graphics = g;
      TreeDrawing<Integer> td = build(tree.root, 1);
      draw(td);
      visitedNodeNumber = 1;
    }
  }

  DrawingPanel getDrawingPanel() {
    return new DrawingPanel();
  }

  private JMenuItem creaItem(String name, ActionListener itemListener) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener(itemListener);
    return item;
  }

  private ActionListener preorderListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.printPreOrder();
    }
  };

  private ActionListener inorderListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.printInOrder();
    }
  };

  private ActionListener postorderListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.printPostOrder();
    }
  };

  private ActionListener heightListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println("height = " + tree.height());
    }
  };

  private ActionListener sommaListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println("sum = " + tree.sum());
    }
  };

  private ActionListener sizeListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println("size = " + tree.size());
    }
  };

  private ActionListener numFoglieListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println("numLeaves = " + tree.numberOfLeaves());
    }
  };

  private ActionListener cardiniListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.printCentralNodes();
    }
  };

  private ActionListener completoListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println(tree.isCompletelyBalanced() ? "completamente bilanciato" : "non completamente bilanciato");
    }
  };

  private ActionListener balListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println(tree.is1Balanced() ? "1-bilanciato" : "non 1-bilanciato");
    }
  };


  private ActionListener copyListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      BinaryTree bt = tree.copy();
      new BinaryTreeGUI("Copy", bt);
    }
  };

  private ActionListener reflectListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.mirrorInPlace();
      repaint();
    }
  };

  private ActionListener specularListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      BinaryTree bt = tree.mirror();
      new BinaryTreeGUI("Copia speculare", bt);
    }
  };

  private ActionListener incListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      tree.increment();
      repaint();
    }
  };

  private ActionListener ugualeAListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      try{
        String input = JOptionPane.showInputDialog(btreeGUIs.get(windowIdx), windowListString,
          "UgualeA", JOptionPane.PLAIN_MESSAGE);
        BinaryTree other = btreeGUIs.get(Integer.parseInt(input)).tree;
        System.out.println(tree.equalTo(other) ? "Sono uguali" : "Non sono uguali");
      }
      catch(NumberFormatException nfe){}
      catch(IndexOutOfBoundsException iobe){}
    }
  };
  
  private ActionListener maxListener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {
      System.out.println("max element = " + tree.maxElem());
    }
  };
  
  private ActionListener esercizio313Listener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {     
      int elem = tree.maxDescendantsHeightRatio().getElement();
      System.out.print("il nodo risultato contiene il valore: ");
      System.out.println(elem);
    }
  }; 
  
  private ActionListener esercizio314Listener = new ActionListener() {
    @Override
		public void actionPerformed(ActionEvent e) {     
      tree.printEquiNodes();
    }
  };   

  private void addMenuItems() {
    menu.add(creaItem("preorder", preorderListener));
    menu.add(creaItem("inorder", inorderListener));
    menu.add(creaItem("postorder", postorderListener));
    menu.add(creaItem("height", heightListener));
    menu.add(creaItem("sum", sommaListener));
    menu.add(creaItem("size", sizeListener));
    menu.add(creaItem("numLeaves", numFoglieListener));
    menu.add(creaItem("central nodes", cardiniListener));
    menu.add(creaItem("completely balanced?", completoListener));
    menu.add(creaItem("1-balanced?", balListener));
    menu.add(creaItem("copy", copyListener));
    menu.add(creaItem("mirror in place", reflectListener));
    menu.add(creaItem("mirror", specularListener));
    menu.add(creaItem("increment", incListener));
    menu.add(creaItem("is equal to", ugualeAListener));
    menu.add(creaItem("max element", maxListener));
    menu.add(creaItem("esercizio 3.13", esercizio313Listener));
    menu.add(creaItem("esercizio 3.14", esercizio314Listener));
  }

  public static void main(String[] args) {
    new BinaryTreeGUI("Alberi binari");
  }
}
