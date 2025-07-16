package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionTest {

    private Subscription subscription;
    private LocalDate startDate;
    private String subscriberName;
    private String subscriptionName;
    private float monthlyValue;

    @BeforeEach
    void setUp() {
        subscription = new Subscription();
    }

    @Test
    void testSetMonthlyValueValido() {
        monthlyValue = 50.0F;
        subscription.setMonthlyValue(monthlyValue);
        assertEquals(monthlyValue, subscription.getMonthlyValue(), "Testando valor mensal acima de 0.");
    }

    @Test
    void testSetMonthlyValueInvalido() {
        monthlyValue = 0.0F;
        assertThrows(IllegalArgumentException.class, () -> subscription.setMonthlyValue(monthlyValue), 
            "Testando valor mensal igual a 0.");
        monthlyValue = -30.0F;
        assertThrows(IllegalArgumentException.class, () -> subscription.setMonthlyValue(monthlyValue), 
            "Testando valor mensal menor que 0.");
    }

    @Test
    void testSetStartDateValida() {
        startDate = LocalDate.now().plusDays(1);
        subscription.setStartDate(startDate);
        assertEquals(startDate, subscription.getStartDate(), "Testando data futura.");
    }
    
    @Test
void testSetStartDateInvalida() {
    startDate = LocalDate.of(2023, 3, 1);
    assertThrows(IllegalArgumentException.class, () -> subscription.setStartDate(startDate), 
        "Testando data passada.");
        
    assertThrows(IllegalArgumentException.class, () -> subscription.setStartDate(null), 
        "Testando data nula.");
}

    @Test
    void testSetSubscriberNameValido() {
        subscriberName = "João Silva";
        subscription.setSubscriberName(subscriberName);
        assertEquals(subscriberName, subscription.getSubscriberName(), 
            "Testando nome de assinante válido com espaço e acento.");
    }

    @Test
    void testSetSubscriberNameInvalido() {
        subscriberName = "João123";
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriberName(subscriberName), 
            "Testando nome de assinante com números.");
        subscriberName = "";
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriberName(subscriberName), 
            "Testando nome de assinante vazio.");
        subscriberName = "   ";
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriberName(subscriberName), 
            "Testando nome de assinante apenas com espaços.");
        subscriberName = null;
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriberName(subscriberName), 
            "Testando nome de assinante nulo.");
    }

    @Test
    void testSetSubscriptionNameValido() {
        subscriptionName = "Netflix Premium";
        subscription.setSubscriptionName(subscriptionName);
        assertEquals(subscriptionName, subscription.getSubscriptionName(), 
            "Testando nome de assinatura válido com espaço.");
    }
    
    @Test
    void testSetSubscriptionNameInvalido() {
        subscriptionName = "";
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriptionName(subscriptionName), 
            "Testando nome de assinatura vazio.");
        subscriptionName = "    ";
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriptionName(subscriptionName), 
            "Testando nome de assinatura apenas com espaços.");
        subscriptionName = null;
        assertThrows(IllegalArgumentException.class, () -> subscription.setSubscriptionName(subscriptionName), 
            "Testando nome de assinatura nulo.");
    }

}