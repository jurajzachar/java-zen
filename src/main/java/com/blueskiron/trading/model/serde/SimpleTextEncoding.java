package com.blueskiron.trading.model.serde;

import com.blueskiron.trading.model.*;
import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

/*
 simple text encoding suitable for testing and development purposes
 */
public class SimpleTextEncoding {
  public static TradedPair parseTradedPair(String encoded) {
    String[] tokens = encoded.strip().toUpperCase(Locale.ENGLISH).split("_");
    return TradedPair.withFxCurrencies(FxCurrency.of(tokens[0]), FxCurrency.of(tokens[1]));
  }

  public static Tuple2<Double, Double> parseOrderBookEntry(String encoded) {
    final var sanitized = encoded
        .replace("A(", "")
        .replace("B(", "")
        .replace(")", "");
    String[] tokens = sanitized.split(",");
    return new Tuple2<>(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
  }
  public static String marshall(MarketTick tick) {
    final var priceFmt = "%." + tick.pair().marketPrecision() + "f";
    final var amountFmt = "%." + tick.pair().amountPrecision() + "f";
    final var FMT = "[%s;B(" + priceFmt + "," + amountFmt + ");A(" + priceFmt + "," + amountFmt + ");%d]";
    return String.format(FMT,
        tick.pair(),
        tick.bid().price(),
        tick.bid().amount(),
        tick.ask().price(),
        tick.ask().amount(),
        tick.seqNr());
  }

  public static MarketTick unmarshall(String encoded) {
    // sanity check - must be enclosed by square brackets
    if (encoded.charAt(0) != '[' && encoded.charAt(encoded.length() - 1) != ']') {
      throw new RuntimeException("cannot unmarshall from unsupported format: " + encoded);
    }
    final var unboxed = encoded.replace("[", "").replace("]", "");
    final var tokens = new ArrayList<>(Collections.list(new StringTokenizer(unboxed, ";")));
    //sanity check - must be of length 4
    if (tokens.size() != 4) {
      throw new RuntimeException("invalid number of tokens in the envelope: " + tokens);
    }
    final var tradedPair = parseTradedPair((String) tokens.get(0));
    final var bidTuple = parseOrderBookEntry((String) tokens.get(1));
    final var bid = new Bid(bidTuple._1, bidTuple._2);
    final var askTuple = parseOrderBookEntry((String) tokens.get(2));
    final var ask = new Ask(askTuple._1, askTuple._2);
    final var seqNr = Long.parseLong((String) tokens.get(3));

    return new MarketTick(tradedPair, bid, ask, seqNr);
  }

  public static void main(String[] args) {
    var encoded = "[EUR_USD;B(1.12345,2);A(1.54321,3);12345]";
    System.out.println("1st encoded: " + encoded);
    var marketTick = SimpleTextEncoding.unmarshall(encoded);
    System.out.println("1st parse:" + marketTick);
    var encoded2 = SimpleTextEncoding.marshall(marketTick);
    System.out.println("2nd encoded: " + encoded2);
    assert encoded.equals(encoded2);
  }
}
