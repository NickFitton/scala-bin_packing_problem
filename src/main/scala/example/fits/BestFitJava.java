package example.fits;

import java.util.LinkedList;
import java.util.List;

public class BestFitJava {
  public static Integer calculate(List<Integer> weights, Integer binSize) {
    Integer result = 0;
    LinkedList<Integer> bins = new LinkedList<>();

    for (Integer weight : weights) {
      int i;
      int minimunSize = binSize + 1;
      int bi = 0;

      for (i = 0; i < bins.size(); i++) {
        if (bins.get(i) >= weight && bins.get(i) - weight < minimunSize) {
          bi = i;
          minimunSize = bins.get(i) - weight;
        }
      }

      if (minimunSize == binSize + 1) {
        bins.add(binSize - weight);
        result++;
      } else {
        bins.set(bi, bins.get(bi) - weight);
      }
    }

    return result;
  }
}
