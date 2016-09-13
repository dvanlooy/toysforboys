package be.vdab.util;

import java.math.BigDecimal;
import java.util.Objects;

public final class Invoercontrole{

	/**
	 * checks if given long is positive
	 * 
	 * @param param
	 * @param error
	 * @return Long
	 * @throws IllegalArgumentException
	 */
	public final static Long positiveLong(long param, String error) throws IllegalArgumentException {
		if (param < 0) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}

	/**
	 * checks if given String is not empty or not null
	 * 
	 * @param param
	 * @param error
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public final static String noEmptyOrNullString(String param, String error) throws IllegalArgumentException {
		if (param == null || param.equals("")) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}

	/**
	 * checks if given BigDecimal is not null or negative
	 * 
	 * @param param
	 * @param error
	 * @return BigDecimal
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public final static BigDecimal positiveBigDecimal(BigDecimal param, String error)
			throws IllegalArgumentException, NullPointerException {
		Objects.requireNonNull(param, "parameter cannot be null");
		if (param.signum() == -1) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}

}
