package io.pivotal.springroots.aspects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DespicableAdviceTest {
	@Test
	@DisplayName("Advised Me is despicable.")
	public void makesMeDespicable() {
		Me me = new Minion("Kevin");

		ProxyFactory despicabilityFactory = new ProxyFactory();
		despicabilityFactory.setTarget(me);
		despicabilityFactory.addAdvice(new DespicableAdvice());
		Me despicableMe = (Me) despicabilityFactory.getProxy();

		assertThat(me.name()).isEqualTo("Kevin");
		assertThat(me.greet("World")).isEqualTo("Hello, World, it is I, Kevin!");

		assertThat(despicableMe.name()).isEqualTo("Despicable Kevin");
		assertThat(despicableMe.greet("World")).isEqualTo("Hello, World, it is I, Despicable Kevin!");
	}

	@Test
	@DisplayName("Advised You is despicable.")
	public void makesYouDespicable() {
		You you = new Human("Gru");

		ProxyFactory despicabilityFactory = new ProxyFactory();
		despicabilityFactory.setTarget(you);
		despicabilityFactory.addAdvice(new DespicableAdvice());
		You despicableYou = (You) despicabilityFactory.getProxy();

		assertThat(you.name()).isEqualTo("Gru");
		assertThat(you.claim("the Statue of Liberty")).isEqualTo("I, Gru, have stolen the Statue of Liberty!");

		assertThat(despicableYou.name()).isEqualTo("Despicable Gru");
		assertThat(despicableYou.claim("the Statue of Liberty")).isEqualTo("I, Despicable Gru, have stolen the Statue of Liberty!");
	}
}
