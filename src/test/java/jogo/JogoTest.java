package jogo;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {

    // private Dado dado1;
    // private Dado dado2;
    // private Jogador jogador;
    // private Jogo jogo;

    // @BeforeEach
    // public void init(){
    //     dado1 = mock(Dado.class);
    //     dado2 = mock(Dado.class);
    //     jogador = mock(Jogador.class);   
    //     jogo = new Jogo();
    // }

    // @Test
    // public void testaDerrota(){
    //     when(dado1.numero()).thenReturn(2);
    //     when(dado2.numero()).thenReturn(1);
    //     when(jogador.lancar(dado1, dado2)).thenReturn(3);

    //     boolean result = jogo.jogar(jogador, dado1, dado2);

    //     Assertions.assertEquals(result, false);;
    // }
    @Test
    public void deveVencerNaPrimeiraRodada() {
        // Arrange
        Jogador jogador = mock(Jogador.class);
        Dado dado1 = mock(Dado.class);
        Dado dado2 = mock(Dado.class);
        
        // Simula que o jogador lança os dados e a soma sai 7 (vencedor na primeira rodada)
        when(jogador.lancar(dado1, dado2)).thenReturn(7);
        
        Jogo jogo = new Jogo();

        // Act
        boolean resultado = jogo.jogar(jogador, dado1, dado2);

        // Assert
        assertTrue(resultado, "O jogador deveria vencer na primeira rodada quando sai 7");
        
        // Verifica que só foi chamado uma vez (já que ganhou de primeira)
        verify(jogador, times(1)).lancar(dado1, dado2);
    }
}