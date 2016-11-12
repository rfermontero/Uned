package com.colymore.uned.resolver;

import com.colymore.uned.BigNumber;

public interface Resolver {
	BigNumber resolve(BigNumber left, BigNumber right);
}
