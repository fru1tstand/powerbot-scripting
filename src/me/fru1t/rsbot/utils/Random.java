package me.fru1t.rsbot.utils;

public class Random extends org.powerbot.script.Random {
	private static final int DEFAULT_NEXT_SKEWED_GAUSS_ATTEMPTS = 25;
	
	/**
	 * The rate at which this method returns true will tend toward the given probability after
	 * repeated calls.
	 * 
	 * @param probabilityPercent The pseudo probability that this method should return true. This
	 * value should be between 0 and 100. Anything less than 0 will always return true, anything
	 * above 100 will always return false.
	 * @return What the roll returned.
	 */
	public static boolean roll(int probabilityPercent) {
		return Random.nextDouble(0, 100) < probabilityPercent;
	}
	
	/**
	 * Returns a skewed normal distribution using Random.nextGaussian by rerolling any value
	 * that is <= min or >= max. This means that both min and max are exclusive in the range.
	 * 
	 * @param min The absolute minimum (non inclusive) the result should be.
	 * @param max The absolute maximum (non inclusive) the result should be.
	 * @param mean The mean of the distribution
	 * @param stdev The standard deviation of the distribution
	 * @param attempts The number of attempts to make before falling back to simply nextInt
	 * @return A number between min and max, with which if repeated multiple times, will tend
	 * towards a unimodal skewed normal distribution about the given mean and with the given
	 * standard deviation.
	 */
	public static int nextSkewedGaussian(int min, int max, int mean, double stdev, int attempts) {
		if (attempts < 1) {
			attempts = DEFAULT_NEXT_SKEWED_GAUSS_ATTEMPTS;
		}
		
		int result = min;
		int attemptCount = 0;
		while ((result > max || result < min) && attemptCount < attempts) {
			attemptCount++;
			result = nextGaussian(min, max, mean, stdev);
		}
		if (result > max || result < min) {
			result = Random.nextInt(min, max);
		}
		return result;
	}
}
