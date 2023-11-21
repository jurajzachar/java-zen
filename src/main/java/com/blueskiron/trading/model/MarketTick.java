package com.blueskiron.trading.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public record MarketTick(TradedPair pair, Bid bid, Ask ask, Long seqNr) {
}
