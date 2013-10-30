package org.springframework.samples.mvc.convert;

/**
 * social security number
 */
public final class SocialSecurityNumber {

	private final String value;

	public SocialSecurityNumber(String value) {
		this.value = value;
	}

	/**
	 * maskformat value
	 */
	@MaskFormat("###-##-####")
	public String getValue() {
		return value;
	}

	public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value) {
		return new SocialSecurityNumber(value);
	}

}