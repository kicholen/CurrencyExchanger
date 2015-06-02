package currency.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class CurrencyEventPublisher implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void publish(String from, String to, float value) {
		CurrencyUpdateEvent ce = new CurrencyUpdateEvent(this, from, to, value);
		applicationEventPublisher.publishEvent(ce);
	}
}
