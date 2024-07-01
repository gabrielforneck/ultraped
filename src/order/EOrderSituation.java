package order;

import order.exceptions.NotDefinedSituationException;

public enum EOrderSituation {
	NEW,
	DELIVERED,
	CANCELED;
	
	@Override
	public String toString() {
		switch(this) {
			case NEW:
				return "Novo";
			
			case DELIVERED:
				return "Entregue";
				
			case CANCELED:
				return "Cancelado";
		}
		
		throw new NotDefinedSituationException();
	}
}
