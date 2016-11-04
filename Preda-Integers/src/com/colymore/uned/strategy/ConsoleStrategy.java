package com.colymore.uned.strategy;

class ConsoleStrategy implements PrintStrategy {

	@Override
	public void resolve(long result) {
		System.out.println(result);
	}
}
