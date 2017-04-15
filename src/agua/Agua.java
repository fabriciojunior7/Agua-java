package agua;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Agua extends JComponent implements ActionListener, KeyListener{
    
    private static String titulo = "Waves by Junior";
    private static int largura = 700, altura = 600;
    private static List ondas = new ArrayList();
    private static List calda = new ArrayList();
    private static int larguraB = 10, alturaB = 700;
    private static int numBarras = largura/larguraB;
    //private static int numBarras = 4;
    private static Agua jogo = new Agua();
    private static int frames = 0, framesInternos = 0;
    private static int indexAtual = 0;
    private static int velocidadeSimulador = 10;
    private static int oo = 1;
    private static boolean aleatorio = true;

    
    public static void main(String[] args){
            for(int i=0; i<numBarras; i++){
                Barra b;
                b = new Barra(larguraB*i, 300, larguraB, alturaB, new Color(0, 0, 255));
                ondas.add(b);
                calda.add(300);
            }
        
        JFrame tela = new JFrame(titulo);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(largura, altura);
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);
        tela.add(jogo);
        
        Timer t = new Timer(10, jogo);
        t.start();
        tela.addKeyListener(jogo);
        indexAtual++;
    }
    
    @Override
    public void paintComponent(Graphics g){
        frames++;
        //Barras
        if(frames % velocidadeSimulador == 0){
            framesInternos++;
            
            if(aleatorio == true && framesInternos % 250 == 0){
                Random rand = new Random();
                int random = rand.nextInt(2);
                if(random == 0){
                    diminuir();
                }
                else{
                    aumentar();
                }
                //System.out.println(random);
            }
            
            Barra b = (Barra) ondas.get(0);
            b.atualizarPosicao(altura);
            calda.remove(calda.size()-1);
            calda.add(0, b.getY());
            
            for(int i=1; i<ondas.size(); i++){
                Barra bb = (Barra) ondas.get(i);
                bb.setY((int) calda.get(i));
            }
        }
        
        //Desenhar
        for(int i=0; i<ondas.size(); i++){
                Barra b = (Barra) ondas.get(i);
                g.setColor(new Color(140, 140, 255));
                g.fillRect(b.getX(), b.getY(), b.getLargura(), b.getAltura());
                //g.setColor(new Color(0, 0, 255));
                //g.drawRect(b.getX(), b.getY(), b.getLargura(), b.getAltura());
            }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Barra bb = (Barra) ondas.get(0);
        //Aumentar
        if(e.getKeyCode() == KeyEvent.VK_UP){
            aumentar();
        }
        //Diminuir
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            diminuir();
        }
        
        //Esquerda
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && larguraB > 1){
            ondas.clear();
            calda.clear();
            larguraB /= 2;
            numBarras = 1+largura/larguraB;
            for(int i=0; i<numBarras; i++){
                Barra b;
                b = new Barra(larguraB*i, 300, larguraB, alturaB, new Color(0, 0, 255));
                ondas.add(b);
                calda.add(300);
            }
            manterOcilacao();
            //System.out.println(larguraB);
        }
        //Direita
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && larguraB < largura){
            ondas.clear();
            calda.clear();
            larguraB *= 2;
            numBarras = 1+largura/larguraB;
            for(int i=0; i<numBarras; i++){
                Barra b;
                b = new Barra(larguraB*i, 300, larguraB, alturaB, new Color(0, 0, 255));
                ondas.add(b);
                calda.add(300);
            }
            manterOcilacao();
            //System.out.println(larguraB);
        }
        
        //Aleatorio
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(aleatorio == false){
                aleatorio = true;
            }
            else{
                aleatorio = false;
            }
        }
        
        //Velocidade Simulador
        else if(e.getKeyCode() == KeyEvent.VK_W && velocidadeSimulador > 1){
            velocidadeSimulador /= 2;
            //System.out.println(velocidadeSimulador);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S && velocidadeSimulador <= 640){
            velocidadeSimulador *= 2;
            //System.out.println(velocidadeSimulador);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void manterOcilacao(){
        Barra b = (Barra) ondas.get(0);
        b.setOcilacao(oo);
    }
    
    public void aumentar(){
        Barra bb = (Barra) ondas.get(0);
        
        if(bb.getOcilacao() < 16){
            if(bb.getOcilacao() < 1){
                bb.setOcilacao(1);
            }
            else if(bb.getOcilacao() == 1){
                bb.setOcilacao(2);
            }
            else{
                bb.setOcilacao(bb.getOcilacao()*2);
            }
            oo = bb.getOcilacao();
        }
    }
    
    public void diminuir(){
        Barra bb = (Barra) ondas.get(0);
        bb.setOcilacao(bb.getOcilacao()/2);
        oo = bb.getOcilacao();
    }
    
}
