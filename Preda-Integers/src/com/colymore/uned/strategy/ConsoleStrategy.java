package com.colymore.uned.strategy;

class ConsoleStrategy implements PrintStrategy {

	@Override
	public void resolve(String result) {
		System.out.println(result);
	}
}
