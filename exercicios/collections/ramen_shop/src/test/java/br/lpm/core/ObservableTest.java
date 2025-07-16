package br.lpm.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.bebida.Refrigerante;
import br.lpm.business.Cliente;
import br.lpm.business.ListaDeEspera;
import br.lpm.business.Pedido;
import br.lpm.prato.Boi;
import br.lpm.prato.Tamanho;
import br.lpm.prato.Vegano;

public class ObservableTest {
    private Observable observable;
    private Observer observer1;
    private Observer observer2;

    @BeforeEach
    void setUp() {
        observable = ListaDeEspera.getInstance();
        observable.getObservers().clear();
        observer1 = new Cliente("Jorge da Silva", new Pedido(new Boi("Ramen Boi", Tamanho.MEDIO, new Refrigerante())));
        observer2 = new Cliente("João Silva", new Pedido(new Vegano("Ramen Vegano", Tamanho.GRANDE, new Refrigerante())));
    }

    @Test
    void testAddObserver() {
        assertEquals(0, observable.getObservers().size(), "Observable deve estar vazio antes de adicionar observadores");

        observable.addObserver(observer1);
        assertEquals(1, observable.getObservers().size(), "Após adicionar o primeiro observador, deve conter 1");

        observable.addObserver(observer2);
        assertEquals(2, observable.getObservers().size(), "Deve conter 2 observadores após adicionar o segundo");

        observable.addObserver(null);
        assertEquals(2, observable.getObservers().size(),"Deve continuar com 2 observadores após tentar adicionar um nulo");
    }

    @Test
    void testRemoveObserver() {
        observable.addObserver(observer1);
        observable.addObserver(observer2);
        assertEquals(2, observable.getObservers().size(), "Deve conter 2 observadores após adicionar");

        observable.removeObserver(observer1);
        assertEquals(1, observable.getObservers().size(), "Tamanho deve reduzir para 1 após remover");
        assertEquals(observer2, observable.getObservers().get(0), "O único observador restante deve ser observer2");

        observable.removeObserver(observer2);
        assertEquals(0, observable.getObservers().size(), "Tamanho deve ser 0 após remover o ultimo observador");

    }

    @Test
    void testRemoveObserverInvalidos() {
        observable.addObserver(observer1);
        assertEquals(1, observable.getObservers().size(), "Deve conter 1 observador após adicionar");

        observable.removeObserver(null);
        assertEquals(1, observable.getObservers().size(),
                "Tamanho não deve mudar ao tentar remover um observador nulo");

        observable.removeObserver(observer2);
        assertEquals(1, observable.getObservers().size(),"Não deve ser possivel remover um observador que não foi adicionado");

        observable.removeObserver(observer1);
        assertEquals(0, observable.getObservers().size(), "Tamanho deve ser 0 após remover o único observador");

        observable.removeObserver(observer1);
        assertEquals(0, observable.getObservers().size(),
                "Não deve ser possivel remover um observador que já foi removido");

    }

    @Test
    void testSetStateValido() {
        observable.setState(1);
        assertEquals(1, observable.getState(), "O estado deve ser alterado para 1");
        observable.setState(2);
        assertEquals(2, observable.getState(), "O estado deve ser alterado para 2");
        observable.setState(0);
        assertEquals(0, observable.getState(), "O estado deve ser alterado para 0");
    }

    @Test
    void testSetStateInvalido() {
        observable.setState(-1);
        assertEquals(0, observable.getState(), "Testando set com valor menor que 0, não deve alterar o estado");
        observable.setState(3);
        assertEquals(0, observable.getState(), "Testando set com valor maior que 2, não deve alterar o estado");
    }
}
