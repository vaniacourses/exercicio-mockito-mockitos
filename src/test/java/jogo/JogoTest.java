package jogo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {

    private Dado dado1;
    private Dado dado2;
    private Jogador jogador;
    private Jogo jogo;

    @BeforeEach
    public void init() {
        dado1 = mock(Dado.class);
        dado2 = mock(Dado.class);
        jogador = new Jogador();
        jogo = new Jogo();
    }

    @Test
    public void testaDerrotaPrimeiraRodada() {
        when(dado1.numero()).thenReturn(2);
        when(dado2.numero()).thenReturn(1);

        boolean resultDerrota = jogo.jogar(jogador, dado1, dado2);

        assertFalse(resultDerrota, "Jogador deveria ter perdido na rodada 1 com a soma de 3 no dado");

    }

    @Test
    public void testaVitoriaPrimeiraRodada() {

        when(dado1.numero()).thenReturn(3);
        when(dado2.numero()).thenReturn(4);

        boolean resultVitoria = jogo.jogar(jogador, dado1, dado2);

        assertTrue(resultVitoria, "Jogador deveria ter vencido na rodada 1 com a soma de 7 no dado");
    }

    @Test
    public void testaDerrotaSegundaRodada() {

        when(dado1.numero()).thenReturn(2, 5);
        when(dado2.numero()).thenReturn(2, 2);

        boolean resultDerrota = jogo.jogar(jogador, dado1, dado2);

        assertFalse(resultDerrota,
                "Jogador deveria ter perdido na rodada 2 se na rodada 1 for tirado a soma 4 e na 2 ter tirado a soma 7");
    }

    @Test
    public void testaVitoriaSegundaRodada() {

        when(dado1.numero()).thenReturn(2, 1);
        when(dado2.numero()).thenReturn(3, 4);

        boolean resultVitoria = jogo.jogar(jogador, dado1, dado2);

        assertTrue(resultVitoria, "Jogador deveria ter vencido na rodada 2");

        verify(dado1, times(2)).numero();
        verify(dado2, times(2)).numero();
    }
}