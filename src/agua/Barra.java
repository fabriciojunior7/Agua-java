package agua;

import java.awt.Color;

public class Barra {
    //Atributos
    private int largura, altura, x, y, velocidadeY, aceleracaoY, maxVelocidadeY, ocilacao;
    private Color cor;
    
    //Construtor
    public Barra(int x, int y, int largura, int altura, Color cor){
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        this.velocidadeY = 0;
        this.aceleracaoY = 1;
        this.maxVelocidadeY = 1;
        this.ocilacao = 1;
    }
    
    //Metodos
    public void atualizarPosicao(int altura){
        if(this.ocilacao > 0){
            if(this.velocidadeY > this.maxVelocidadeY || this.velocidadeY < -this.maxVelocidadeY){
                if(this.velocidadeY > 0){
                    this.velocidadeY = this.maxVelocidadeY;
                }
                else if(this.velocidadeY < 0){
                    this.velocidadeY = -this.maxVelocidadeY;
                }
            }
            else if(this.velocidadeY <= this.maxVelocidadeY && this.velocidadeY >= -this.maxVelocidadeY){
                if(this.y <= altura/2-this.ocilacao && this.aceleracaoY < 0){
                    this.aceleracaoY *= -1;
                }
                else if(this.y >= altura/2+this.ocilacao && this.aceleracaoY > 0){
                    this.aceleracaoY *= -1;
                }
                this.velocidadeY += this.aceleracaoY;
            }
        }
        else{
            this.velocidadeY = 0;
        }
        this.y += this.velocidadeY;
        
        //System.out.println(this.velocidadeY);
    }
    
    //Metodos Especiais
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }
    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Color getCor() {
        return cor;
    }
    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getVelocidadeY() {
        return velocidadeY;
    }
    public void setVelocidadeY(int velocidadeY) {
        this.velocidadeY = velocidadeY;
    }

    public int getAceleracaoY() {
        return aceleracaoY;
    }
    public void setAceleracaoY(int aceleracaoY) {
        this.aceleracaoY = aceleracaoY;
    }

    public int getMaxVelocidadeY() {
        return maxVelocidadeY;
    }
    public void setMaxVelocidadeY(int maxVelocidadeY) {
        this.maxVelocidadeY = maxVelocidadeY;
    }

    public int getOcilacao() {
        return ocilacao;
    }
    public void setOcilacao(int ocilacao) {
        this.ocilacao = ocilacao;
        this.maxVelocidadeY = ocilacao;
    }
    
    
}
