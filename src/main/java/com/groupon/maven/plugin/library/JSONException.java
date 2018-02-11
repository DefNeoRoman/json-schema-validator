package com.groupon.maven.plugin.library;

/**
 * The library.JSONException is thrown by the JSON.org classes when things are amiss.
 *
 * @author JSON.org
 * @version 2015-12-09
 */
public class JSONException extends RuntimeException {
    /** Serialization ID */
    private static final long serialVersionUID = 0;

    /**
     * Constructs a library.JSONException with an explanatory message.
     *
     * @param message
     *            Detail about the reason for the exception.
     */
    public JSONException(final String message) {
        super(message);
    }

    /**
     * Constructs a library.JSONException with an explanatory message and cause.
     * 
     * @param message
     *            Detail about the reason for the exception.
     * @param cause
     *            The cause.
     */
    public JSONException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new library.JSONException with the specified cause.
     * 
     * @param cause
     *            The cause.
     */
    public JSONException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
