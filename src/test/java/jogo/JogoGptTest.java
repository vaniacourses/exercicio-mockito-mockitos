package jogo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Testes da classe Jogo")
public class JogoGptTest {

    private Jogo jogo;
    private Jogador jogadorMock;
    private Dado dado1Mock;
    private Dado dado2Mock;

    @BeforeEach
    void setup() {
        // Arrange: Criamos um objeto real da classe que queremos testar
        jogo = new Jogo();

        // Arrange: Criamos mocks para TODAS as dependências externas
        jogadorMock = mock(Jogador.class);
        dado1Mock = mock(Dado.class);
        dado2Mock = mock(Dado.class);
    }

    @Nested
    @DisplayName("Testes da Primeira Rodada")
    class TestesPrimeiraRodada {

        @Test
        @DisplayName("Deve vencer na primeira rodada quando a soma for 7")
        void deveVencerNaPrimeiraRodadaQuandoSomaFor7() {
            // Arrange: Configuramos o mock para retornar 7 na chamada do método lancar()
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(7);

            // Act: Executamos o método a ser testado
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert: Verificamos se o resultado foi uma vitória (true)
            assertTrue(resultado, "Deveria vencer com a soma 7 na primeira rodada");

            // Verify: Garantimos que o método lancar() foi chamado apenas uma vez
            verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
        }

        @Test
        @DisplayName("Deve vencer na primeira rodada quando a soma for 11")
        void deveVencerNaPrimeiraRodadaQuandoSomaFor11() {
            // Arrange
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(11);

            // Act
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert
            assertTrue(resultado, "Deveria vencer com a soma 11 na primeira rodada");
            verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
        }

        @Test
        @DisplayName("Deve perder na primeira rodada quando a soma for 2")
        void devePerderNaPrimeiraRodadaQuandoSomaFor2() {
            // Arrange
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(2);

            // Act
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert
            assertFalse(resultado, "Deveria perder com a soma 2 na primeira rodada");
            verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
        }
    }

    @Nested
    @DisplayName("Testes com Múltiplas Rodadas")
    class TestesMultiplasRodadas {

        @Test
        @DisplayName("Deve vencer na segunda rodada se repetir o ponto")
        void deveVencerNaSegundaRodadaSeRepetirOponto() {
            // Arrange: Simulamos duas chamadas. 1ª retorna 6 (o ponto), 2ª retorna 6 (a vitória)
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(6, 6);

            // Act
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert
            assertTrue(resultado, "Deveria vencer ao repetir o ponto 6 na segunda rodada");

            // Verify: O método deve ser chamado exatamente duas vezes
            verify(jogadorMock, times(2)).lancar(dado1Mock, dado2Mock);
        }

        @Test
        @DisplayName("Deve perder na segunda rodada se a soma for 7")
        void devePerderNaSegundaRodadaSeSomaFor7() {
            // Arrange: 1ª chamada retorna 8 (o ponto), 2ª retorna 7 (a derrota)
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(8, 7);

            // Act
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert
            assertFalse(resultado, "Deveria perder se o ponto for 8 e a segunda soma for 7");
            verify(jogadorMock, times(2)).lancar(dado1Mock, dado2Mock);
        }

        @Test
        @DisplayName("Deve vencer após várias rodadas se repetir o ponto")
        void deveVencerAposVariasRodadas() {
            // Arrange: Ponto=4, jogada intermediária=9, jogada da vitória=4
            when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(4, 9, 4);

            // Act
            boolean resultado = jogo.jogar(jogadorMock, dado1Mock, dado2Mock);

            // Assert
            assertTrue(resultado, "Deveria vencer ao repetir o ponto 4 na terceira rodada");

            // Verify: O método foi chamado 3 vezes
            verify(jogadorMock, times(3)).lancar(dado1Mock, dado2Mock);
        }
    }
}